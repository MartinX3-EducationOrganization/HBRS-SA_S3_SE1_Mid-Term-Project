package org.bonn.se.ws17.midterm.view;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal implements Closeable {
    private final BufferedReader bufInput;
    private final InputStreamReader input;
    
    public Terminal() {
        input = new InputStreamReader(System.in);
        bufInput = new BufferedReader(input);
    }
    
    public String readLine() {
        System.out.print("> ");
        try {
            return bufInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void close() throws IOException {
        bufInput.close();
        input.close();
    }
}
