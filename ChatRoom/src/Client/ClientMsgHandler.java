package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMsgHandler extends Thread{
    public static boolean running=true;
        public static Socket socket = null;
    public void run() {
        System.out.println("listenning... on port:3334");
        try{
            socket = new Socket("localhost",3333);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Packet p = (Packet) in.readObject();
            System.out.println(p.user+" "+p.msgtxt);
            in.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*ObjectOutputStream out;
        ObjectInputStream in = null;
        try {
            socket = new Socket("localhost", 3000);
            User.socket = socket;
            System.out.println("msglistener: "+socket.getPort()+" "+socket.getLocalPort());
            while(running){
                in = new ObjectInputStream(socket.getInputStream());
                Packet p = (Packet) in.readObject();
                System.out.println(p.msgtxt);
            }
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }
}
