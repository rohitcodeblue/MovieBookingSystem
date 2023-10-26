package com.project.moviebooking.email;

/**
 * Email priority enum used by email target
 *
 */
public enum EmailPriority {
    HIGH_PRIORITY(1),NORMAL_PRIORITY(3), LOW_PRIORITY(5);
    
    private EmailPriority(int priority) {
        this.priority = priority;
    }
    
    private int priority;
    
    public int getPriority() {
        return priority;
    }
    
}
