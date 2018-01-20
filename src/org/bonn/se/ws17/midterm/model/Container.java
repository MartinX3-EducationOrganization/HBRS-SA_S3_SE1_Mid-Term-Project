package org.bonn.se.ws17.midterm.model;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.enumerator.Modus;
import org.bonn.se.ws17.midterm.exception.ContainerException;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Container {
    private static final Container container = new Container();
    private List<UserStory> liste;
    
    private Container() {
        changeListType(Modus.LIST_TYPE_ARRAY);
    }
    
    public static Container getContainer() {
        return container;
    }
    
    public List<UserStory> getList() {
        return liste;
    }
    
    private void changeListType(Modus modus) {
        switch (modus) {
            case LIST_TYPE_ARRAY:
                liste = new ArrayList<>();
                break;
            
            case LIST_TYPE_LINKED:
                liste = new LinkedList<>();
                break;
            
            default:
                throw new NoSuchElementException("Modus [ " + modus + " ] wurde nicht implementiert.");
        }
    }
    
    public UserStory get(UUID id) {
        Optional<UserStory> vorhanden = liste.stream().filter(entry -> entry.getId() == id).findFirst();
        if (vorhanden.isPresent()) {
            UserStory entry = vorhanden.get();
            return vorhanden.get();
        }
        return null;
    }
    
    public void add(UserStory us) throws ContainerException {
    
        if (!idVorhanden(us.getId())) {
            liste.add(us);
        } else {
            throw new ContainerException(us.getId());
        }
    }
    
    public boolean idVorhanden(UUID id) {
        return liste.stream().anyMatch(x -> x.getId().equals(id));
    }
    
    public void delete(UUID id) throws NoSuchObjectException {
        Optional<UserStory> us = liste.stream().filter(entry -> entry.getId() == id).findFirst();
        if (us.isPresent()) {
            UserStory entry = us.get();
            liste.remove(us);
        } else {
            throw new NoSuchObjectException(String.format("Das UserStory-Objekt mit der ID [%s] existiert nicht!", id));
        }
    }
    
    public int size() {
        return liste.size();
    }
    
    public void clear() {
        liste.clear();
    }
}