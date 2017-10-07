package Server;

import Client.Packet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    public static ArrayList<ClientThread> clients;
    public static CopyOnWriteArrayList<String> msgs;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectInputStream in = null;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(3000);
            System.out.println("Listenning...");

            while(true) {
                socket = serverSocket.accept();
                System.out.println(socket.getLocalPort() + " " + socket.getPort());
                if (socket.isConnected()) {
                    ClientThread thread = new ClientThread(socket);
                    thread.start();
                    clients.add(thread);
                }
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ClientThread extends Thread {
    public Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            Packet packet;

                if (socket.isConnected()) {
                packet = (Packet) in.readObject();
                    System.out.println(packet.msgRequest+" request");

                if (packet.validateUser && !packet.validateEmail) {
                    Packet toSend = new Packet();
                    if (DB.checkUser(packet.user)) {
                        toSend.validatedUser = false;
                    } else {
                        toSend.validatedUser = true;
                    }
                    out.writeObject(toSend);
                } else if (packet.validateEmail && !packet.validateUser) {
                    Packet toSend = new Packet();
                    if (DB.checkEmail(packet.email)) {
                        toSend.validatedEmail = false;
                    } else {
                        toSend.validatedEmail = true;
                    }
                    out.writeObject(toSend);
                } else if (packet.authenticate) {
                    System.out.println(packet.toString());
                    Packet toSend = new Packet();
                    if (DB.authenticate(packet.email_user, Util.getHash(packet.pass))) {
                        toSend.authenticated = true;
                    }
                    out.writeObject(toSend);
                } else if (packet.addToDB) {
                    Packet toSend = new Packet();
                    String hashedPass = Util.getHash(packet.pass);
                    if (DB.addUser(packet.email, packet.user, hashedPass)) {
                        toSend.addedToDb = true;
                    }
                    out.writeObject(toSend);
                } else if (packet.msgRequest) {
                }
                in.close();
                out.close();
                socket.close();
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









