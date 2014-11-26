package Network;

import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

class ServerThread extends Thread{
    Socket connectionSocket;
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    ArrayList<BufferedImage> lecture;
    Server server;
    int studentID;
    int numCorrect = 0, numQuestions = 0;
    String studentName, studentUniq;
    
    
    //this is the only thing to be sent as of now
    //slides will also be sent eventually
    String entireQuizFile;
    int ID;
    
    ServerThread(Socket s, int ID_in, Server in_server){
        ID = ID_in;
        connectionSocket = s;
        entireQuizFile = "";
        server = in_server;
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
            case 2://receive name
                studentName = (String) objectIn.readObject();
                break;
            case 3://receive uniq
                studentUniq = (String) objectIn.readObject();
                break;
            case 4://receive ID
                studentID = (Integer) objectIn.readObject();
                break;
            case 5://receive numCorrect
                numCorrect = (Integer) objectIn.readObject();
                break;
            case 6://receive numQuestions
                numQuestions = (Integer) objectIn.readObject();
                break;
            case 7://push lecture slides
                objectOut.writeObject(lecture);
                break;
            case 8:
              String message = (String) objectIn.readObject();
              server.sendToAll(message);
              break;
            default:
              System.out.println("Error, reached default clause");
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