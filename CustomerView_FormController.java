package CustomerView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerView_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomerBean> tblview;

    @FXML
    private Button btnexcel;

    @FXML
    private ComboBox<String> combocustomer;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private Button btnshowall;

    @FXML
    private Button btncustomer;

    @FXML
    private Button btnarea;

    @FXML
    void doarea(ActionEvent event) {
    	getarea();
    }
    void getarea()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customers where city=?");
			pst.setString(1,comboarea.getEditor().getText());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cnme=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cutype=records.getString("utype");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofnum");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				CustomerBean array =new CustomerBean(cnme,caddress,ccity,cmob,cutype,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    @FXML
    void docomboarea(ActionEvent event) {
    	comboarea.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docombocustomer(ActionEvent event) {
        combocustomer.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docustomer(ActionEvent event) {
    	getcustomer();
    }
    void getcustomer()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customers where utype=?");
			pst.setString(1,combocustomer.getEditor().getText());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cnme=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cutype=records.getString("utype");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofnum");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				CustomerBean array =new CustomerBean(cnme,caddress,ccity,cmob,cutype,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	File file = new File("PCV.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name,Address,City,Mobile,Customer Type,ID Proof,Proof Number,Date of Registration\n";
            writer.write(text);
            for (CustomerBean p : list) 
            {
    			text = p.getCname()+ "," + p.getAddress()+ "," + p.getCity()+ "," + p.getMobile()+"," + p.getUtype()+"," + p.getIdproof()+ "," + p.getProofnum()+ "," + p.getDor()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }

    @FXML
    void doexcel(ActionEvent event) {
    	try {
			writeExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void doshowall(ActionEvent event) {
    	doall();    	
    }
    void doall()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from customers");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cnme=records.getString("cname");
				String caddress=records.getString("address");
				String ccity=records.getString("city");
				String cmob=records.getString("mobile");
				String cutype=records.getString("utype");
				String cid=records.getString("idproof");
				String cproof=records.getString("proofnum");
				LocalDate cdor=records.getDate("dor").toLocalDate();
				CustomerBean array =new CustomerBean(cnme,caddress,ccity,cmob,cutype,cid,cproof,cdor);
				list.add(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    void fillarea()
    {
    	ArrayList<String> carea=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct city from customers");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String areas=records.getString("city");
				carea.add(areas);
			}
			comboarea.getItems().addAll(carea);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void fillcustomer()
    {
    	ArrayList<String> ccustomer=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct utype from customers");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ctype=records.getString("utype");
				ccustomer.add(ctype);
			}
			combocustomer.getItems().addAll(ccustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @SuppressWarnings("unchecked")
	void addcoloumn()
    {
    	TableColumn<CustomerBean,String> namecol=new TableColumn<CustomerBean, String>("Name");
		namecol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("cname"));//field name in bean
		namecol.setMinWidth(50);
		
		TableColumn<CustomerBean,String> addresscol=new TableColumn<CustomerBean, String>("Address");
		addresscol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("address"));//field name in bean
		addresscol.setMinWidth(350);
		
		TableColumn<CustomerBean,String> citycol=new TableColumn<CustomerBean,String>("City");
		citycol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("city"));//field name in bean
		citycol.setMinWidth(50);
		
		TableColumn<CustomerBean,String> mobilecol=new TableColumn<CustomerBean,String>("Mobile No.");
		mobilecol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));//field name in bean
		mobilecol.setMinWidth(80);
		
		TableColumn<CustomerBean,String> customercol=new TableColumn<CustomerBean, String>("Customer Type");
		customercol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("utype"));//field name in bean
		customercol.setMinWidth(80);
		
		TableColumn<CustomerBean,String> idproofcol=new TableColumn<CustomerBean, String>("ID Proof");
		idproofcol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("idproof"));//field name in bean
		idproofcol.setMinWidth(80);
		
		TableColumn<CustomerBean,String> proofcol=new TableColumn<CustomerBean, String>("Proof No.");
		proofcol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("proofnum"));//field name in bean
		proofcol.setMinWidth(100);
		
		TableColumn<CustomerBean,LocalDate> dorcol=new TableColumn<CustomerBean,LocalDate>("Date of Registration");
		dorcol.setCellValueFactory(new PropertyValueFactory<CustomerBean,LocalDate>("dor"));//field name in bean
	    dorcol.setMinWidth(50);
		
		tblview.getColumns().addAll(namecol,addresscol,citycol,mobilecol,customercol,idproofcol,proofcol,dorcol);
    }
    
    Connection con;
    PreparedStatement pst;
    ObservableList<CustomerBean> list;
    @FXML
    void initialize() {
        con=Connect.getConnection();
        fillcustomer();
        fillarea();
        addcoloumn();
        }
}
