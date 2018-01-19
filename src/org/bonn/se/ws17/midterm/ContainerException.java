package org.bonn.se.ws17.midterm;

import java.util.UUID;

class ContainerException extends Exception {
    ContainerException(UUID s) {
        super(String.format("Das UserStory-Objekt mit der ID [%s] ist bereits vorhanden!", s));
    }
}