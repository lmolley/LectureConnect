package Network;

import GUI.WaitingForStudents;

public class acceptThreads extends Thread
{
    Server server;
    WaitingForStudents waitingScreen;
    public acceptThreads(Server server_in, WaitingForStudents waitingScreen_in)
    {
        server = server_in;
        waitingScreen = waitingScreen_in;
    }
    public void run()
    {
        while(server.acceptingClients)
        {
            server.spinThread();
            waitingScreen.updateNumConnected();
        }
    }
}