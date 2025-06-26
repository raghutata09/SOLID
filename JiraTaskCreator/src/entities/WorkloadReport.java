package entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import enums.TaskStatus;

public class WorkloadReport {
    private final long userId;
    private final int totalTasks;
    private final Map<TaskStatus, Integer> tasksByStatus;
    private final List<Task> overdueTasks;
    private final List<Task> upcomingDeadlines;
    
    public WorkloadReport(long userId, int totalTasks, Map<TaskStatus, Integer> tasksByStatus, 
                         List<Task> overdueTasks, List<Task> upcomingDeadlines) {
        this.userId = userId;
        this.totalTasks = totalTasks;
        this.tasksByStatus = new ConcurrentHashMap<>(tasksByStatus);
        this.overdueTasks = overdueTasks;
        this.upcomingDeadlines = upcomingDeadlines;
    }
    
    // Getters
    public long getUserId() {
        return userId;
    }
    
    public int getTotalTasks() {
        return totalTasks;
    }
    
    public Map<TaskStatus, Integer> getTasksByStatus() {
        return new ConcurrentHashMap<>(tasksByStatus);
    }
    
    public List<Task> getOverdueTasks() {
        return overdueTasks;
    }
    
    public List<Task> getUpcomingDeadlines() {
        return upcomingDeadlines;
    }
    
    public int getOverdueTaskCount() {
        return overdueTasks.size();
    }
    
    public int getUpcomingDeadlineCount() {
        return upcomingDeadlines.size();
    }
    
    public double getCompletionRate() {
        if (totalTasks == 0) {
            return 0.0;
        }
        
        Integer completedCount = tasksByStatus.get(TaskStatus.COMPLETED);
        int completed = completedCount != null ? completedCount : 0;
        
        return (double) completed / totalTasks * 100.0;
    }

    // We can have Enums also for Workload types
    public String getWorkloadLevel() {
        if (totalTasks == 0) {
            return "No Workload";
        } else if (totalTasks <= 3) {
            return "Light";
        } else if (totalTasks <= 7) {
            return "Moderate";
        } else if (totalTasks <= 12) {
            return "Heavy";
        } else {
            return "Critical";
        }
    }
    
    @Override
    public String toString() {
        return "WorkloadReport{userId=" + userId + ", totalTasks=" + totalTasks + 
               ", completionRate=" + String.format("%.1f", getCompletionRate()) + "%" +
               ", workloadLevel='" + getWorkloadLevel() + "'}";
    }
} 