package org.bonn.se.ws17.midterm.controller;

import org.bonn.se.ws17.midterm.dto.UserStoryDTO;
import org.bonn.se.ws17.midterm.entity.UserStory;
import org.bonn.se.ws17.midterm.model.Container;
import org.bonn.se.ws17.midterm.utility.IOUtils;
import org.bonn.se.ws17.midterm.utility.InputUtils;
import org.bonn.se.ws17.midterm.utility.OutputUtils;
import org.bonn.se.ws17.midterm.view.OutputView;

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
            if (strings[0].equals("analyze")) {
                OutputUtils.analyze(strings[1]);
            }
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
            if (strings[0].equals("exit")) {
                break;
            }
        }
    }
}
