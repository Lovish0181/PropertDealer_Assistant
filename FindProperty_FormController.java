package FindProperty;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    }

    @FXML
    void dogoogle(ActionEvent event) {
    	dofetch();
    }
    void dofetch()
    {
    	list=FXCollections.observableArrayList();
    	
    }
    Connection con;
    PreparedStatement pst;
    ObservableList<PropertBean> list;
    @FXML
    void initialize() {
       con=Connect.getConnection();
    }
}
