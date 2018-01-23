package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.dto.UserStoryDTO;
import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class DumpCommand implements Command {
    
    @Override
    public void execute() {
        
        Container container = Container.getContainer();
        List<UserStory> userStories = container.getUserStories();
        System.out.println("Userstories:");
        new OutputView().display(userStories.stream().map(us -> new UserStoryDTO(us)).collect(Collectors.toList()));
    }
    
    
    @Override
    public void undo() {
    
    }
    
    @Override
    public Command clone() throws CloneNotSupportedException {
        return null;
    }
}