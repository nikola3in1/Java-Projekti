package TEST;
//Example 26 (updated)

import Client.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * A chat server that delivers public and private messages.
 */
public class TrafficServer{

    // The server socket.
    private static ServerSocket serverSocket = null;
    // The client socket.
    private static Socket clientSocket = null;

    // This chat server can accept up to maxClientsCount clients' connections.
    private static final int maxClientsCount = 10;
    private static final clientThread[] threads = new clientThread[maxClientsCount];

    public static void main(String[] args) {

        // The default port number.
        int portNumber = 2222;
//        if (args.length < 1) {
            System.out.println("Usage: java ServerTEST <portNumber>\n"
                    + "Now using port number=" + portNumber);
//        } else {
//            portNumber = Integer.valueOf(args[0]).intValue();
//        }

    /*
     * Open a server socket on the portNumber (default 2222). Note that we can
     * not choose a port less than 1023 if we are not privileged users (root).
     */
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

    /*
     * Create a client socket for each connection and pass it to a new client
     * thread.
     */
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class clientThread extends Thread {

    private String room = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private Socket clientSocket = null;
    private final clientThread[] threads;
    private int maxClientsCount;

    public clientThread(Socket clientSocket, clientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        clientThread[] threads = this.threads;

        try {
      /*
       * Create input and output streams for this client.
       */
            is = new ObjectInputStream(clientSocket.getInputStream());
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            String name="";
            String img;
//            while (true) {
//                os.writeObject(new Packet("Enter your name."));
////                os.println("Enter your name.");
////                name = is.readLine().trim();
//                Packet p = (Packet) is.readObject();
//                name = p.msgtxt;
//                if (name.indexOf('@') == -1) {
//                    break;
//                } else {
////                    os.println("The name should not contain '@' character.");
//                    os.writeObject(new Packet("The name should not contain '@' character."));
//                }
//            }

      /* Welcome the new the client. */
//            os.println("Welcome " + name
//                    + " to our chat room.\nTo leave enter /quit in a new line.");
            Packet pack = new Packet("Welcome .");
            pack.serverMsg=true;
            os.writeObject(pack);
//            synchronized (this) {
//                for (int i = 0; i < maxClientsCount; i++) {
//                    if (threads[i] != null && threads[i] == this) {
//                        clientName = "@" + name;
//                        break;
//                    }
//                }
//                for (int i = 0; i < maxClientsCount; i++) {
//                    if (threads[i] != null && threads[i] != this) {
////                        threads[i].os.println("*** A new user " + name
////                                + " entered the chat room !!! ***");
//                        Packet packet = new Packet();
//                        packet.serverMsg = true;
//                        threads[i].os.writeObject(packet
//                        );
//                    }
//                }
//            }
      /* Start the conversation. */
            while (true) {
                Packet p = (Packet) is.readObject();
//                String line = is.readLine();
                String line = p.msgtxt;
                name = p.user;
                img = p.img;
                room = p.room;
                if (line.startsWith("/quit")) {
                    break;
                }

                if(room.equals("welcome")){
                    synchronized (this) {
                                for (int i = 0; i < maxClientsCount; i++) {
                                    if (threads[i] != null
                                            && threads[i].room != null
                                            && threads[i].room.equals("welcome")) {
                                        Packet packet = new Packet(line);
                                        packet.user=name;
                                        packet.img= img;
                                        packet.room=room;
                                        threads[i].os.writeObject(packet);
                                    }
                                }
                    }
                }
                else if(room.equals("games")){
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null
                                    && threads[i].room != null
                                    && threads[i].room.equals("games")) {
                                Packet packet = new Packet(line);
                                packet.user=name;
                                packet.img= img;
                                packet.room=room;
                                threads[i].os.writeObject(packet);
                            }
                        }
                    }
                }
                else if(room.equals("science")){
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null
                                    && threads[i].room != null
                                    && threads[i].room.equals("science")) {
                                Packet packet = new Packet(line);
                                packet.user=name;
                                packet.img= img;
                                packet.room=room;
                                threads[i].os.writeObject(packet);
                            }
                        }
                    }
                }
                else if(room.equals("music")){
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null
                                    && threads[i].room != null
                                    && threads[i].room.equals("music")) {
                                Packet packet = new Packet(line);
                                packet.user=name;
                                packet.img= img;
                                packet.room=room;
                                threads[i].os.writeObject(packet);
                            }
                        }
                    }
                }

            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].room == room) {
//                        threads[i].os.println("*** The user " + name
//                                + " is leaving the chat room !!! ***");
                        threads[i].os.writeObject(new Packet("*** The user " + name
                                + " is leaving the chat room !!! ***"));
                    }
                }
            }
//            os.println("*** Bye " + name + " ***");

            os.writeObject(new Packet("BYE"));
      /*
       * Clean up. Set the current thread variable to null so that a new client
       * could be accepted by the server.
       */
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
      /*
       * Close the output stream, close the input stream, close the socket.
       */
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}