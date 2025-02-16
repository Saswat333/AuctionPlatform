package org.project.exceptions;

public class EntityDoesNotExistException extends Exception{
    public EntityDoesNotExistException(String entityName, String entityValue) {
        super(entityName + ": " + entityValue + " do not exist");
    }
}
