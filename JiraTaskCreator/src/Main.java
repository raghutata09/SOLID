//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// Import from new package structure
import entities.User;
import entities.Task;
import entities.Story;
import entities.WorkloadReport;
import services.TaskManagementService;
import services.UserService;
import services.AuthenticationService;
import enums.TaskStatus;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== Task Management System Demo ===\n");
        
        // Get singleton instances
        TaskManagementService taskService = TaskManagementService.getInstance();
        UserService userService = UserService.getInstance();
        AuthenticationService authService = AuthenticationService.getInstance();
        
        // Demo 1: User Registration and Login
        System.out.println("1. User Registration and Login:");
        System.out.println("--------------------------------");
        
        // Register users with strong passwords
        User user1 = userService.registerUser("raghu_tata", "raghu@phonepe.com", "Raghu@09");
        User user2 = userService.registerUser("vidhi_tata", "vidhi@phonepe.com", "Vidhi!20");
        User user3 = userService.registerUser("aman", "aman@phonepe.com", "Aman$29!");
        
        System.out.println("Registered users: " + user1.getUsername() + ", " + user2.getUsername() + ", " + user3.getUsername());
        
        // Login. Ideally we can maintain active sessions and we can pass that session id , which we can check while
        // checking the other api calls but due to lack of time, I am not implementing it
        User loggedInUser = authService.login("raghu_tata", "Raghu@09");
        System.out.println("Logged in user: " + loggedInUser.getUsername() + "\n");
        
        // Demo 2: Task Creation
        System.out.println("2. Task Creation:-");
        System.out.println("-----------------");
        
        LocalDateTime deadline1 = LocalDateTime.now().plusDays(7);
        LocalDateTime deadline2 = LocalDateTime.now().plusDays(14);
        LocalDateTime deadline3 = LocalDateTime.now().plusDays(21);
        
        Task task1 = taskService.createTask("Partnermech", "Design PartnerMech V4", deadline1, loggedInUser);
        Task task2 = taskService.createTask("SubwaySurfers", "Make a reskin for Subway Surfers", deadline2, user2);
        Task task3 = taskService.createTask("Racing mech", "Design UI/UX and sound for racing mech", deadline3, user3);
        
        System.out.println("Created tasks:-");
        System.out.println("- " + task1.getTitle() + " (Asigned to: " + task1.getAssignedUser().getUsername() + ")");
        System.out.println("- " + task2.getTitle() + " (Asigned to: " + task2.getAssignedUser().getUsername() + ")");
        System.out.println("- " + task3.getTitle() + " (Asigned to: " + task3.getAssignedUser().getUsername() + ")");
        
        // Demo 3: Subtask Creation
        System.out.println("3. Subtask Creation:-");
        System.out.println("--------------------");
        
        Task subtask1 = taskService.createSubtask(task1.getId(), "Logic subwaySurfers", "Logic creating for subwaySurfers", LocalDateTime.now().plusDays(3), user2);
        Task subtask2 = taskService.createSubtask(task1.getId(), "Entities for PartnerMech", "Entities for partnermech", LocalDateTime.now().plusDays(5), user3);
        Task subtask3 = taskService.createSubtask(task2.getId(), "Entities Subway surfer", "Automation script for Subway surfer", LocalDateTime.now().plusDays(10), loggedInUser);
        
        System.out.println("Created subtasks:-");
        System.out.println("- " + subtask1.getTitle() + " (Parent: " + task1.getTitle() + ")");
        System.out.println("- " + subtask2.getTitle() + " (Parent: " + task1.getTitle() + ")");
        System.out.println("- " + subtask3.getTitle() + " (Parent: " + task2.getTitle() + ")\n");
        
        // Demo 4: Story Creation
        System.out.println("4. Story Creation:-");
        System.out.println("------------------");
        
        List<Task> storyTasks = Arrays.asList(task1, task2);
        Story story1 = taskService.createStory("Story for New game and Reskin", "Complete new feature and Reskin both in Q1 quarter", storyTasks, loggedInUser);
        System.out.println("Story created: " + story1.getTitle());
        System.out.println("Story contains " + story1.getTasks().size() + " tasks\n");
        
        // Demo 5: Task Updates
        System.out.println("5. Task Updates:-");
        System.out.println("----------------");
        
        taskService.updateTask(task1.getId(), "Do RivalMech", "Do Rivalmech instead of Partnermech", deadline1.plusDays(2));
        System.out.println("Task updated: " + task1);
        
        taskService.updateTaskStatus(task1.getId(), TaskStatus.IN_PROGRESS);
        System.out.println("Task status updated:- " + task1.getTitle() + " -> " + task1.getStatus());
        
        // Demo 6: Task Movement
        System.out.println("6. Task Movement:-");
        System.out.println("-----------------");
        
        taskService.moveTask(subtask1.getId(), task2.getId());
        System.out.println("Moved subtask '" + subtask1.getTitle() + "' from '" + task1.getTitle() + "' to '" + task2.getTitle() + "'\n");
        
        // Demo 7: Workload Analysis
        System.out.println("7. Workload Analysis:-");
        System.out.println("---------------------");
        
        WorkloadReport workload1 = taskService.getUserWorkload(loggedInUser.getId());
        WorkloadReport workload2 = taskService.getUserWorkload(user2.getId());
        
        System.out.println("Workload for " + loggedInUser.getUsername() + ":");
        System.out.println("- Total tasks: " + workload1.getTotalTasks());
        System.out.println("- Pending: " + workload1.getTasksByStatus().get(TaskStatus.PENDING));
        System.out.println("- In Progress: " + workload1.getTasksByStatus().get(TaskStatus.IN_PROGRESS));
        System.out.println("- Completed: " + workload1.getTasksByStatus().get(TaskStatus.COMPLETED));
        
        System.out.println("\nWorkload for " + user2.getUsername() + ":");
        System.out.println("- Total tasks: " + workload2.getTotalTasks());
        System.out.println("- Pending: " + workload2.getTasksByStatus().get(TaskStatus.PENDING));
        System.out.println("- In Progress: " + workload2.getTasksByStatus().get(TaskStatus.IN_PROGRESS));
        System.out.println("- Completed: " + workload2.getTasksByStatus().get(TaskStatus.COMPLETED));
        System.out.println();
        
        // Demo 8: Task Listing
        System.out.println("8. Task Listing:");
        System.out.println("----------------");
        
        List<Task> userTasks = taskService.getTasksByUser(loggedInUser.getId());
        System.out.println("Tasks assigned to " + loggedInUser.getUsername() + ":");
        for (Task task : userTasks) {
            System.out.println("- " + task.getTitle() + " (" + task.getStatus() + ")");
        }
        System.out.println();
        
        // Demo 9: Task Deletion
        System.out.println("9. Task Deletion:");
        System.out.println("-----------------");

        List<Task> remainingTasks = taskService.getTasksByUser(user3.getId());
        System.out.println("Remaining tasks for BEFORE " + user3.getUsername() + ": " + remainingTasks.size());
        taskService.deleteTask(task3.getId());
        System.out.println("Task deleted: " + task3.getTitle());
        
        remainingTasks = taskService.getTasksByUser(user3.getId());
        System.out.println("Remaining tasks for AFTER" + user3.getUsername() + ": " + remainingTasks.size());

        
        System.out.println("=== Demo Completed Successfully ===");
    }
}