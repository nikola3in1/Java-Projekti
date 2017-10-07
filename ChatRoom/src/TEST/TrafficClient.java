package TEST;

import Client.ChatPage;
import Client.Packet;
import Client.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TrafficClient implements Runnable {

    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static ObjectOutputStream os = null;
    // The input stream
    private static ObjectInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    public static String msg = "";
    public static boolean newMsg = false;


    public static   void main(String[] args) {

        // The default port.
        int portNumber = 2222;
        // The default host.
        String host = "localhost";



    /*
     * Open a socket on a given host and port. Open input and output streams.
     */
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }

    /*
     * If everything has been initialized then we want to write some data to the
     * socket we have opened a connection to on the port portNumber.
     */
        if (clientSocket != null && os != null && is != null) {
            try {

        /* Create a thread to read from the server. */
                new Thread(new TrafficClient()).start();
                while (!closed) {




                    ChatPage.logout.setOnMouseClicked(mouseEvent -> {
                        try{
                            os.writeObject(new Packet("/quit"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Platform.exit();
                    });


                    ChatPage.send.setOnMouseClicked(mouseEvent -> {
                        try {
                            if(!ChatPage.input.getText().trim().equals("")&& ChatPage.input.getText()!=null){
                                System.out.println(ChatPage.input.getText()+" ,");
                            Packet packet = new Packet(ChatPage.input.getText().trim());
                            packet.user = User.user;
                            packet.img = User.img;
                                packet.room = User.room;
                            os.writeObject(packet);
                            ChatPage.input.setText("");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                   });
                    ChatPage.input.setOnKeyPressed(keyEvent -> {
                        if(keyEvent.getCode()== KeyCode.ENTER){
                            try {
                                if(!ChatPage.input.getText().trim().equals("") && ChatPage.input.getText()!=null) {

                                    Packet packet = new Packet(ChatPage.input.getText().trim());
                                    packet.user = User.user;
                                    packet.img = User.img;
                                    packet.room = User.room;


                                    //packet.user picture
                                    os.writeObject(packet);
                                }
                                ChatPage.input.setText("");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        ChatPage.scroll.setVvalue(Double.MAX_VALUE);
                    });



                    ChatPage.mainRoom.setOnMouseClicked(mouseEvent -> {
                        User.room = "welcome";
                        try {
                            Packet p = new Packet(User.user+" joined room");
                            p.serverMsg=true;

                            os.writeObject(p);
                            ChatPage.msgs.getChildren().clear();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    ChatPage.gamesRoom.setOnMouseClicked(mouseEvent -> {
                        User.room = "games";
                        try {
                            Packet p = new Packet(User.user+" joined room");
                            p.serverMsg=true;

                            os.writeObject(p);
                            ChatPage.msgs.getChildren().clear();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("games");

                    });
                    ChatPage.scienceRoom.setOnMouseClicked(mouseEvent -> {
                        System.out.println("science");
                        try {
                            Packet p = new Packet(User.user+" joined room");
                            p.serverMsg=true;

                            os.writeObject(p);
                            ChatPage.msgs.getChildren().clear();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        User.room = "science";
                    });
                    ChatPage.musicRoom.setOnMouseClicked(mouseEvent -> {
                        System.out.println("music");
                        try {
                            Packet p = new Packet(User.user+" joined room");
                            p.serverMsg=true;

                            os.writeObject(p);
                            ChatPage.msgs.getChildren().clear();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        User.room = "music";
                    });




                }
        /*
         * Close the output stream, close the input stream, close the socket.
         */
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    /*
     * Create a thread to read from the server. (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */

    public void run() {
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
        String responseLine;
        try {
            while (true) {
                Packet p = (Packet) is.readObject();
                if(p.msgtxt.equals("BYE")){
                    closed=true;
                }
                if ((responseLine = p.msgtxt) != null) {
                    System.out.println(responseLine);


                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            ChatPage.msgs.setMaxWidth(200);
                            HBox textFields = new HBox();
                            textFields.setStyle("-fx-background-color: azure; -fx-border-color: #429eff; -fx-border-radius: 5px");
                            textFields.setPadding(new Insets(4,5,2,5));
                            //46 je granica za karaktere


                            String formatedMsg = "";
                            if(!p.serverMsg) {
                                ImageView img = new ImageView();
                                img.setFitWidth(25);
                                img.setFitHeight(25);
                                img.setSmooth(true);
                                img.setStyle("-fx-background-radius: 5px;  -fx-border-radius: 5px");
                                img.setImage(new Image(p.img));
                                Text user = new Text(p.user + ": ");

                                String msgtext;
                                if(p.msgtxt.length()>19){
                                    msgtext=p.msgtxt.substring(0,19);
                                    for(int i =35;i<p.msgtxt.length();){

                                        if(i*2<p.msgtxt.length()) {
                                            formatedMsg += p.msgtxt.substring(i, i+33) + "\n";
                                        }

                                        if(i+19<p.msgtxt.length()){
                                            i += 19;
                                        }else{
                                            formatedMsg += p.msgtxt.substring(i,p.msgtxt.length())+"\n";
                                            break;
                                        }
                                    }
                                }else{
                                    msgtext = p.msgtxt;
                                }
                                Text msgtxt = new Text(msgtext);
                                user.setFill(Color.web("darkblue"));
                                textFields.getChildren().addAll(user,msgtxt);
                                HBox msg = new HBox();
                                msg.setSpacing(5);
                                msg.setPadding(new Insets(2,2,2,2));
                                msg.getChildren().addAll(img, textFields);
                                ChatPage.msgs.getChildren().add(msg);
                                ChatPage.scroll.setVvalue(Double.MAX_VALUE);
                            }else{
                                HBox msg = new HBox();
                                msg.getChildren().add(new Text(p.msgtxt));
                                ChatPage.msgs.getChildren().add(msg);
                            }


                            if(formatedMsg.length()>0){
                                HBox msg = new HBox();
                                msg.setPadding(new Insets(2,2,0,2));
                                msg.setStyle("-fx-background-color: azure; -fx-border-color: #429eff; -fx-border-radius: 5px");
                                msg.getChildren().add(new Text(formatedMsg));
                                ChatPage.msgs.getChildren().add(msg);
                            }

                            ChatPage.scroll.setVvalue(Double.MAX_VALUE);

                        }
                    });

                    if (responseLine.indexOf("*** Bye") != -1)
                        break;
                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}