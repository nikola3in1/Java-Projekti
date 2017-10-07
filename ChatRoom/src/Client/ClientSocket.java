package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

    public static Socket socket = null;
    public static ObjectOutputStream out = null;

    public ClientSocket() {
    }



    public static void sendMessage(Packet packet){
        try{
            Socket socket2 = new Socket("localhost", 3333);
            ObjectOutputStream out = new ObjectOutputStream(socket2.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket2.getInputStream());
            out.writeObject(packet);
            Packet p = (Packet) in.readObject();
            System.out.println(socket2.getLocalPort());
            System.out.println(p.msgtxt);
//
//            in.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static boolean sendPackets(Packet packet, int mode) {
        ObjectInputStream in;
        try {

            socket = new Socket("localhost", 3000);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(packet);
            System.out.println(packet.msgRequest+" request");

            in = new ObjectInputStream(socket.getInputStream());

            Packet p = (Packet) in.readObject();
            System.out.println(p.toString());

            out.close();
            in.close();
            socket.close();
            switch (mode) {
                case 0:
                    //msgs
//                    System.out.println("successfully sent");
                    System.out.println(p.msgRequest+" msgrequest");
                    ClientMsgHandler cmh = new ClientMsgHandler();
                    cmh.start();

                case 1:
                    //check user
                    if (p.validatedUser) {
                        return true;
                    } else {
                        return false;
                    }
                case 2:
                    //check email
                    if (p.validatedEmail) {
                        return true;
                    } else {
                        return false;
                    }
                case 3:
                    //authenticate
                    if (p.authenticated) {
                        return true;
                    } else {
                        return false;
                    }
                case 4:
                    //addToDb
                    return true;
                default:
                    if (p.newMsg) {
                        System.out.println(p.msgtxt);
                    }
                    break;

            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

}




