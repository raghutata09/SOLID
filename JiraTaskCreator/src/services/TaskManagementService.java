package services;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import entities.User;
import entities.Task;
import entities.Story;
import entities.WorkloadReport;
import enums.TaskStatus;
import utils.ValidationUtil;
import exceptions.TaskException;


public class TaskManagementService {
    private static volatile TaskManagementService instance;

    private final Map<Long, Task> tasksById;
    private final Map<Long, Story> storiesById;
    private final Map<Long, List<Task>> tasksByUser;
    private final UserService userService;
    
    private TaskManagementService() {
        this.tasksById = new ConcurrentHashMap<>();
        this.storiesById = new ConcurrentHashMap<>();
        this.tasksByUser = new ConcurrentHashMap<>();
        this.userService = UserService.getInstance();
    }
    
    // Get singleton instance
    public static synchronized TaskManagementService getInstance() {
        if (instance == null) {
            instance = new TaskManagementService();
        }
        return instance;
    }
    
    //Create a new task
    public synchronized Task createTask(String title, String description, LocalDateTime deadline, User assignedUser) {
        // Validate Title . deadline and assigned user
        if (!ValidationUtil.validateTaskTitle(title)) {
            throw new TaskException("Invalid task title");
        }
        if (!ValidationUtil.validateDeadline(deadline)) {
            throw new TaskException("Invalid deadline");
        }
        if (assignedUser == null || !userService.userExists(assignedUser.getId())) {
            throw new TaskException("Invalid assigned user");
        }

        Task task = new Task(title, description, deadline, assignedUser);

        tasksById.put(task.getId(), task);

        tasksByUser.computeIfAbsent(assignedUser.getId(), k -> new CopyOnWriteArrayList<>()).add(task);
        
        return task;
    }
    
   //Create a subtask
    public synchronized Task createSubtask(long parentTaskId, String title, String description, LocalDateTime deadline, User assignedUser) {
        // Validate parent task
        Task parentTask = tasksById.get(parentTaskId);
        if (parentTask == null) {
            throw new TaskException("Parent task not found");
        }
        
        // Create subtask
        Task subtask = createTask(title, description, deadline, assignedUser);
        
        // Set parent relationship using Task entity method
        parentTask.addSubtask(subtask);
        
        return subtask;
    }
    
   //Create a story
    public synchronized Story createStory(String title, String description, List<Task> tasks, User loggedInUser) {
        if (!ValidationUtil.validateTaskTitle(title)) {
            throw new TaskException("Invalid story title");
        }
        if (tasks == null || tasks.isEmpty()) {
            throw new TaskException("Story must contain at least one task");
        }
        
        // Validate all tasks exist
        for (Task task : tasks) {
            if (!tasksById.containsKey(task.getId())) {
                throw new TaskException("Task not found: " + task.getId());
            }
        }
        
        // Create story
        Story story = new Story(title, description,loggedInUser);
        
        // Add tasks to story
        for (Task task : tasks) {
            story.addTask(task);
        }
        
        // Store story
        storiesById.put(story.getId(), story);
        
        return story;
    }
    
     //Update task
    public synchronized Task updateTask(long taskId, String title, String description, LocalDateTime deadline) {
        Task task = tasksById.get(taskId);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        
        // Validate inputs
        if (!ValidationUtil.validateTaskTitle(title)) {
            throw new TaskException("Invalid task title");
        }
        if (!ValidationUtil.validateDeadline(deadline)) {
            throw new TaskException("Invalid deadline");
        }
        
        // Update task
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        
        return task;
    }
    
    //Update task status
    public synchronized Task updateTaskStatus(long taskId, TaskStatus newStatus) {
        Task task = tasksById.get(taskId);
        if (task == null) {
            throw new TaskException("Task not found");
        }
        
        TaskStatus oldStatus = task.getStatus();
        task.setStatus(newStatus);

        //We can check if same same status then do nothing
        return task;
    }
    
    //Move task to different parent
    public synchronized Task moveTask(long taskId, long newParentId) {
        Task task = tasksById.get(taskId);
        //NULL check for task and parent
        if (task == null) {
            throw new TaskException("Task not found");
        }

        Task newParent = tasksById.get(newParentId);
        if (newParent == null) {
            throw new TaskException("New parent task not found");
        }
        
        // Prevent circular reference
        if (wouldCreateCircularReference(task, newParent)) {
            throw new TaskException("Cannot move task: would create circular reference");
        }
        
        Long oldParentId = task.getParentTaskId(); // Old = d
        
        // Remove from old parent using Task entity method
        if (oldParentId != null) {
            Task oldParent = tasksById.get(oldParentId);
            if (oldParent != null) {
                oldParent.removeSubtask(task);
            }
        }
        
        // Add to new parent using Task entity method ,
        newParent.addSubtask(task);
        
        return task;
    }
    
    //Check for circular reference
    private boolean wouldCreateCircularReference(Task task, Task newParent) {
        if (task.getId() == newParent.getId()) { // Task -B , NewParent - E

            return true;
        }
        
        Long parentId = newParent.getParentTaskId();
        // D - parentID
        while (parentId != null) {
            if (parentId == task.getId()) { //C
                return true;
            }
            Task parent = tasksById.get(parentId);
            if (parent == null) {
                break;
            }
            parentId = parent.getParentTaskId();//B
        }
        return false;
    }
    
    //Delete task
    public synchronized boolean deleteTask(long taskId) {
        Task task = tasksById.get(taskId);
        if (task == null) {
            return false;
        }
        
        // Delete all subtasks recursively
        deleteSubtasksRecursively(task);
        
        // Remove from parent using Task entity method
        if (task.getParentTaskId() != null) {
            Task parent = tasksById.get(task.getParentTaskId());
            if (parent != null) {
                parent.removeSubtask(task);
            }
        }
        
        // Remove from user mapping
        if (task.getAssignedUser() != null) {
            tasksByUser.get(task.getAssignedUser().getId()).remove(task);
        }
        
        // Remove task
        tasksById.remove(taskId);
        
        return true;
    }
    
    //Delete subtasks recursively
    private void deleteSubtasksRecursively(Task parentTask) {
        List<Task> subtasks = new ArrayList<>(parentTask.getSubtasks());
        for (Task subtask : subtasks) {
            deleteSubtasksRecursively(subtask);
            tasksById.remove(subtask.getId());
            
            if (subtask.getAssignedUser() != null) {
                tasksByUser.get(subtask.getAssignedUser().getId()).remove(subtask);
            }
        }
    }
    
    //Get tasks by user
    public List<Task> getTasksByUser(long userId) {
        List<Task> userTasks = tasksByUser.get(userId);
        return userTasks != null ? new ArrayList<>(userTasks) : new ArrayList<>();
    }
    
    //Get user workload
    public synchronized WorkloadReport getUserWorkload(long userId) {
        List<Task> userTasks = getTasksByUser(userId);

        // Group tasks by status
        Map<TaskStatus, Integer> tasksByStatus = new HashMap<>();
        for (TaskStatus status : TaskStatus.values()) {
            tasksByStatus.put(status, 0);
        }
        for (Task task : userTasks) {
            TaskStatus status = task.getStatus();
            tasksByStatus.put(status, tasksByStatus.get(status) + 1);
        }

        // Find overdue tasks
        List<Task> overdueTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Task task : userTasks) {
            if (task.getDeadline().isBefore(now) && task.getStatus() != TaskStatus.COMPLETED) {
                overdueTasks.add(task);
            }
        }

        // Find upcoming deadlines (next 7 days)
        LocalDateTime weekFromNow = now.plusDays(7);
        List<Task> upcomingDeadlines = new ArrayList<>();
        for (Task task : userTasks) {
            if (task.getDeadline().isAfter(now) &&
                task.getDeadline().isBefore(weekFromNow) &&
                task.getStatus() != TaskStatus.COMPLETED) {
                upcomingDeadlines.add(task);
            }
        }

        return new WorkloadReport(userId, userTasks.size(), tasksByStatus, overdueTasks, upcomingDeadlines);
    }

} 