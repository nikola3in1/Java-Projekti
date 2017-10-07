package Client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register {


    ClientSocket cs;

    Boolean emailIsValid=false;
    Boolean passIsValid=false;
    Boolean userIsValid=false;
    TextField emailInput = new TextField();
    TextField userInput = new TextField();
    PasswordField passInput = new PasswordField();
    PasswordField confirmPassInput = new PasswordField();
    Text errMsg = new Text("");

    Image validated = new Image("true.png");
    Image notvalidated = new Image("false.png");
    ImageView img = new ImageView();
    ImageView img1 = new ImageView();
    ImageView img2 = new ImageView();


    private final EventHandler<MouseEvent> handleRegister = event ->{
        Stage stage = new Stage();

        cs = new ClientSocket();

        GridPane form = new GridPane();
        VBox root = new VBox();
        HBox submitPane = new HBox();
        HBox errorPane = new HBox();


        errorPane.setPadding(new Insets(18,1,1,50));
        submitPane.setPadding(new Insets(7,1,1,290));
        form.setHgap(10);
        form.setVgap(13);
        form.setPadding(new Insets(35,10,0,40));


        Text email = new Text("Email:");
        Text user = new Text("Username:");
        Text pass = new Text("Password:");
        Text confPass = new Text("Confirm Password:");


        img.setFitHeight(25);
        img.setFitWidth(25);
        img1.setFitHeight(25);
        img1.setFitWidth(25);
        img2.setFitHeight(25);
        img2.setFitWidth(25);

        Button submit = new Button("Submit");
        submit.setMinSize(100,40);
        submit.setMaxSize(100,40);
        emailInput.setMinSize(200,20);

        form.add(email, 1, 1);
        form.add(emailInput, 2, 1);
        form.add(img, 3, 1);
        form.add(user, 1, 2);
        form.add(userInput, 2, 2);
        form.add(img1, 3, 2);
        form.add(pass, 1, 3);
        form.add(passInput, 2, 3);
        form.add(confPass, 1, 4);
        form.add(confirmPassInput, 2, 4);
        form.add(img2, 3, 4);
        errMsg.setFill(Color.RED);
        errorPane.getChildren().addAll(errMsg);
        submitPane.getChildren().addAll(submit);
        root.getChildren().addAll(form, errorPane,submitPane);

        emailInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue) {
                    validateEmail();
                }
            }
        });

        confirmPassInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue) {
                    validatePass();
                }
            }
        });

        userInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue) {
                    validateUser();
                }
            }
        });

        submit.setOnMouseReleased(mouseEvent -> {
            if (userIsValid && emailIsValid && passIsValid) {
                Packet p = new Packet();
                p.email = emailInput.getText();
                p.user = userInput.getText();
                p.pass = passInput.getText();
                p.addToDB=true;

                cs.sendPackets(p,4);
                System.out.println("validated");
                //open main page
                try {
                    User.user = userInput.getText();
                    new ChatPage().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
                Main.primaryStage.close();
            }
        });

        stage.setResizable(false);
        stage.setTitle("Register");
        stage.setScene(new Scene(root,450,300));
        stage.show();
    };

    private void validateUser() {
        String user = userInput.getText();
        Packet p = new Packet();
        p.validateUser=true;
        p.user=user;
        System.out.println(user);
        if(!user.contains("@") && user.length()>6 && cs.sendPackets(p,1)){
            img1.setImage(validated);
            errMsg.setText("");
            userIsValid=true;
        }else{
            errMsg.setText("This username already exist.");
            userInput.setTooltip(new Tooltip("Username can't contain @ and must be longer than 6 characters."));
            userIsValid=false;
            img1.setImage(notvalidated);
        }
    }

    private void validatePass() {
        String pass = passInput.getText();
        String conf = confirmPassInput.getText();

        if(pass.length()>7){
            if(pass.equals(conf)){
                img2.setImage(validated);
                passIsValid=true;
                errMsg.setText("");
            }else{
                passInput.setTooltip(new Tooltip("Passwords doesn't match."));
                errMsg.setText("Passwords doesn't match.");
                passIsValid=false;
                img2.setImage(notvalidated);
            }
        }else{
            passInput.setTooltip(new Tooltip("Password must be longer than 7 characters."));
            passIsValid=false;
            img2.setImage(notvalidated);
        }



    }

    private void validateEmail() {
        String text =emailInput.getText();
        if(text.contains("@") && text.contains(".")){
            Packet p = new Packet();
            p.validateEmail=true;
            p.email=text;
            if(cs.sendPackets(p,2)){
                img.setImage(validated);
                errMsg.setText("");
                emailIsValid=true;
            }else{
                errMsg.setText("This email is already used.");
                img.setImage(notvalidated);
                emailIsValid=false;
            }
        }else{
            errMsg.setText("Invalid mail.");

            img.setImage(notvalidated);
            emailIsValid=false;
        }
    }

    public EventHandler<MouseEvent> getHandleRegister() {
        return handleRegister;
    }


}
