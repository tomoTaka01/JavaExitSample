import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class JavaExitSample extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        WebView webView = new WebView();
        final WebEngine engine = webView.getEngine();
        String html = getClass().getResource("test.html").toExternalForm();
        engine.load(html);
        Button btn = new Button();
        btn.setText("set member");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JSObject jsobj = (JSObject) engine.executeScript("window");
                jsobj.setMember("java", new Bridge());
            }
        });
        root.getChildren().addAll(btn, webView);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Bridge {
        public void exit() {
            Platform.exit();
        }
    }
}
