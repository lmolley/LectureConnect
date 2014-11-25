package Network;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
    String host, timeStamp;
    int port;
    public String name;
    public String uniqname;
    public int ID;
    public int score = 0;
    public int num_questions = 0;
    
    InetAddress addr;
    Socket connectionSocket;
    
    
    ObjectOutputStream objectOut;
    ObjectInputStream objectIn;
    
    public Client(String host_in, String name_in, String uniq_in, int ID_in){
        //DEFINE HOSTNAME AND PORT NUM
        host = host_in;
        port = 9001;
        name = name_in;
        uniqname = uniq_in;
        ID = ID_in;
        
        try{
            addr = InetAddress.getByName(host);
            connectionSocket = new Socket(addr, port);
        
            objectOut = new ObjectOutputStream(connectionSocket.getOutputStream());
            objectIn = new ObjectInputStream(connectionSocket.getInputStream());
        
        } 
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void sendRequest(int reqNum){
        try {
            objectOut.writeObject(reqNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public String getQuizString(){
        sendRequest(0);
        String quizString = "not initialized";
        try {
            quizString = (String) objectIn.readObject();
        } 
        catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return quizString;
    }
    
    public String getTestString(){
        sendRequest(1);
        String string = "not initialized";
        try {
            string = (String) objectIn.readObject();
        } 
        catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return string;
    }
    
    public void closeConnection(){
        try {
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}