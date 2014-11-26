package Network;

import java.net.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Network.ServerThread;
//testing push
public class Server{
		public boolean acceptingClients = true;
		
    private ServerSocket serverSocket;
    final private int port = 9001;
    private Socket connectionSocket;
    
    private String timeStamp;
    private String entireQuizFile;
    
    int ID;
    //streams
    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;
    
    //threads
    private ArrayList<Thread> threads = new ArrayList<Thread>();
    private ArrayList<ServerThread> serverThreads = new ArrayList<ServerThread>();
    
    public Server(){
        ID = 0;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server now listening on port: " + port);
        }
        catch (Exception e){
            System.out.println(e);
        }  
    }
    
    public void spinThread(){
        try{
            connectionSocket = serverSocket.accept();
            ServerThread st = new ServerThread(connectionSocket, ID++);
            Runnable runnable = st;
            Thread thread = new Thread(runnable);
            threads.add(thread);
            serverThreads.add(st);
            thread.start();
        }
        catch (Exception e){
            System.out.println(e);
        }  
    }
    
    public void pushQuizToThreads(String s){
        if(threads.isEmpty())
            return;
        for(ServerThread st : serverThreads){
            st.setEntireQuizFile(s);
        }
    }
    
    public void pushExistingQuizToThreads(){
        if(threads.isEmpty())
            return;
        for(ServerThread st : serverThreads){
            st.setEntireQuizFile(entireQuizFile);
        }
    }
    
    public void loadQuizToThreads(JFrame mainFrame){
        File myFile;
        String content = "not initialized";
        
        JFileChooser loadChooser = new JFileChooser("Load Quiz File");
        loadChooser.setMultiSelectionEnabled(false);
        int approve = loadChooser.showOpenDialog(mainFrame);
        if (approve == JFileChooser.APPROVE_OPTION){
            myFile = loadChooser.getSelectedFile();
            try {
                Scanner fileScanner = new Scanner(myFile);
                content = fileScanner.useDelimiter("\\Z").next();
                fileScanner.close();
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File ptr is null");
            }
           
        }
        entireQuizFile = content;
    }
    
    public String getIP()
    {
    	try
      {
	      return InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e)
      {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      return "";
      }
    }
    
    public int numConnected()
    {
    	return threads.size();
    }
    
    public void pushLectureToThreads(List<BufferedImage> lecture_in){
        if (threads.isEmpty())
            return;
        for (ServerThread st : serverThreads){
            st.lecture = lecture_in;
        }
    }
    
    
}


