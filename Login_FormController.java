package Login;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;

public class  Login_FormController{
	PreparedStatement pst;
	Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtusername;
    
    @FXML
    private Label txtforgot;

    @FXML
    private PasswordField txtpass;

    @FXML
    private Button btnsignin;

    @FXML
    private Button btnsignup;
  

    void ShowWarning(String msg)
    {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Warning Message");
    	alert.setHeaderText("ThankYou For Using Our Services");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    
    @FXML
    void dosignin(ActionEvent event) throws SQLException {
    	String user = txtusername.getText();
        String pass = txtpass.getText();
        
        pst = con.prepareStatement("select * from users");
        ResultSet record = pst.executeQuery();
        
        if(record.next())
        {
     	   String checkuser = record.getString("name");
     	   String checkpass = record.getString("password");
     	   
     	   if(user.equals(checkuser) && pass.equals(checkpass))
     	   {
     		   try{
     			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Dashboard/Dashboard_Form.fxml"));
     	        	Parent root=(Parent)fxmlloader.load();
     	        	
     	        	Stage stage=new Stage();
     	        	stage.setScene(new Scene(root));
     	        	stage.show();

     	            Scene scene1 = (Scene)btnsignin.getScene();
     	            scene1.getWindow().hide();

     			}
     			catch(Exception e)
     			{
     				e.printStackTrace();
     			}
     	   }
     	   else 
     	   {
     		  Alert alert = new Alert(AlertType.WARNING);
   	    	alert.setTitle("Warning Message");
   	    	alert.setHeaderText("ThankYou For Using Our Services");
   	    	alert.setContentText("Invalid Username or Password");
   	    	alert.showAndWait();
     	   }
        }
        

    }


    @FXML
    void dosignup(ActionEvent event){
        try{
     			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Signup/Signup_Form.fxml"));
     	        	Parent root=(Parent)fxmlloader.load();
     	        	
     	        	Stage stage=new Stage();
     	        	stage.setScene(new Scene(root));
     	        	stage.show();
     	        	 Scene scene1 = (Scene)btnsignup.getScene();
      	            scene1.getWindow().hide();

     			}
     			catch(Exception e)
     			{
     				e.printStackTrace();
     			}
        
    }
    @FXML
    void doforget(MouseEvent event) throws SQLException {
    	try{
			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/ForgotPassword/Forgotpass_Form.fxml"));
	        	Parent root=(Parent)fxmlloader.load();
	        	
	        	Stage stage=new Stage();
	        	stage.setScene(new Scene(root));
	        	stage.show();

	            Scene scene1 = (Scene)txtforgot.getScene();
	            scene1.getWindow().hide();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

    }
    @FXML
    void initialize() {
    	con=Connect.getConnection();
    }
}
