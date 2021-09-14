package ForgotPassword;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Forgotpass_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtusername;

    @FXML
    private TextField txtpass;

    @FXML
    private Label txtlogin;

    @FXML
    private Button btnverify;
    
    PreparedStatement pst1;
    @FXML
    void doverify(ActionEvent event) throws SQLException {
    	pst=con.prepareStatement("Select distinct password from users where name=?");
    	pst.setString(1,txtusername.getText());
    	ResultSet records=pst.executeQuery();
    	pst1=con.prepareStatement("Select distinct password from users where contact=?");
    	pst1.setString(1,txtusername.getText());
    	ResultSet record=pst1.executeQuery();
    	if(records.next())
    	{
    		String us=records.getString("password");
    		txtpass.setText(us);
    		btnverify.setText("Verified");
    	}
    	else
    		if(record.next())
    		{
    			String ph=record.getString("password");
    			txtpass.setText(ph);
    		btnverify.setText("Verified");
    		}
    		else
    		{
    			btnverify.setText("Invalid");
    		}
    }
    

    @FXML
    void backlogin(MouseEvent event) {
    	try{
			  FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Login/Login_Form.fxml"));
	        	Parent root=(Parent)fxmlloader.load();
	        	
	        	Stage stage=new Stage();
	        	stage.setScene(new Scene(root));
	        	stage.show();

	            Scene scene1 = (Scene)txtlogin.getScene();
	            scene1.getWindow().hide();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
    }
    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() {
        con=Connect.getConnection();
    }
}
