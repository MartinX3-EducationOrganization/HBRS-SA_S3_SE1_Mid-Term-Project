package org.bonn.se.ws17.midterm;

import java.rmi.NoSuchObjectException;
import java.util.*;

public class Container {
    private static Container container;
    private List<UserStory> liste;
    
    private Container() {
        changeListType(Modus.LIST_TYPE_ARRAY);
    }
    
    public static Container getContainer() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }
    
    private void changeListType(Modus modus) {
        switch (modus) {
            case LIST_TYPE_ARRAY:
                liste = new ArrayList<>();
                break;
            
            case LIST_TYPE_LINKED:
                liste = new LinkedList<>();
                break;
            
            default:
                throw new NoSuchElementException("Modus [ " + modus + " ] wurde nicht imnplementiert.");
        }
    }
    
    public UserStory get(UUID id) {
        Optional<UserStory> vorhanden = liste.stream().filter(entry -> entry.getId() == id).findFirst();
        if (vorhanden.isPresent()) {
            UserStory entry = vorhanden.get();
            return vorhanden.get();
        }
        return null;
    }
    
    public void add(UserStory us) throws ContainerException {
        if (!idVorhanden(us.getId())) {
            liste.add(us);
        } else {
            throw new ContainerException(String.format("Das UserStory-Objekt mit der ID [%s] ist bereits vorhanden!", us.getId()));
        }
        
    }
    
    private boolean idVorhanden(UUID id) {
        return liste.stream().anyMatch(x -> x.getId().equals(id));
    }
    
    public void delete(UUID id) throws NoSuchObjectException {
        Optional<UserStory> us = liste.stream().filter(entry -> entry.getId() == id).findFirst();
        if (us.isPresent()) {
            UserStory entry = us.get();
            liste.remove(us);
        }
        throw new NoSuchObjectException(String.format("Das UserStory-Objekt mit der ID [%s] existiert nicht!", id));
    }
    
    public void dump() {
        for (UserStory us : liste) {
            System.out.println(us.toString("id"));
        }
    }
    
    public int anzahl() {
        return liste.size();
    }
    
    public void getall() {
        for (int i = 0; i < liste.toArray().length; i++) {
            System.out.println(liste.get(i));
            if (i < (liste.toArray().length - 1)) {
                System.out.println("---------------------------------------------------------");
            }
        }
    }
}
