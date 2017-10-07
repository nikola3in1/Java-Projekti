package Client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage a) throws Exception{

        primaryStage = new Stage();
        int width=360;
        int height=170;

        VBox root = new VBox();
        HBox hbox = new HBox();
        Text text = new Text("ChatRoom");
        Font font = new Font("Arial",35);
        text.setFont(font);

        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);



        Button register = new Button("Register");
        Button login = new Button("Login");
        register.setMinSize(100,40);
        login.setMinSize(100,40);
        root.setPadding(new Insets(20,1,1,90));
        hbox.setPadding(new Insets(20,1,1,-35));
        hbox.setSpacing(50);


        Register reg = new Register();
        Login log = new Login();
        register.setOnMouseClicked(reg.getHandleRegister());
        login.setOnMouseClicked(log.getHandleLogin());


        hbox.getChildren().addAll(register,login);
        root.getChildren().addAll(text,hbox);

        primaryStage.setTitle("ChatRoom");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
