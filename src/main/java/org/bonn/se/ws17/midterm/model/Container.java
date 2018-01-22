package org.bonn.se.ws17.midterm.model;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Container {
    private static final Container container = new Container();
    private final List<String> actorList = new ArrayList<>();
    private final List<UserStory> liste = new ArrayList<>();
    
    private Container() {
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
    
    public UserStory get(String uuid) {
        return liste.stream().filter(entry -> entry.getId().equals(uuid)).findAny().orElse(null);
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
    
    public boolean contains(String usid) {
        return liste.stream().anyMatch(x -> x.getId().equals(usid));
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