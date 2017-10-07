package Server;

import Client.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficServer {

    public static ArrayList<Socket> clients;
    public static Lock lock = new ReentrantLock();

    public static String getMsg() {

        return msg;

    }

    public static String msg = "";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = null;
            ObjectOutputStream out = null;
            serverSocket = new ServerSocket(3333);
            clients = new ArrayList<>();
            System.out.println("Listening... on 3333");
            while (true) {
                System.out.println(clients.size()+" : SIZE");
                Socket socket = serverSocket.accept();
                if (socket.isConnected()) {
                    System.out.println(socket.getPort());
                    clients.add(socket);
                    ForwardTraffic forwardTraffic = new ForwardTraffic(clients);
                    forwardTraffic.start();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ForwardTraffic extends Thread {
    ArrayList<Socket> clients;
    public Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    String msg = null;
    public ForwardTraffic(ArrayList<Socket> clients) {
        this.clients = clients;
    }

    public void run(){
        try{
            System.out.println(clients.size());
            for(int i =0; i < clients.size();i++){
                System.out.println(clients.get(i).getPort() +"<....");
//                if (!clients.get(i).isClosed()){
                    in= new ObjectInputStream(clients.get(i).getInputStream());
                    out = new ObjectOutputStream(clients.get(i).getOutputStream());
                    Packet packet;
                    packet = (Packet) in.readObject();
                    System.out.println(packet.msgtxt+" "+clients.get(i).getPort());
                    out.writeObject(packet);
//                    out.close();
//                    in.close();
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
