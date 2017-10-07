package Client;

import java.io.Serializable;

public class Packet implements Serializable {
    public boolean newMsg=false;
    public boolean validateEmail=false;
    public boolean validateUser=false;
    public boolean addToDB=false;
    public boolean addedToDb=false;
    public boolean validatedUser = false;
    public boolean validatedEmail = false;
    public boolean authenticate=false;
    public boolean authenticated=false;
    public String img = "bot.jpg";
    public boolean serverMsg=false;
    public String room = "welcome";


    public boolean msgRequest=false;
    public String email_user = null;
    public String email = null;
    public String user = null;
    public String pass = null;
    public String msgtxt=null;

    public Packet() {
    }

    public Packet(String msgtxt) {

        this.msgtxt = msgtxt;
    }


}
