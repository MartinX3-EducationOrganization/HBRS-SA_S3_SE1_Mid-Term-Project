package org.bonn.se.ws17.midterm.Controller;

import View.OutputView;
import org.bonn.se.ws17.midterm.DTO.UserStoryDTO;
import org.bonn.se.ws17.midterm.Entity.UserStory;
import org.bonn.se.ws17.midterm.Model.Container;
import org.bonn.se.ws17.midterm.Utility.IOUtils;
import org.bonn.se.ws17.midterm.Utility.InputUtils;
import org.bonn.se.ws17.midterm.Utility.OutputUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    
    private void dump(boolean b) {
        Container container = Container.getContainer();
        List<UserStory> liste;
        System.out.println("Userstories:");
        if (b) {
            liste = container.getList();
        } else {
            liste = container.getList().stream().filter(x -> !x.isDone()).collect(Collectors.toList());
        }
        List<UserStoryDTO> listDTO = new ArrayList<>();
        liste.forEach(entity -> {
            UserStoryDTO dto = new UserStoryDTO(entity);
            listDTO.add(dto);
        });
        new OutputView().display(listDTO);
    }
    
    public void anfang() {
        OutputUtils.welcome();
        String strInput = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                strInput = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            String[] strings = strInput.split(" ");
            if (strings[0].equals("help")) {
                OutputUtils.help();
            }
            
            if (strings[0].equals("dump")) {
                dump(true);
            }
            
            if (strings[0].equals("dumpNotDone")) {
                dump(false);
                
            }
            if (strings[0].equals("load")) {
                IOUtils.load();
                
            }
            
            if (strings[0].equals("store")) {
                IOUtils.store();
            }
            
            if (strings[0].equals("enter")) {
                try {
                    InputUtils.eingabe();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    }
}
