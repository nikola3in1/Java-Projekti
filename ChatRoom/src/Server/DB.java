package Server;

import java.sql.*;

public class DB {
    private static Connection con = null;
    private static String url = "jdbc:mysql://localhost:3306/ChatRoom";
    private static String user = "root";
    private static String pass = "n14031997";

    public static void connect() {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex){
//            System.out.println(ex.toString());
        }

    }

    public static void disconnect() {
        try {
            con.close();
        } catch (SQLException ex){}
    }


    public static boolean addUser(String email,String username,String password){
        connect();
        System.out.println(password);
        try{
            Statement st = con.createStatement();
            st.execute("INSERT INTO users (email,username,password) VALUES ('"
                    +email+"','"+username+"','"+password+"')");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return true;
    }

    public static boolean checkEmail(String email){
        boolean inBase=false;
        connect();
        try{
            String query="SELECT email FROM `users` WHERE email='"+email+"'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                System.out.println("its in the base");
                inBase=true;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return inBase;
    }
    public static boolean checkUser(String user){
        connect();
        boolean inBase=false;
        try{
            String query="SELECT username FROM `users` WHERE username='"+user+"'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()){
                inBase=true;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return inBase;
    }
    public static boolean authenticate(String email_user, String pass){
        connect();
        boolean inBase=false;

        String query = "";
        if(email_user.contains("@")){
            query="SELECT * FROM `users` WHERE email='"+email_user+"' AND "+"password='"+pass+"'";
        }else{
            query="SELECT * FROM `users` WHERE username='"+email_user+"' AND "+"password='"+pass+"'";
        }
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                inBase = true;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return inBase;
    }


}
