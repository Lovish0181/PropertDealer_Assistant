package CustomerRegistration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CustomerRegistration_FormController {

	Connection con;
	PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgprofile;

    @FXML
    private Button btnbrowse;

    @FXML
    private TextField txtproof;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtmobile;

    @FXML
    private Button btnfetch;

    @FXML
    private TextArea txtaddress;

    @FXML
    private RadioButton radbuyer;

    @FXML
    private ToggleGroup customer;

    @FXML
    private RadioButton radseller;

    @FXML
    private RadioButton radboth;

    @FXML
    private ComboBox<String> comboid;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnmodify;

    @FXML
    private Button btnregister;
    
    String filepath;
    @FXML
    void dobrowse(ActionEvent event) {
    	FileChooser chooser=new FileChooser();
    	chooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("All Images","*.*"),
    			new FileChooser.ExtensionFilter("JPG","*.jpg"),
    			new FileChooser.ExtensionFilter("PNG","*.png"),
    			new FileChooser.ExtensionFilter("*.*","*.*")
    			);
    	File file=chooser.showOpenDialog(null);
    	filepath=file.getAbsolutePath();
    try {
		imgprofile.setImage(new Image(new FileInputStream(filepath)));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }


    @FXML
    void docomboid(ActionEvent event) {
    	comboid.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void doregister(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,Current_Date)");
			pst.setString(1,txtname.getText());
			pst.setString(2,txtaddress.getText());
			pst.setString(3,txtcity.getText());
			pst.setString(4,txtmobile.getText());
			pst.setString(5,String.valueOf(filepath));
			if(radboth.isSelected())
			{
			pst.setString(6,radboth.getText());
			}
			else
				if(radbuyer.isSelected())
				{
					pst.setString(6,radbuyer.getText());
				}
				else
					pst.setString(6,radseller.getText());
			pst.setString(7,comboid.getSelectionModel().getSelectedItem());
			pst.setString(8,txtproof.getText());
			pst.executeUpdate();
			btnregister.setText("Registered");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void docancel(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1,txtmobile.getText());
			int count=pst.executeUpdate();
            if(count==0)
	          btncancel.setText("Invalid");
            else
              btncancel.setText("Cancelled");
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    }

    @FXML
    void dofetch(ActionEvent event) {
try {
	pst=con.prepareStatement("select * from customers where mobile=?");
	pst.setString(1,txtmobile.getText());
	ResultSet records=pst.executeQuery();
	if(records.next())
	{
		String ccname=records.getString("cname");
		String caddress=records.getString("address");
		String ccity=records.getString("city");
		String cmobile=records.getString("mobile");
		String cpath=records.getString("picpath");
		String cutype=records.getString("utype");
		String cidproof=records.getString("idproof");
		String cproofnum=records.getString("proofnum");
		txtname.setText(ccname);
		txtaddress.setText(caddress);
		txtcity.setText(ccity);
		txtmobile.setText(cmobile);
		File file1=new File(cpath);
			try {
				imgprofile.setImage(new Image(new FileInputStream(file1)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(cutype.equals("Both"))
		
			radboth.setSelected(true);
		
		else if(cutype.equals("Buyer"))
			radbuyer.setSelected(true);
		else
			radseller.setSelected(true);
		
			comboid.getSelectionModel().select(cidproof);
		txtproof.setText(cproofnum);
		
	}
	else
	{ txtname.setText("");
	txtaddress.setText("");
	txtcity.setText("");
	txtmobile.setText("");
	comboid.getSelectionModel().select("");
	txtproof.setText("");

		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

    }

    @FXML
    void domodify(ActionEvent event) {
    	try {
			pst=con.prepareStatement("Update customers set cname=?,address=?,city=?,utype=?,idproof=?,proofnum=? where mobile=?");
			pst.setString(7,txtmobile.getText());
			pst.setString(1,txtname.getText());
			pst.setString(2,txtaddress.getText());
			pst.setString(3,txtcity.getText());
			if(radboth.isSelected())
			pst.setString(4,radboth.getText());
			else
				if(radbuyer.isSelected())
					pst.setString(4,radbuyer.getText());
				else
					pst.setString(4,radseller.getText());
			pst.setString(5,comboid.getSelectionModel().getSelectedItem());
			pst.setString(6,txtproof.getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				btnmodify.setText("Invalid");
			}
			else
				btnmodify.setText("Modified");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
   con=Connect.getConnection();
   ArrayList<String> ccity=new ArrayList<String>(Arrays.asList("Voter Card","Passport","Aadhar Card","Driving License","Pan Card"));
   comboid.getSelectionModel().select(2);
   comboid.getItems().addAll(ccity);
    }
}

