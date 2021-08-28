package FindProperty1;

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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class FindProperty1_FormController {


    @FXML
    private ToggleGroup reg;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PropertyBean> tblview;

    @FXML
    private Button btnshow;

    @FXML
    private Button btnexcel;

    @FXML
    private DatePicker dateregafter;

    @FXML
    private RadioButton raddone;
    
    @FXML
    private RadioButton radpending;

    @FXML
    private RadioButton radall;

    @FXML
    void doshow(ActionEvent event) {
    	    if(radall.isSelected())
    	    {
                show();
    	    }
                else
    		if(raddone.isSelected())
    		{
    			showdone();
    		}
    			else
    			{	showpend();
    }
    }
    void show()
    {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("Select * from matured");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String pi=records.getString("pid");
				String buy=records.getString("buyer");
				String sell=records.getString("seller");
				String total=records.getString("tamount");
				String adv=records.getString("adv");
				String bal=records.getString("bal");
				LocalDate dor=records.getDate("doreg").toLocalDate();
				PropertyBean array=new PropertyBean(pi,buy,sell,total,adv,bal,dor);
				list.addAll(array);	
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    	void showpend()
    	{
    		list=FXCollections.observableArrayList();
				try {
					pst=con.prepareStatement("select * from matured where doreg>CURRENT_DATE");
					ResultSet records=pst.executeQuery();
					while(records.next())
					{
				    String pi=records.getString("pid");
					String buy=records.getString("buyer");
					String sell=records.getString("seller");
					String total=records.getString("tamount");
					String adv=records.getString("adv");
					String bal=records.getString("bal");
					LocalDate dor=records.getDate("doreg").toLocalDate();
					PropertyBean array=new PropertyBean(pi,buy,sell,total,adv,bal,dor);
					list.addAll(array);
					}
					tblview.setItems(list);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
 	void showdone()
 	{list=FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("select * from matured where doreg>=CURRENT_DATE");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
			String pi=records.getString("pid");
			String buy=records.getString("buyer");
			String sell=records.getString("seller");
			String total=records.getString("tamount");
			String adv=records.getString("adv");
			String bal=records.getString("bal");
			LocalDate dor=records.getDate("doreg").toLocalDate();
			PropertyBean array=new PropertyBean(pi,buy,sell,total,adv,bal,dor);
			list.addAll(array);
			}
			tblview.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

    void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	File file = new File("FP.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Pid,Buyer,Seller,Total Amount,Advance,Balance,Date of Registration\n";
            writer.write(text);
            for (PropertyBean p : list) 
            {
    			text = p.getPid()+ "," + p.getBuyer()+ "," + p.getSeller()+ "," + p.getTamount()+"," + p.getAdv()+"," + p.getBal()+ "," + p.getDoreg()+"\n";
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
    @SuppressWarnings("unchecked")
	void addcoloumn()
    {
    	TableColumn<PropertyBean,String> pidcol=new TableColumn<PropertyBean, String>("P-ID");
		pidcol.setCellValueFactory(new PropertyValueFactory<PropertyBean, String>("pid"));//field name in bean
		pidcol.setMinWidth(10);
		
		TableColumn<PropertyBean,String> buyercol=new TableColumn<PropertyBean, String>("Buyer");
		buyercol.setCellValueFactory(new PropertyValueFactory<PropertyBean, String>("buyer"));//field name in bean
		buyercol.setMinWidth(50);
		
		TableColumn<PropertyBean,String> sellercol=new TableColumn<PropertyBean,String>("Seller");
		sellercol.setCellValueFactory(new PropertyValueFactory<PropertyBean,String>("seller"));//field name in bean
		sellercol.setMinWidth(50);
		
		TableColumn<PropertyBean,String> tamtcol=new TableColumn<PropertyBean,String>("Total Amount");
		tamtcol.setCellValueFactory(new PropertyValueFactory<PropertyBean,String>("tamount"));//field name in bean
		tamtcol.setMinWidth(100);
		
		TableColumn<PropertyBean,String> advcol=new TableColumn<PropertyBean, String>("Advance Amount");
		advcol.setCellValueFactory(new PropertyValueFactory<PropertyBean, String>("adv"));//field name in bean
		advcol.setMinWidth(120);
		
		TableColumn<PropertyBean,String> balcol=new TableColumn<PropertyBean, String>("Balance Amount");
		balcol.setCellValueFactory(new PropertyValueFactory<PropertyBean, String>("bal"));//field name in bean
		balcol.setMinWidth(120);
	
		TableColumn<PropertyBean,LocalDate> dorcol=new TableColumn<PropertyBean,LocalDate>("Date of Registration");
		dorcol.setCellValueFactory(new PropertyValueFactory<PropertyBean,LocalDate>("doreg"));//field name in bean
	    dorcol.setMinWidth(200);
		
		tblview.getColumns().addAll(pidcol,buyercol,sellercol,tamtcol,advcol,balcol,dorcol);
    
    }
    Connection con;
    PreparedStatement pst;
    ObservableList<PropertyBean> list;
    @FXML
    void initialize() {
    	con=Connect.getConnection();
    	addcoloumn();
           }
}
