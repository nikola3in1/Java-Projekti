import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola3in1 on 19.2.17..
 */
public class Credentials {
    String username ="";
    String filePath= "";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTxt() throws IOException {

        URL sup = new URL("https://4cardsharing.com/wp-content/uploads/2014/08/4cardsharing.com-1-1.txt");
        URLConnection yc = sup.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine+"\n");
        in.close();

        return a.toString();
    }

    @Override
    public String toString() {
        return
                "username: " + username;
    }

    public void getCredentials() throws IOException {
        setUser();
    }

    public void setUser() throws IOException {
        String rawText = getTxt();
        String[] split = rawText.split("user : ");
        char[] userChar = split[1].toCharArray();
        String finalUser="";
        for (char c:
             userChar) {
            if(c > 64 && c < 91 || c > 96 && c < 123){
                finalUser+=c;
            }else{
                break;
            }
        }
        this.username = finalUser;


    }

    public String getUsername() {
        return username;
    }

    public void newTextFile(){
        FTPConnection ftp = new FTPConnection();
        String line = null;
        List<String> lines= new ArrayList<>();
        for (char a :
                filePath.toCharArray()) {
            System.out.print(a+" ,");
        }
        System.out.println("ascacacascascascascasc");

        try {
            File f1 = new File(filePath+"/Regex.cfg");
            File f2 = new File(filePath+"/CCcam.cfg");


            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains("REGEXUSER"))
                    line = line.replace("REGEXUSER", username);
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f2);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                out.write(s+'\n');
            out.flush();
            out.close();

            //boolean success = f1.renameTo(f2);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
