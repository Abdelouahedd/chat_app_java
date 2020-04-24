package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import vue.Login;
import vue.RoomChat;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new RoomChat();
    }


}

//        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        lastname.getStylesheets().add("field");