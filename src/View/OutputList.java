package View;

import org.bonn.se.ws17.midterm.DTO.UserStoryDTO;

import java.util.List;

public interface OutputList {
    void display(List<UserStoryDTO> list);
}
