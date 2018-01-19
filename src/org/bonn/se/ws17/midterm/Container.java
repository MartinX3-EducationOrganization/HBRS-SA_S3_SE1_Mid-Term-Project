package org.bonn.se.ws17.midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    private List<UserStory> get() {
        return liste;
    }
    public void setListe(List<UserStory> liste) {
        this.liste = liste;
    }
    
    List<UserStory> listausgabe() {
        return liste;
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
    
    public void shell() throws ContainerException, IOException {
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
                System.out.print("Folgende Befehle stehen zur Verfuegung:" + "\n" +
                        "'enter' > Eingabe der UserStory" + "\n" +
                        "'store' > Speichert die UserStories auf den Datenträger." + "\n" +
                        "'load' > Ladet die UserStories vom Datenträger." + "\n" +
                        "'dump' > Sortiert die vorhanden UserStories und gibt diese aus." + "\n" +
                        "'help' > Zeigt alle Commandos an.");
            }
            
            if (strings[0].equals("dump")) {
                Container container = Container.getContainer();
                System.out.println("Userstories:");
                List<UserStory> list = container.get();
                Collections.sort(list);
                for (UserStory us : list) {
                    us.toString();
                }
            }
            
            if (strings[0].equals("load")) {
                Utils.load();
            }
            
            if (strings[0].equals("store")) {
                Utils.store();
            }
            
            if (strings[0].equals("enter")) {
                try {
                    Container.getContainer().add(EingabeUtil.eingabe());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    
}
