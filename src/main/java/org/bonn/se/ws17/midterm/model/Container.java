package org.bonn.se.ws17.midterm.model;

import org.bonn.se.ws17.midterm.command.Command;
import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.exception.ContainerException;
import org.bonn.se.ws17.midterm.utility.ErrorUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Container {
    private static final Container container = new Container();
    private final Stack<Command> history = new Stack<>();
    private final List<String> actors = new ArrayList<>();
    private final ConcurrentHashMap<String, UserStory> userStories = new ConcurrentHashMap<>();
    private final HashMap<String, Command> commands = new HashMap<>();
    
    private Container() {
    }
    
    public static Container getContainer() {
        return container;
    }
    
    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories.values());
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
    
    public void addUS(UserStory us) throws ContainerException {
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
    
    public void removeUS(String usid) {
        userStories.remove(usid);
    }
    
    public int size() {
        return userStories.size();
    }
    
    public void clear() {
        userStories.clear();
    }
    
    public void addHistory(Command cmd) {
        history.add(cmd);
    }
    
    public void undoHistory() {
        history.pop().undo();
    }
    
    public Set<String> getCommands() {
        return commands.keySet();
    }
    
    public void addCommand(String cmdKey, Command cmdObject) {
        commands.put(cmdKey, cmdObject);
    }
    
    public Command getCommand(String cmdKey) {
        Command cmd = commands.get(cmdKey);
        if (cmd == null) {
            ErrorUtils.cmdNotFound(cmdKey);
            return commands.get("help");
        }
        return cmd;
    }
}