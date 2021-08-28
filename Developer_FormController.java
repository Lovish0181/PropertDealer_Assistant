package Developer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Developer_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Hyperlink dash;
    
    @FXML
    void dodashboard(ActionEvent event) {
    	
		try {
			FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Dashboard/Dashboard_Form.fxml"));
	    	Parent root;
			root = (Parent)fxmlloader.load();
			Stage stage=new Stage();
	    	stage.setScene(new Scene(root));
	    	stage.show();
	    	Scene scene1=(Scene)dash.getScene();
			scene1.getWindow().hide();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    
    @FXML
    void facebook(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI("https://www.facebook.com/"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void instagram(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI("https://www.instagram.com/"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void linkedin(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/lovishjindal0181/"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void whatsapp(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI("https://web.whatsapp.com/"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void github(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI("https://github.com/Lovish0181"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
