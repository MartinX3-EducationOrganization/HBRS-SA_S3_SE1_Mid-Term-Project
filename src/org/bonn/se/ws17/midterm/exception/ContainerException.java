package org.bonn.se.ws17.midterm.exception;

import java.util.UUID;

public class ContainerException extends Exception {
    public ContainerException(UUID s) {
        super(String.format("Das UserStory-Objekt mit der ID [%s] ist bereits vorhanden!", s));
    }
}