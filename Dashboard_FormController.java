package Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Dashboard_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imglogout;

    @FXML
    private Button btndeveloper;


    @FXML
    void doavail(MouseEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/PropertySales/PropertySales_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void docregistration(MouseEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/CustomerRegistration/CustomerRegistration_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void docview(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/CustomerView/CustomerView_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void dodeals(MouseEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/DealsMatured/DealsMatured_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void dodemand(MouseEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/CustomerDemand/CustomerDemand_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void dodeveloper(ActionEvent event) {
    	try {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Developer/Developer_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();
		 Scene scene1=(Scene)btndeveloper.getScene();
		   scene1.getWindow().hide();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

    }

    @FXML
    void doimglogout(MouseEvent event) {
    	try{
    		FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Login/Login_Form.fxml"));
        	Parent root=(Parent)fxmlloader.load();
        	
        	Stage stage=new Stage();
        	stage.setScene(new Scene(root));
        	stage.show();
            
        	Scene scene1=(Scene)imglogout.getScene();
			scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void dopic(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/PicGallery/PicGallery_Form.fxml"));
    	Parent root=(Parent)fxmlloader.load();
    	
    	Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    @FXML
    void doproperty(ActionEvent event) throws IOException {

		FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/FindProperty1/FindProperty1_Form.fxml"));
    	Parent root;
		root = (Parent)fxmlloader.load();
		Stage stage=new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();
    }

    @FXML
    void doregistered(ActionEvent event) {
    	try {
	    	FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/FindProperty/FindProperty_Form.fxml"));
	    	Parent root=(Parent)fxmlloader.load();
	    	
	    	Stage stage=new Stage();
	    	stage.setScene(new Scene(root));
	    	stage.show();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void initialize() {
            }
}
