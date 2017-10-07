package Client;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login {

    ClientSocket cs;
    Boolean emailIsValid = false;
    Boolean passIsValid = false;
    Boolean userIsValid = false;
    PasswordField passInput = new PasswordField();
    TextField userInput = new TextField();

    public EventHandler<MouseEvent> getHandleLogin() {
        return handleLogin;
    }

    private final EventHandler<MouseEvent> handleLogin = event -> {
        cs = new ClientSocket();
        Stage stage = new Stage();
        GridPane form = new GridPane();
        VBox root = new VBox();
        HBox errorPane = new HBox();
        HBox submitPane = new HBox();

        submitPane.setPadding(new Insets(10, 1, 1, 300));
        form.setHgap(10);
        form.setVgap(13);
        form.setPadding(new Insets(40, 10, 0, 20));


        Text user = new Text("Username/Email:");
        Text pass = new Text("Password:");
        Text errMsg = new Text("");
        errMsg.setFill(Color.RED);
        errorPane.getChildren().add(errMsg);
        errorPane.setPadding(new Insets(13,1,1,155));

        Button submit = new Button("Submit");
        submit.setMinSize(100, 40);
        submit.setMaxSize(100, 40);
        userInput.setMinSize(200, 20);


        form.add(user, 1, 1);
        form.add(userInput, 2, 1);
        form.add(pass, 1, 2);
        form.add(passInput, 2, 2);



        submitPane.getChildren().add(submit);
        root.getChildren().addAll(form,errorPane, submitPane);

        submit.setOnMouseReleased(mouseEvent -> {
            if(passInput.getText().length()>7 && userInput.getText().length()>6){
                Packet p = new Packet();
                p.email_user = userInput.getText();
                p.pass = passInput.getText();
                p.authenticate=true;
                if(ClientSocket.sendPackets(p,3)){
                    //DALJE
                    try {
                        User.user = userInput.getText();
                        new ChatPage().start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stage.close();
                    Main.primaryStage.close();
                }else{
                    errMsg.setText("Wrong input!");
                }
            }else{
                errMsg.setText("Wrong input!");
            }
        });


        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 450, 230));
        stage.show();

    };





}
