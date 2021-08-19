package PropertySales;

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
import javafx.scene.image.ImageView;

public class PropertySales_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtpid;

    @FXML
    private Button btnfetch;

    @FXML
    private ImageView imgproperty;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnchange;

    @FXML
    private TextField txtcid;

    @FXML
    private ComboBox<String> comboptype;

    @FXML
    private TextField txtsize;

    @FXML
    private TextField txtrside;

    @FXML
    private TextField txtlside;

    @FXML
    private TextField txtdepth;

    @FXML
    private TextField txtwidth;

    @FXML
    private TextField txtloaction;

    @FXML
    private TextField txtamount;

    @FXML
    private TextField txtcity;

    @FXML
    private ComboBox<String> combodirection;

    @FXML
    private TextArea txtdetails;

    @FXML
    void docombodirection(ActionEvent event) {
    	combodirection.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docomboptype(ActionEvent event) {
       comboptype.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void dochange(ActionEvent event) {

		try {
			pst=con.prepareStatement("update properties set contact=?,ptype=?,size=?,width=?,depth=?,lside=?,rside=?,location=?,city=?,amount=?,direction=?,description=? where pid=?");
			pst.setString(13,txtpid.getText());
			pst.setString(1,txtcid.getText());
			pst.setString(2,comboptype.getEditor().getText());
			pst.setString(3,txtsize.getText());
			pst.setString(4,txtwidth.getText());
			pst.setString(5,txtdepth.getText());
			pst.setString(6,txtlside.getText());
			pst.setString(7,txtrside.getText());
			pst.setString(8,txtloaction.getText());
			pst.setString(9,txtcity.getText());
			pst.setString(10,txtamount.getText());
			pst.setString(11,combodirection.getEditor().getText());
			pst.setString(12,txtdetails.getText());
			
			int count=pst.executeUpdate();
			if(count==0)
			btnchange.setText("Invalid");
			else
				btnchange.setText("Changed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from properties where pid=?");
			pst.setString(1,txtpid.getText());
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

    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("Select * from properties where pid=?");
			pst.setString(1,txtpid.getText());
			ResultSet records=pst.executeQuery();
			
			if(records.next())
			{
				String pcid=records.getString("contact");
				String pctype=records.getString("ptype");
				String psize=records.getString("size");
				String pwidth=records.getString("width");
				String pdepth=records.getString("depth");
				String plside=records.getString("lside");
				String prside=records.getString("rside");
				String ploaction=records.getString("location");
				String pcity=records.getString("city");
				String pamt=records.getString("amount");
				String pdir=records.getString("direction");
				String pdes=records.getString("description");
				txtcid.setText(pcid);
				comboptype.getEditor().setText(pctype);
				txtsize.setText(psize);
				txtwidth.setText(pwidth);
				txtdepth.setText(pdepth);
				txtlside.setText(plside);
				txtrside.setText(prside);
				txtloaction.setText(ploaction);
				txtcity.setText(pcity);
				txtamount.setText(pamt);
				combodirection.getEditor().setText(pdir);
				txtdetails.setText(pdes);
				}
			else
			{
				txtcid.setText("");
				comboptype.getEditor().setText("");
				txtsize.setText("");
				txtwidth.setText("");
				txtdepth.setText("");
				txtlside.setText("");
				txtrside.setText("");
				txtloaction.setText("");
				txtcity.setText("");
				txtamount.setText("");
				combodirection.getEditor().setText("");
				txtdetails.setText("");
			}
				
    	}
    		
    		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void dosave(ActionEvent event) {
    	try {
			
    		pst=con.prepareStatement("insert into properties(contact,ptype,size,width,depth,lside,rside,location,city,amount,direction,description,doa) values(?,?,?,?,?,?,?,?,?,?,?,?,Current_Date)");
			pst.setString(1,txtcid.getText());
			pst.setString(2,comboptype.getEditor().getText());
			pst.setString(3,txtsize.getText());
			pst.setString(4,txtwidth.getText());
			pst.setString(5,txtdepth.getText());
			pst.setString(6,txtlside.getText());
			pst.setString(7,txtrside.getText());
			pst.setString(8,txtloaction.getText());
			pst.setString(9,txtcity.getText());
			pst.setString(10,txtamount.getText());
			pst.setString(11,combodirection.getEditor().getText());
			pst.setString(12,txtdetails.getText());
			pst.executeUpdate();
			btnsave.setText("Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    PreparedStatement pst;
    Connection con;

    @FXML
    void initialize() {
       con=Connect.getConnection();
       ArrayList<String> prtype=new ArrayList<String>(Arrays.asList("Commercial","Residential","Agricultural/Open Land","Industrial","Mixed-Use","Special Purpose"));
       comboptype.getSelectionModel().select(1);
       comboptype.getItems().addAll(prtype);
       
       ArrayList<String> pdir=new ArrayList<String>(Arrays.asList("North","South","West","East"));
       combodirection.getSelectionModel().select(1);
       combodirection.getItems().addAll(pdir);
    }
}

