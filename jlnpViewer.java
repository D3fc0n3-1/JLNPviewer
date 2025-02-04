import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JNLPApplet extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField urlField = new TextField();
        urlField.setPromptText("Enter JNLP URL");

        Button launchButton = new Button("Launch");
        launchButton.setOnAction(event -> {
            String url = urlField.getText();
            if (url.isEmpty()) {
                showAlert(AlertType.WARNING, "Warning", "Please enter a URL");
                return;
            }

            try {
                URI uri = new URI(url);
                if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    showAlert(AlertType.ERROR, "Error", "Desktop browsing is not supported on this platform.");
                    return;
                }
                Desktop.getDesktop().browse(uri);
            } catch (URISyntaxException ex) {
                showAlert(AlertType.ERROR, "Error", "Invalid URL: " + ex.getMessage());
            } catch (IOException ex) {
                showAlert(AlertType.ERROR, "Error", "Error launching JNLP: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(urlField, launchButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JNLP Applet");
        primaryStage.show();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
