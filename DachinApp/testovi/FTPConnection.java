import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.SocketException;


/**
 * Created by nikola3in1 on 19.2.17..
 */
public class FTPConnection {
    String server = "ftp.drivehq.com"; ///"ftp.drivehq.com" ,"qetuoadgjl.myftp.org"
    int port = 21;
    String user = "nikola3in1"; //nikola3in1 , root
    String pass = "Nm14031997"; //Nm14031997, dobardan
    public String filePath="";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public FTPConnection() {
    }
    public void download(){

                FTPClient ftpClient = new FTPClient();
                try {

                    ftpClient.connect(server, port);
                    ftpClient.login(user, pass);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                    // APPROACH #1: using retrieveFile(String, OutputStream)
//                    String remoteFile1 = "\\My Documents\\SampleText.txt";
                    String remoteFile1 = "var/etc/CCcam.cfg";

                    File downloadFile1 = new File("/home/nikola3in1/Desktop/CCcam.cfg");
                    OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                    boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
                    outputStream1.close();

                    if (success) {
                        System.out.println("File #1 has been downloaded successfully.");
                    }


                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    public void upload(){

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(filePath+"CCcam.cfg");

            String firstRemoteFile = "CCcam.cfg";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}