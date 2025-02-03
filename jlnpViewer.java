
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JNLPApplet extends JFrame {

    private JTextField urlField;
    private JButton launchButton;

    public JNLPApplet() {
        setLayout(new FlowLayout());

        urlField = new JTextField(30);
        add(urlField);

        launchButton = new JButton("Launch");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchJNLP();
            }
        });
        add(launchButton);

        setSize(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void launchJNLP() {
        String url = urlField.getText();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a URL");
            return;
        }

        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error launching JNLP: " +
ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JNLPApplet();
            }
        });
    }
}

