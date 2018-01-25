package org.bonn.se.ws17.midterm.command;

import org.bonn.se.ws17.midterm.dto.UserStoryDTO;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.view.OutputView;

import java.util.stream.Collectors;

public class Dump implements Command {
    @Override
    public void execute(String[] params) {
        new OutputView().display(Container.getContainer().getUserStories(false).stream().map(UserStoryDTO::new).collect(Collectors.toList()));
    }
    
    @Override
    public void undo() {
    }
    
    @Override
    public Command clone() {
        return null;
    }
}