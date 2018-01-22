package org.bonn.se.ws17.midterm.model;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.enumerator.Modus;
import org.bonn.se.ws17.midterm.exception.ContainerException;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Container {
    private static final Container container = new Container();
    private List<String> actorList;
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
    
    public List<String> getActorList() {
        return actorList;
    }
    
    private void changeListType(Modus modus) {
        switch (modus) {
            case LIST_TYPE_ARRAY:
                liste = new ArrayList<>();
                actorList = new ArrayList<>();
                break;
            
            case LIST_TYPE_LINKED:
                liste = new LinkedList<>();
                actorList = new LinkedList<>();
                break;
            
            default:
                throw new NoSuchElementException("Modus [ " + modus + " ] wurde nicht implementiert.");
        }
    }
    
    public UserStory get(UUID uuid) {
        Optional<UserStory> us = liste.stream().filter(entry -> entry.getId().equals(uuid)).findAny();
        if (us.isPresent()) {
            return us.get();
        } else {
            return null;
        }
    }
    
    public void addActor(String s) {
        actorList.add(s);
    }
    
    public void add(UserStory us) throws ContainerException {
        if (!contains(us.getId())) {
            liste.add(us);
        } else {
            throw new ContainerException(us.getId());
        }
    }
    
    public boolean contains(UUID id) {
        return liste.stream().anyMatch(x -> x.getId().equals(id));
    }
    
    public boolean containsActor(String s) { return actorList.stream().anyMatch(x -> x.equals(s)); }
    
    public void delete(UUID uuid) throws NoSuchObjectException {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getId().equals(uuid)) {
                liste.remove(i);
                return;
            }
            throw new NoSuchObjectException(String.format("Das UserStory-Objekt mit der ID [%s] existiert nicht!", uuid));
        }
    }
    
    public int size() {
        return liste.size();
    }
    
    public void clear() {
        liste.clear();
    }
}