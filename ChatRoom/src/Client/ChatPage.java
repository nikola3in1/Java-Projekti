package Client;

import TEST.TrafficClient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChatPage extends Application {

    public String nick = "";
    public static String msg = "";
    public static TextField input = new TextField();
    public static Button send = new Button("Send");
    public static VBox msgs = new VBox();
    public static ScrollPane scroll = new ScrollPane();
    public static ArrayList<StackPane> imgss;
    public static Button logout = new Button("Logout");
    public static Button mainRoom = new Button("WelcomeRoom");
    public static Button scienceRoom = new Button("ScienceRoom");
    public static Button gamesRoom = new Button("GamesRoom");
    public static Button musicRoom = new Button("MusicRoom");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        BorderPane chat = new BorderPane();
        HBox inputPane = new HBox();
        HBox root = new HBox();
        VBox menu = new VBox();
        root.setPadding(new Insets(5,5,5,5));
        root.setSpacing(5);
        send.setMinSize(50,20);
        inputPane.setSpacing(4);
        scroll.setStyle("-fx-background-color: #caffa3; -fx-border-color: aqua");
        chat.setBackground(new Background(new BackgroundFill(Color.web("#a2f961"), CornerRadii.EMPTY, Insets.EMPTY)));
        imgss = new ArrayList<>();

        menu.setPadding(new Insets(1,1,1,1));
        menu.setSpacing(30);
        logout.setMinSize(70,27);
        logout.setMaxSize(70,27);
        BorderPane logoutContainer = new BorderPane();
        logoutContainer.setRight(logout);
        HBox textContainer = new HBox();
        textContainer.setStyle("-fx-border-color: #429eff ; -fx-border-radius: 5px");
        Text text = new Text("Welcome to \n             ChatRoom!");
        text.setFont(new Font("Arial",18));
        textContainer.getChildren().add(text);
        textContainer.setPadding(new Insets(3,0,3,8));
        VBox rooms = new VBox();

        Button plusRoom = new Button("+");
        mainRoom.setMinSize(195,30);
        mainRoom.setMaxSize(195,30);
        scienceRoom.setMinSize(195,30);
        scienceRoom.setMaxSize(195,30);
        gamesRoom.setMinSize(195,30);
        gamesRoom.setMaxSize(195,30);
        musicRoom.setMinSize(195,30);
        musicRoom.setMaxSize(195,30);
        plusRoom.setMinSize(195,30);
        plusRoom.setMaxSize(195,30);

        rooms.setSpacing(5);
        rooms.getChildren().addAll(mainRoom, scienceRoom, gamesRoom, musicRoom, plusRoom);
        plusRoom.setDisable(true);
        Text info = new Text("More rooms coming soon!");
        GridPane pics = new GridPane();
        Image img = new Image("cat.jpg");
        Image img1 = new Image("bot.jpg");
        Image img2 = new Image("cage.png");
        Image img3 = new Image("minion.png");
        Image img4 = new Image("dolan.jpg");
        Image img5 = new Image("frog.jpg");
        Image img6 = new Image("kappa.png");
        Image img7 = new Image("spongebob.png");
        Image img8 = new Image("troll.jpg");
        ImageView imgv = new ImageView(img);
        ImageView imgv1 = new ImageView(img1);
        ImageView imgv2 = new ImageView(img2);
        ImageView imgv3 = new ImageView(img3);
        ImageView imgv4 = new ImageView(img4);
        ImageView imgv5 = new ImageView(img5);
        ImageView imgv6 = new ImageView(img6);
        ImageView imgv7 = new ImageView(img7);
        ImageView imgv8 = new ImageView(img8);
        StackPane imgs = new StackPane();
        StackPane imgs1 = new StackPane();
        StackPane imgs2 = new StackPane();
        StackPane imgs3 = new StackPane();
        StackPane imgs4 = new StackPane();
        StackPane imgs5 = new StackPane();
        StackPane imgs6 = new StackPane();
        StackPane imgs7 = new StackPane();
        StackPane imgs8 = new StackPane();
        imgss.add(imgs);
        imgss.add(imgs1);
        imgss.add(imgs2);
        imgss.add(imgs3);
        imgss.add(imgs4);
        imgss.add(imgs5);
        imgss.add(imgs6);
        imgss.add(imgs7);
        imgss.add(imgs8);

        imgs.getChildren().add(imgv);
        imgs1.getChildren().add(imgv1);
        imgs2.getChildren().add(imgv2);
        imgs3.getChildren().add(imgv3);
        imgs4.getChildren().add(imgv4);
        imgs5.getChildren().add(imgv5);
        imgs6.getChildren().add(imgv6);
        imgs7.getChildren().add(imgv7);
        imgs8.getChildren().add(imgv8);


        int imgSize=60;
        imgv.setFitHeight(imgSize);
        imgv.setFitWidth(imgSize);
        imgv1.setFitHeight(imgSize);
        imgv1.setFitWidth(imgSize);
        imgv2.setFitHeight(imgSize);
        imgv2.setFitWidth(imgSize);
        imgv3.setFitHeight(imgSize);
        imgv3.setFitWidth(imgSize);
        imgv4.setFitHeight(imgSize);
        imgv4.setFitWidth(imgSize);
        imgv5.setFitHeight(imgSize);
        imgv5.setFitWidth(imgSize);
        imgv6.setFitHeight(imgSize);
        imgv6.setFitWidth(imgSize);
        imgv7.setFitHeight(imgSize);
        imgv7.setFitWidth(imgSize);
        imgv8.setFitHeight(imgSize);
        imgv8.setFitWidth(imgSize);
        imgv.setSmooth(true);
        imgv1.setSmooth(true);
        imgv2.setSmooth(true);
        imgv3.setSmooth(true);
        imgv4.setSmooth(true);
        imgv5.setSmooth(true);
        imgv6.setSmooth(true);
        imgv7.setSmooth(true);
        imgv8.setSmooth(true);

        pics.setVgap(4);
        pics.setHgap(4);
        pics.addColumn(0,imgs,imgs1,imgs2);
        pics.addColumn(1,imgs3,imgs4,imgs5);
        pics.addColumn(2, imgs6, imgs7, imgs8);
        HBox infoContainer = new HBox();
        infoContainer.getChildren().add(info);
        infoContainer.setPadding(new Insets(3,0,3,8));
        infoContainer.setStyle("-fx-border-color: #429eff ; -fx-border-radius: 5px");
        pics.setPadding(new Insets(3,3,3,3));
        pics.setStyle("-fx-border-color: #429eff; -fx-border-radius: 5px");

        menu.getChildren().addAll(logoutContainer, textContainer, rooms, infoContainer, pics);



        inputPane.getChildren().addAll(input, send);
        inputPane.setPadding(new Insets(3,3,3,3));
        scroll.setContent(msgs);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setPrefViewportHeight(552);
        chat.setTop(scroll);
        chat.setBottom(inputPane);
        input.setMinWidth(340);






        chat.setMinSize(400,590);
        chat.setMaxSize(400,590);

        nick = User.user;
        User.img="bot.jpg";

        new Thread(() -> TrafficClient.main(null)).start();
//        send.setOnMouseClicked(mouseEvent -> {
//            TrafficClient.newMsg = true;
//            TrafficClient.msg = input.getText();
//
//        });


//        Image img = new Image("cat.jpg");
//        Image img1 = new Image("bot.jpg");
//        Image img2 = new Image("cage.png");
//        Image img3 = new Image("minion.png");
//        Image img4 = new Image("dolan.jpg");
//        Image img5 = new Image("frog.jpg");
//        Image img6 = new Image("kappa.png");
//        Image img7 = new Image("spongebob.png");
//        Image img8 = new Image("troll.jpg");




        imgs.setOnMouseClicked(mouseEvent -> {
            User.img = "cat.jpg";
            resetPics(imgs);
            imgs.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs1.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs1);
            User.img = "bot.jpg";
            imgs1.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs2.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs2);
            User.img = "cage.png";
            imgs2.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs3.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs3);
            User.img = "minion.png";
            imgs3.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs4.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs4);
            User.img = "dolan.jpg";
            imgs4.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs5.setOnMouseClicked(mouseEvent -> {

            resetPics(imgs5);
            User.img = "frog.jpg";
            imgs5.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs6.setOnMouseClicked(mouseEvent -> {
            System.out.println("sadas");
            resetPics(imgs6);
            User.img = "kappa.png";
            imgs6.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });
        imgs7.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs7);
            User.img = "spongebob.png";
            imgs7.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });

        imgs8.setOnMouseClicked(mouseEvent -> {
            resetPics(imgs8);
            User.img = "troll.jpg";
            imgs8.setStyle("-fx-border-color: darkcyan; -fx-border-radius: 5px");
        });





        msgs.setPadding(new Insets(10,3,3,10));
        msgs.setSpacing(10);

        System.out.println(nick);

        root.getChildren().addAll(chat,menu);

        stage.setTitle("ChatRoom");
        stage.setScene(new Scene(root,613,600));
        stage.show();
    }
    public static void resetPics(StackPane imgs){
        for (StackPane s: imgss){
            if(s!=imgs){
                s.setStyle("");
            }
        }
    }
}
