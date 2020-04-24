package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Stage {
    private BorderPane root;
    private VBox vBox;
    private TextField host;
    private TextField port;
    private Button cnx;


    public Login() {
        initComposant();
        fixChildrenVBox();
        handleEvent();
        root.setCenter(vBox);
        this.setScene(new Scene(root, 300, 300));
        this.show();
        this.setTitle("Login");
    }

    private void fixChildrenVBox() {
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(40.0));
        vBox.setSpacing(12);
        vBox.getChildren().addAll(host, port, cnx);
    }

    private void initComposant() {
        root = new BorderPane();
        vBox = new VBox();
        host = new TextField("127.0.0.1");
        port = new TextField("1234");
        cnx = new Button("connexion");
    }

    private void handleEvent() {
        cnx.setOnAction(actionEvent -> {
            System.out.println("click");
            new RoomChat();
            this.close();
        });
    }

}
