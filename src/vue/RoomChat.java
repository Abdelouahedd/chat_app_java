package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RoomChat extends Stage {
    private BorderPane pane;
    private ListView messages;
    private ListView users;
    private TextField msg;
    private Text text;
    private Button sendMdg;
    private VBox vBox;
    private VBox lisUsers;
    private HBox hBox;

    public RoomChat() {
        initCompenet();
        fixStyle();
        pane.setCenter(vBox);
        pane.setLeft(lisUsers);
        this.setScene(new Scene(pane, 800, 500));
        this.show();
        this.setTitle("Chat application ");
    }

    private void initCompenet() {
        pane = new BorderPane();
        messages = new ListView();
        users = new ListView();
        msg = new TextField();
        text = new Text("List of USERS");
        sendMdg = new Button("Send Message");
        vBox = new VBox();
        lisUsers = new VBox();
        hBox = new HBox();
    }

    private void fixStyle() {
        messages.setPrefSize(350, 300);
        users.setPrefSize(200, 400);
        msg.setPrefSize(220,10);
        listUsers();
        body();
        fiwRoom();
        addCHildren();
    }

    private void listUsers() {
        lisUsers.setPadding(new Insets(10, 5, 2, 6));
        lisUsers.setSpacing(8);
        lisUsers.setAlignment(Pos.CENTER);
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
        lisUsers.getChildren().addAll(text, users);
    }


}
