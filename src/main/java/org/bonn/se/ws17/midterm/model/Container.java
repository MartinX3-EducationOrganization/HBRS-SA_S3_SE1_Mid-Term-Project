package org.bonn.se.ws17.midterm.model;

import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Container {
    private static final Container container = new Container();
    private final List<String> actors = new ArrayList<>();
    private final ConcurrentHashMap<String, UserStory> userStories = new ConcurrentHashMap<>();
    
    private Container() {
    }
    
    public static Container getContainer() {
        return container;
    }
    
    public List<UserStory> getUserStories() {
        return (List<UserStory>) userStories.values();
    }
    
    public List<UserStory> getUndoneUserStories() {
        return userStories.values().stream().filter(us -> !us.isDone()).collect(Collectors.toList());
    }
    
    public List<String> getActors() {
        return actors;
    }
    
    public UserStory get(String uuid) {
        return userStories.get(uuid);
    }
    
    public void addActor(String s) {
        actors.add(s);
    }
    
    public void add(UserStory us) throws ContainerException {
        if (!contains(us.getId())) {
            userStories.put(us.getId(), us);
        } else {
            throw new ContainerException(us.getId());
        }
    }
    
    public boolean contains(String usid) {
        return userStories.containsKey(usid);
    }
    
    public boolean containsActor(String s) { return actors.stream().anyMatch(x -> x.equals(s)); }
    
    public void delete(UUID uuid) throws NoSuchObjectException {
        if (userStories.remove(uuid) == null) {
            throw new NoSuchObjectException(String.format("Das UserStory-Objekt mit der ID [%s] existiert nicht!", uuid));
        }
    }
    
    public int size() {
        return userStories.size();
    }
    
    public void clear() {
        userStories.clear();
    }
}