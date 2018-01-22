package org.bonn.se.ws17.midterm.exception;

public class ContainerException extends Exception {
    public ContainerException(String usid) {
        super(String.format("Das UserStory-Objekt mit der ID [%s] ist bereits vorhanden!", usid));
    }
}