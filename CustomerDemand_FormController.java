package CustomerDemand;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerDemand_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtrecord;

    @FXML
    private ComboBox<String> comboptypes;

    @FXML
    private ComboBox<String> comborecord;

    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnfetch;

    @FXML
    private Button btnmodify;

    @FXML
    private TextArea txtdemands;

    @FXML
    private TextField txtmin;

    @FXML
    private TextField txtmax;

    @FXML
    private TextField txtplace;

    @FXML
    private TextField txtbudget;

    @FXML
    private TextField txtcid;

    @FXML
    void doadd(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into demands(contact,ptype,budget,locality,minsize,maxsize,otherinfo) values(?,?,?,?,?,?,?)");
			pst.setString(1,txtcid.getText());
			pst.setString(2,comboptypes.getEditor().getText());
			pst.setInt(3,Integer.parseInt(txtbudget.getText()));
			pst.setString(4,txtplace.getText());
			pst.setInt(5,Integer.parseInt(txtmin.getText()));
			pst.setInt(6,Integer.parseInt(txtmax.getText()));
			pst.setString(7,txtdemands.getText());
			pst.executeUpdate();
			btnadd.setText("Added");
			pst=con.prepareStatement("select distinct rid from demands");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String drid=records.getString("rid");
				txtrecord.setText(drid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void docomboptypes(ActionEvent event) {
    	comboptypes.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docomborecord(ActionEvent event) {

    }

    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from demands where rid=?");
			pst.setString(1,comborecord.getSelectionModel().getSelectedItem());
			int count=pst.executeUpdate();
			if(count==0)
				btndelete.setText("Invalid");
			else
				btndelete.setText("Deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    void fillrecords()
    {
    	ArrayList<String> rec=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select distinct rid from demands");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String crec=records.getString("rid");
				rec.add(crec);
			}
			comborecord.getItems().addAll(rec);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from demands where rid=?");
			pst.setString(1,comborecord.getSelectionModel().getSelectedItem());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ccontact=records.getString("contact");
				String cptype=records.getString("ptype");
				String cbudget=records.getString("budget");
				String cplace=records.getString("locality");
				String cmin=records.getString("minsize");
				String cmax=records.getString("maxsize");
				String cinfo=records.getString("otherinfo");
				String crecord=records.getString("rid");
				txtcid.setText(ccontact);
				comboptypes.getEditor().setText(cptype);
				txtbudget.setText(cbudget);
				txtplace.setText(cplace);
				txtmin.setText(cmin);
				txtmax.setText(cmax);
				txtdemands.setText(cinfo);
				txtrecord.setText(crecord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void domodify(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update demands set contact=?,ptype=?,budget=?,locality=?,minsize=?,maxsize=?,otherinfo=? where rid=?");
			pst.setString(8,txtrecord.getText());
			pst.setString(1,txtcid.getText());
			pst.setString(2,comboptypes.getEditor().getText());
			pst.setString(3,txtbudget.getText());
			pst.setString(4,txtplace.getText());
			pst.setString(5,txtmin.getText());
			pst.setString(6,txtmax.getText());
			pst.setString(7,txtdemands.getText());
			int count=pst.executeUpdate();
			if(count==0)
			btnmodify.setText("Invalid");
			else
				btnmodify.setText("Modified");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void initialize() {
        con=Connect.getConnection();
        ArrayList<String> property=new ArrayList<String>(Arrays.asList("Commercial","Residential","Agricultural/Open Land","Industrial","Mixed-Use","Special Purpose"));
        comboptypes.getSelectionModel().select(0);
        comboptypes.getItems().addAll(property);
        fillrecords();
    }
}
