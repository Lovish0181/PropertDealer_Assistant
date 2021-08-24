package PicGallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PicGallery_FormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboproperty;

    @FXML
    private Button btnfetch;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img2;

    @FXML
    private Button btnpic4;

    @FXML
    private Button btnpic3;

    @FXML
    private Button btnpic2;

    @FXML
    private Button btnpic1;

    @FXML
    private Button btnsave;

    @FXML
    private Button btnupdate;

    @FXML
    void docomboproperty(ActionEvent event) {
    comboproperty.getSelectionModel().getSelectedItem();
    }

    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("Select * from gallery where pid=?");
			pst.setString(1,comboproperty.getEditor().getText());
			ResultSet records=pst.executeQuery();
			if(records.next())
			{
				String gpid=records.getString("pid");
				String gpic1=records.getString("pic1");
				String gpic2=records.getString("pic2");
				String gpic3=records.getString("pic3");
				String gpic4=records.getString("pic4");
				comboproperty.getEditor().setText(gpid);
				File file1=new File(gpic1);
				try {
					img1.setImage(new Image(new FileInputStream(file1)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file2=new File(gpic2);
				try {
					img2.setImage(new Image(new FileInputStream(file2)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file3=new File(gpic3);
				try {
					img3.setImage(new Image(new FileInputStream(file3)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				File file4=new File(gpic4);
				try {
					img4.setImage(new Image(new FileInputStream(file4)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
String pic1;
String pic2;
String pic3;
String pic4;
    @FXML
    void dopic1(ActionEvent event) {
    FileChooser chooser=new FileChooser();
    chooser.getExtensionFilters().addAll(
    		new FileChooser.ExtensionFilter("All Images","*.*"),
    		new FileChooser.ExtensionFilter("JPEG","*.jpg"),
    		new FileChooser.ExtensionFilter("PNG","*.png"),
    		new FileChooser.ExtensionFilter("*.*","*.*")
    		);
    File file=chooser.showOpenDialog(null);
    pic1=file.getAbsolutePath();
    try {
		img1.setImage(new Image(new FileInputStream(file)));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void dopic2(ActionEvent event) {
    	FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("All Images","*.*"),
        		new FileChooser.ExtensionFilter("JPEG","*.jpg"),
        		new FileChooser.ExtensionFilter("PNG","*.png"),
        		new FileChooser.ExtensionFilter("*.*","*.*")
        		);
        File file=chooser.showOpenDialog(null);
        pic2=file.getAbsolutePath();
        try {
    		img2.setImage(new Image(new FileInputStream(file)));
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    }

    @FXML
    void dopic3(ActionEvent event) {
    	FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("All Images","*.*"),
        		new FileChooser.ExtensionFilter("JPEG","*.jpg"),
        		new FileChooser.ExtensionFilter("PNG","*.png"),
        		new FileChooser.ExtensionFilter("*.*","*.*")
        		);
        File file=chooser.showOpenDialog(null);
        pic3=file.getAbsolutePath();
        try {
    		img3.setImage(new Image(new FileInputStream(file)));
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    }

    @FXML
    void dopic4(ActionEvent event) {
    	FileChooser chooser=new FileChooser();
        chooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("All Images","*.*"),
        		new FileChooser.ExtensionFilter("JPEG","*.jpg"),
        		new FileChooser.ExtensionFilter("PNG","*.png"),
        		new FileChooser.ExtensionFilter("*.*","*.*")
        		);
        File file=chooser.showOpenDialog(null);
        pic4=file.getAbsolutePath();
        try {
    		img4.setImage(new Image(new FileInputStream(file)));
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    }

    @FXML
    void dosave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into gallery values(?,?,?,?,?)");
			pst.setString(1,comboproperty.getEditor().getText());
			pst.setString(2,String.valueOf(pic1));
			pst.setString(3,String.valueOf(pic2));
			pst.setString(4,String.valueOf(pic3));
			pst.setString(5,String.valueOf(pic4));
			pst.executeUpdate();
			btnsave.setText("Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doupdate(ActionEvent event) {

		try {
			pst=con.prepareStatement("Update gallery set pic1=?,pic2=?,pic3=?,pic4=? where pid=?");
			pst.setString(5,comboproperty.getEditor().getText());
			pst.setString(1,String.valueOf(pic1));
			pst.setString(2,String.valueOf(pic2));
			pst.setString(3,String.valueOf(pic3));
			pst.setString(4,String.valueOf(pic4));
			
			int count=pst.executeUpdate();
			if(count==0)
				btnupdate.setText("Invalid");
			else
			btnupdate.setText("Updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    }
    void fillproperty()
    {
    	ArrayList<String> pr=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("Select distinct pid from properties");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String pro=records.getString("pid");
				pr.add(pro);
			}
			comboproperty.getItems().addAll(pr);
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
       fillproperty();
    }
}
