package Network;

import java.net.*;
import java.io.*;
import java.util.*;

class ServerThread extends Thread{
    Socket connectionSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    
    //this is the only thing to be sent as of now
    //slides will also be sent eventually
    String entireQuizFile;
    int ID;
    
    ServerThread(Socket s, int ID_in){
        ID = ID_in;
        connectionSocket = s;
        entireQuizFile = "";
        try{
            objectIn = new ObjectInputStream(s.getInputStream());
            objectOut = new ObjectOutputStream(s.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    @Override
    public void run() {
        while(true){
            handleRequest();
        }
        
    }
    
    
    private void handleRequest(){
        try {
            int request = (Integer)objectIn.readObject();
            
            switch (request){
            case 0:
                objectOut.writeObject(entireQuizFile);
                break;
                
            case 1:
                objectOut.writeObject("test String from Server");
                break;
            }//switch
        }//try 
 
        catch (Exception e) {
            return;
        }//catch
    }//handleRequest
    
    
    
    public void setEntireQuizFile(String s){
        entireQuizFile = s;
    }
    
    public void closeIt(){
        try {
            connectionSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}