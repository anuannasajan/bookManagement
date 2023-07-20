package com.edstem.ProjectManagement.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long id) {
        super("Task not found" +id);
    }
}
