package FindProperty;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FindProperty_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PropertBean> tblview;

    @FXML
    private ComboBox<String> combopropertype;

    @FXML
    private ComboBox<String> combocity;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private TextField txtmin;

    @FXML
    private TextField txtmax;

    @FXML
    private TextField txtrs2;

    @FXML
    private TextField txtrs1;

    @FXML
    private Button btngoogle;

    @FXML
    private Button btnexcel;

    @FXML
    void docomboarea(ActionEvent event) {
    	comboarea.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docombocity(ActionEvent event) {
    	combocity.getSelectionModel().getSelectedItem();
    }

    @FXML
    void docombopropertype(ActionEvent event) {
    	combopropertype.getSelectionModel().getSelectedItem();
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
    void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	File file = new File("FPF.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Contact,Propert-Type,Size,Width,Depth,L-Side,R-Side,City,Location,Amount,Direction,Date of Authentication\n";
            writer.write(text);
            for (PropertBean p : list) 
            {
    			text = p.getContact()+ "," + p.getPtype()+ "," + p.getSize()+ "," + p.getWidth()+ "," + p.getDepth()+ "," + p.getLside()+ "," + p.getRside()+ "," + p.getCity()+ "," + p.getLocation()+"," + p.getAmount()+"," + p.getDirection()+ "," + p.getDoa()+"\n";
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
    void dogoogle(ActionEvent event) {
    	dofetch();
    }
    void dofetch()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from properties where ptype=? and city=? and location=? and amount between ? and ? and size between ? and ?");
			pst.setString(1,combopropertype.getEditor().getText());
			pst.setString(2,combocity.getEditor().getText());
			pst.setString(3,comboarea.getEditor().getText());
			pst.setString(4,txtrs1.getText());
			pst.setString(5,txtrs2.getText());
			pst.setString(6,txtmin.getText());
			pst.setString(7,txtmax.getText());
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String fcontact=records.getString("contact");
				String fptype=records.getString("ptype");
				String fsize=records.getString("size");
				String fwidth=records.getString("width");
				String fdepth=records.getString("depth");
				String flside=records.getString("lside");
				String frside=records.getString("rside");
				String floc=records.getString("location");
				String fcity=records.getString("city");
			    String famt=records.getString("amount");
			    String fdirection=records.getString("direction");
				LocalDate fdoa=records.getDate("doa").toLocalDate();
				PropertBean array=new PropertBean(fcontact, fptype, fsize, fwidth, fdepth, flside, frside, floc, fcity, famt, fdirection, fdoa);
			list.addAll(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void getarea()
    {
    	ArrayList<String> ar=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct location from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String are=records.getString("location");
				ar.add(are);
			}
			comboarea.getItems().addAll(ar);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void getcity()
    {
    	ArrayList<String> ci=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct city from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String cit=records.getString("city");
				ci.add(cit);
			}
			combocity.getItems().addAll(ci);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   void getptype()
   {
	   ArrayList<String> pty=new ArrayList<String>();
   	try {
		pst=con.prepareStatement("Select distinct ptype from properties");
		ResultSet records=pst.executeQuery();
		while(records.next())
		{
			String ptyp=records.getString("ptype");
			pty.add(ptyp);
		}
		combopropertype.getItems().addAll(pty);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   @SuppressWarnings("unchecked")
void addcoloumn()
   {
   	    TableColumn<PropertBean,String> contactcol=new TableColumn<PropertBean, String>("Contact");
   	    contactcol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("contact"));//field name in bean
   	    contactcol.setMinWidth(50);
		
		TableColumn<PropertBean,String> ptypecol=new TableColumn<PropertBean, String>("Property-type");
		ptypecol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("ptype"));//field name in bean
		ptypecol.setMinWidth(150);
		
		TableColumn<PropertBean,String> sizecol=new TableColumn<PropertBean,String>("Size");
		sizecol.setCellValueFactory(new PropertyValueFactory<PropertBean,String>("size"));//field name in bean
		sizecol.setMinWidth(30);
		
		TableColumn<PropertBean,String> widthcol=new TableColumn<PropertBean,String>("Width");
		widthcol.setCellValueFactory(new PropertyValueFactory<PropertBean,String>("width"));//field name in bean
		widthcol.setMinWidth(30);
		
		TableColumn<PropertBean,String> depthcol=new TableColumn<PropertBean, String>("Depth");
		depthcol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("depth"));//field name in bean
		depthcol.setMinWidth(30);
		
		TableColumn<PropertBean,String> lsidecol=new TableColumn<PropertBean, String>("L-side");
		lsidecol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("lside"));//field name in bean
		lsidecol.setMinWidth(30);
		
		TableColumn<PropertBean,String> rsidecol=new TableColumn<PropertBean, String>("R-side");
		rsidecol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("rside"));//field name in bean
		rsidecol.setMinWidth(30);
		
		TableColumn<PropertBean,String> locationcol=new TableColumn<PropertBean, String>("Location");
		locationcol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("location"));//field name in bean
		locationcol.setMinWidth(300);
		
		TableColumn<PropertBean,String> citycol=new TableColumn<PropertBean, String>("City");
		citycol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("city"));//field name in bean
		citycol.setMinWidth(50);
		
		TableColumn<PropertBean,String> amountcol=new TableColumn<PropertBean, String>("Amount");
		amountcol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("amount"));//field name in bean
		amountcol.setMinWidth(50);
		
		TableColumn<PropertBean,String> directioncol=new TableColumn<PropertBean, String>("Direction");
		directioncol.setCellValueFactory(new PropertyValueFactory<PropertBean, String>("direction"));//field name in bean
		directioncol.setMinWidth(30);
		
		TableColumn<PropertBean,LocalDate> doacol=new TableColumn<PropertBean,LocalDate>("Date of Registration");
		doacol.setCellValueFactory(new PropertyValueFactory<PropertBean,LocalDate>("doa"));//field name in bean
	    doacol.setMinWidth(130);
		
		tblview.getColumns().addAll(contactcol,ptypecol,sizecol,widthcol,depthcol,lsidecol,rsidecol,locationcol,citycol,amountcol,directioncol,doacol);
   }
   
    Connection con;
    PreparedStatement pst;
    ObservableList<PropertBean> list;
    @FXML
    void initialize() {
       con=Connect.getConnection();
       addcoloumn();
       getarea();
       getcity();
       getptype();
    }
}
