import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nikola3in1 on 19.2.17..
 */

public class App {
    String username = "empty";
    public static void main(String[] args) throws IOException, InterruptedException {
        new App();
    }

    public App() throws IOException, InterruptedException {
        FTPConnection ftp = new FTPConnection();
        Credentials credentials = new Credentials();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to DreamboxAuthenticator");
        System.out.print("Enter the server ip adress: ");
        ftp.setServer(scanner.next());
        System.out.print("Enter the username: ");
        ftp.setUser(scanner.next());
        System.out.print("Enter the password: ");
        ftp.setPass(scanner.next());
        System.out.print("Enter the REGEXUSER number: ");
        credentials.regexNumber = scanner.nextInt();
        System.out.print("Set the update time interval(minutes): ");
        int minutes = scanner.nextInt();
        System.out.println("DreamboxAuthenticator is running.");
        credentials.getCredentials();

        long seconds = (minutes * 60) * 1000;
        int i=0;
        while (true) {
            if (!username.equals(credentials.getUsername())) {
                credentials.newTextFile();
                ftp.upload();
                System.out.println("Current username is: " + credentials.getUsername() + ", Check number: " + i);
                username = credentials.getUsername();

            } else {
                System.out.println("Current username is: " + credentials.getUsername() + ", Check number: " + i);
                credentials.getCredentials();
                Thread.sleep(seconds);
            }
            i++;
        }
    }
}


