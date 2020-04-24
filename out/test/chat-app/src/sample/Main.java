package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.DatagramPacket;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        VBox vBox = new VBox();
        TextField name = new TextField();
        name.setStyle("-fx-border-color: black;");
        TextField lastname = new TextField();
        Text fullname = new Text();
        Button btn = new Button("valide");

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
//        fullname.setEffect(ds);
        fullname.setFill(Color.YELLOWGREEN);
        fullname.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

//        fullname.textProperty().bind(name.textProperty());
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(40.0));
        vBox.setSpacing(12);
        vBox.getChildren().addAll(name, lastname, btn, fullname);
        root.setCenter(vBox);

        btn.setOnAction(actionEvent -> getDate(name, lastname, fullname));


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    private void getDate(TextField name, TextField lastname, Text fullname) {
        String text = name.getText() + " " + lastname.getText();
        fullname.setText(text);
    }
}

//        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        lastname.getStylesheets().add("field");