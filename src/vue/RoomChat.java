package vue;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RoomChat extends Stage {
    PrintWriter writer;
    BufferedReader br;
    private Socket socketClient;
    private BorderPane pane;
    private ListView<String> messages;
    private TextField msg;

    private Button sendMdg;
    private VBox vBox;

    private HBox hBox;
    private ObservableList<String> messageList = FXCollections.observableArrayList();

    public RoomChat(Socket socket) {
        this.socketClient = socket;
        initCompenet();
        fixStyle();
        envoyer();
        chat();
        pane.setCenter(vBox);
        this.setScene(new Scene(pane, 500, 500));
        this.show();
        this.setTitle("Chat application ");
    }

    private void initCompenet() {
        pane = new BorderPane();
        messages = new ListView(messageList);
        msg = new TextField();
        sendMdg = new Button("Send Message");
        vBox = new VBox();
        hBox = new HBox();
    }

    private void fixStyle() {
        messages.setPrefSize(250, 250);
        msg.setPrefSize(220, 10);
        body();
        fiwRoom();
        addCHildren();
    }


    private void body() {
        vBox.setPadding(new Insets(40));
        vBox.setSpacing(12);
        vBox.setAlignment(Pos.CENTER);
    }

    private void fiwRoom() {
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(8);
        hBox.setAlignment(Pos.CENTER);
    }

    private void addCHildren() {
        hBox.getChildren().addAll(msg, sendMdg);
        vBox.getChildren().addAll(messages, hBox);
    }

    private void envoyer() {
        sendMdg.setOnAction((actionEvent -> {
            writer.println(msg.getText());
            msg.clear();
        }));
    }

    void chat() {
        try {
            br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            writer = new PrintWriter(socketClient.getOutputStream(), true);
            new Thread(() -> {
                while (true) {
                    try {
                        String resp = br.readLine();
                        Platform.runLater(() -> messageList.add(resp));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
