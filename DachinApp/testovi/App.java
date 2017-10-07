import java.io.IOException;
import java.util.Scanner;

/**
 * Created by nikola3in1 on 19.2.17..
 */
public class App {
    public static void main(String[]args) throws IOException, InterruptedException {
        new App();
    }

    public App() throws IOException, InterruptedException {
            FTPConnection ftp = new FTPConnection();
            Credentials credentials = new Credentials();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert the path to Regex.cfg file: ");
            String path = scanner.next();

        if(path.endsWith("/")){
                ftp.setFilePath(path);
                credentials.setFilePath(path);
            }else{
                ftp.setFilePath(path+'/');
                credentials.setFilePath(path+'/');
            }
            credentials.getCredentials();
            String username = "asd";
            System.out.print("Set the update time interval(minutes): ");
            int minutes = scanner.nextInt();
            long seconds = ( minutes * 60)*1000;
            while(true) {
                if(username != credentials.getUsername()){
                    System.out.println("there is new username");
                    credentials.newTextFile();
                    ftp.upload();
                    username = credentials.getUsername();
                }else{
                    System.out.println("username is same");
                    Thread.sleep(seconds);
                }
            }
        }
}


