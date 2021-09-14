package Signup;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

    	public class Signup_FormController {
    		Connection con;
    		PreparedStatement pst;

    	    @FXML
    	    private ResourceBundle resources;

    	    @FXML
    	    private URL location;

    	    @FXML
    	    private PasswordField txtpass;
    	    @FXML
    	    private PasswordField txtpass1;

    	    @FXML
    	    private TextField txtusername;
    	    
    	    @FXML
    	    private TextField txtphone;

    	    @FXML
    	    private Button btnsignup;
    	    
    	    @FXML
    	    private TextField txtaadhar;



    	    @FXML
    	    void dosignup(ActionEvent event) {
    	    	String user = txtusername.getText();
    	        String pass = txtpass.getText();
    	        String pass1 = txtpass1.getText();
    	        if(user.isEmpty() && pass.isEmpty())
    	        {
          	        btnsignup.setText("Invalid");
    	        }
    	        else
    	        {
                try {
					pst = con.prepareStatement("insert into users values(?,?,?,?)");
					pst.setString(1,txtusername.getText());
					if(user.isEmpty()==true)
					{
						Alert alert3 = new Alert(AlertType.WARNING);
	        	    	alert3.setTitle("Warning Message");
	        	    	alert3.setContentText("Please fill Username");
	        	    	alert3.showAndWait();
	        	    	
					}
					pst.setString(2,txtpass.getText());
					  String upperCaseChars = "(.*[A-Z].*)";
					  String lowerCaseChars = "(.*[a-z].*)";
					  String numbers = "(.*[0-9].*)";
                      String specialChars = "(.*[@,#,$,%].*$)";
					if(!pass.matches(upperCaseChars) || !pass.matches(lowerCaseChars) || !pass.matches(numbers) || !pass.matches(specialChars))
					{
						Alert alert3 = new Alert(AlertType.WARNING);
	        	    	alert3.setTitle("Warning Message");
	        	    	alert3.setContentText("Password must include Uppercase,Lowercase,Numbers and Special Characters");
	        	    	alert3.showAndWait();
					}
					else
	                if(pass.equals(pass1)==true)
	    	    	{
	    	    		Alert alert2 = new Alert(AlertType.WARNING);
	        	    	alert2.setTitle("Warning Message");
	        	    	alert2.setContentText("Password Confirmed");
	        	    	alert2.showAndWait();
	        	    	pst.setString(3,txtphone.getText());
	        	    	pst.setString(4,txtaadhar.getText());
	          	        pst.executeUpdate();
	          	      try{
             			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Login/Login_Form.fxml"));
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
	          	    		Alert alert = new Alert(AlertType.WARNING);
	            	    	alert.setTitle("Warning Message");
	            	    	alert.setHeaderText("ThankYou For Using Our Services");
	            	    	alert.setContentText("Signed Up Successfully");
	            	    	alert.showAndWait();

	    	    	}
	    	    	else
	    	    	{
	    	    		Alert alert1 = new Alert(AlertType.WARNING);
	        	    	alert1.setTitle("Warning Message");
	        	    	alert1.setContentText("Incorrect Password");
	        	    	alert1.showAndWait();
	    	    	}
					
        	    	                }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
    	    }
    	    }
    	    @FXML
    	    void initialize() {
    	       con=Connect.getConnection();
    	      }
    	}
