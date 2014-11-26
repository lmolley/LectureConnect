package Network;

import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import GUI.StudentMainGUI;


public class Client implements Runnable{
    String host, timeStamp;
    int port;
    public String name;
    public String uniqname;
    public int ID;
    ArrayList<BufferedImage> lectureSlideList;
    private StudentMainGUI mainGUI;
    
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
            new Thread(this).start();
        } 
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void run()
    {
      try
      {
        while(true)   
        {
          String message = (String) objectIn.readObject();
          //System.out.println(message);
          mainGUI.ta.append(message+"\n");  
        }
      }
      catch(Exception e)
      {
        System.out.println(e);
      }
    }
    
    public void processMessage(String message)
    {
      try
      {
        sendRequest(8);
        objectOut.writeObject(name+": "+message);        
        mainGUI.tf.setText("");
      }
      catch(IOException ie)
      {
        System.out.println("76");
        System.out.println(ie);
      }
    }
    
    public void setGUI(StudentMainGUI inGUI)
    {
      mainGUI = inGUI;
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
    
    public void sendName(){
        sendRequest(2);
        try{
            objectOut.writeObject(name);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendUniq(){
        sendRequest(3);
        try{
            objectOut.writeObject(uniqname);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendUMID(){
        sendRequest(4);
        try{
            objectOut.writeObject(ID);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendNumCorrect(int i){
        sendRequest(5);
        try{
            objectOut.writeObject(i);
        }
        catch(Exception e){
            e.printStackTrace();  
        }
    }
    
    public void sendNumQuestions(int i){
        sendRequest(6);
        try{
            objectOut.writeObject(i);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void updateLectureSlideContainer(int i){
        sendRequest(7);
        try{
            lectureSlideList = (ArrayList<BufferedImage>) objectIn.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void closeConnection(){
        try {
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}