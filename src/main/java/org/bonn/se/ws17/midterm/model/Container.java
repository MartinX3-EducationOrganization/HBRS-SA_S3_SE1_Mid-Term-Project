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
        return Container.container;
    }
    
    public List<UserStory> getUserStories(boolean onlyUndone) {
        return userStories.values().stream().filter(us -> !onlyUndone || !us.isDone()).collect(Collectors.toList());
    }
    
    public List<String> getActors() {
        return actors;
    }
    
    public UserStory getUS(String uuid) {
        return userStories.get(uuid);
    }
    
    public String addActor(String actorKey) {
        actors.add(actorKey);
        return actorKey;
    }
    
    public void addUS(UserStory us) throws ContainerException {
        if (!containsUS(us.getId())) {
            userStories.put(us.getId(), us);
        } else {
            throw new ContainerException(us.getId());
        }
    }
    
    public boolean containsUS(String usid) {
        return userStories.containsKey(usid);
    }
    
    public boolean containsActor(String s) { return actors.stream().noneMatch(x -> x.equals(s)); }
    
    public void removeUS(String usid) {
        userStories.remove(usid);
    }
    
    public void removeActor(String name) { actors.remove(name);}
    
    public int size() {
        return userStories.size();
    }
    
    public void clearUS() {
        userStories.clear();
    }
    
    public void addHistory(Command cmd) {
        history.add(cmd);
    }
    
    public void undoHistory() {
        if (history.isEmpty()) {
            System.out.println("nothing to undo.");
        } else {
            history.pop().undo();
        }
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