package DealsMatured;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class DealsMatured_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnchange;

    @FXML
    private Button btnremove;

    @FXML
    private ComboBox<String> combobuyer;

    @FXML
    private ComboBox<String> comboseller;

    @FXML
    private ComboBox<String> comboproperty;

    @FXML
    private TextField txttotal;

    @FXML
    private TextField txtadvance;

    @FXML
    private TextField txtbalance;

    @FXML
    private DatePicker dateregistration;

    @FXML
    void docombobuyer(ActionEvent event) {
    	combobuyer.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docomboproperty(ActionEvent event) {
    	try {
     		pst=con.prepareStatement("Select distinct amount from properties where pid=?");
     		pst.setString(1,comboproperty.getSelectionModel().getSelectedItem());
     		ResultSet records=pst.executeQuery();
     		while(records.next())
     		{
     			String pamt=records.getString("amount");
     			txttotal.setText(pamt);
     		}
     	} catch (SQLException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}
    }

    @FXML
    void docomboseller(ActionEvent event) {
    	comboseller.getSelectionModel().getSelectedItem();
    }

    @FXML
    void doremove(ActionEvent event) {

    }
    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into matured values(?,?,?,?,?,?,?)");
			pst.setString(1,combobuyer.getEditor().getText());
			pst.setString(2,comboseller.getEditor().getText());
			pst.setString(3,comboproperty.getEditor().getText());
			pst.setString(4,txttotal.getText());
			pst.setString(5,txtadvance.getText());
			pst.setString(6,txtbalance.getText());
			pst.setDate(7,Date.valueOf(dateregistration.getValue()));
			pst.executeUpdate();
			btnsave.setText("Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dochange(ActionEvent event) {
    	
    }

    void fillbid()
    {
    ArrayList<String> ids=new ArrayList<String>();
    try {
		pst=con.prepareStatement("Select distinct contact from demands");
		ResultSet records=pst.executeQuery();
		while(records.next())
		{
			String cid=records.getString("contact");
			ids.add(cid);
		}
		combobuyer.getItems().addAll(ids);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    void fillsid()
    {
    ArrayList<String> ids=new ArrayList<String>();
    try {
		pst=con.prepareStatement("Select distinct contact from properties");
		ResultSet records=pst.executeQuery();
		while(records.next())
		{
			String cid=records.getString("contact");
			ids.add(cid);
		}
		comboseller.getItems().addAll(ids);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    void fillpid()
    {
    	ArrayList<String> pids=new ArrayList<String>();
        try {
    		pst=con.prepareStatement("Select distinct pid from properties");
    		ResultSet records=pst.executeQuery();
    		while(records.next())
    		{
    			String pid=records.getString("pid");
    			pids.add(pid);
    		}
    		comboproperty.getItems().addAll(pids);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}	
    }
    int bal;
    @FXML
    void dobalamt(KeyEvent event) {
    	String tamt=txttotal.getText();
    	String advamt=txtadvance.getText();
    	  bal=Integer.parseInt(tamt)-Integer.parseInt(advamt);
    	  System.out.println("qwjkj");
    	 txtbalance.setText(String.valueOf(bal));
    }
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void initialize() {
    con=Connect.getConnection();
    fillbid();
    fillsid();
    fillpid();
    }
}
