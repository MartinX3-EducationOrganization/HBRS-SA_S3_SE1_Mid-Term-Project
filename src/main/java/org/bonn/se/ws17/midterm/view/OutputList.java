package org.bonn.se.ws17.midterm.view;

import org.bonn.se.ws17.midterm.dto.UserStoryDTO;

import java.util.List;

public interface OutputList {
    void display(List<UserStoryDTO> list);
}
