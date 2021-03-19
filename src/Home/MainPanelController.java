package Home;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Utils.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainPanelController implements Initializable{

	@FXML
	private ListView empTable;
	@FXML
	private ListView empWorksWith;
	@FXML
	private Button exitMainPanelBtn;
	@FXML
	private Label listEmpId;
	@FXML
	private Label listEmpFirstName;
	@FXML
	private Label listEmpLastName;
	@FXML
	private TableView<person>tableview=null;
	@FXML
	private TableColumn<person, String>id=null;
	@FXML
	private TableColumn<person, String>fname=null;
	@FXML
	private TableColumn<person, String>lname=null;
	@FXML
	private TableColumn<person, String>birthday=null;
	@FXML
	private TableColumn<person, String>salary=null;
	@FXML
	private TextField filterField=null;
    @FXML
    private TextField txt_user=null;
    @FXML
    private TextField txt_pass=null;
    @FXML
    private TextField txt_salary=null;
    @FXML
    private TextField txt_branch=null;
    @FXML
    private TextField txt_super=null;
    @FXML
    private TextField txt_id=null;
	
	ObservableList<person>dataList = FXCollections.observableArrayList();
	ObservableList<person>listM = FXCollections.observableArrayList();
	int index = -1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	

		updateTable();
		search_user();
		
	}
	
	 public void Delete(ActionEvent ev){
		    Connection conn = ConnectionUtil.conDB();
		    System.out.println("first flag");
		    String delete_SQL = "delete from employees where emp_id = ?;";
		    PreparedStatement Pstat = null;

		        try {
		        	System.out.println("second flag");
		            Pstat = conn.prepareStatement(delete_SQL);
		            Pstat.setString(1, txt_id.getText());
		            Pstat.execute();
		            JOptionPane.showMessageDialog(null, "Delete");
		            updateTable();
		            
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		    
		    }
	//where condition incomplete
	 public void Edit (ActionEvent ev){   
		 PreparedStatement pst = null;
	        try {
	            Connection conn = ConnectionUtil.conDB();
	            String value1 = txt_id.getText();
	            String value2 = txt_user.getText();
	            String value3 = txt_pass.getText();
	            String value4 = txt_salary.getText();
	            String value5 = txt_branch.getText();
	            String value6 = txt_super.getText();
	           
	            String sql = "update employees set emp_id= '"+value1+"',emp_user= '"+value2+"',emp_pass= '"+
	                    value3+"',salary= '"+value4+"',branch_id= '"+value5+"' ,super_id= '"+value6+"' where user_id='"+value1+"' ";
	            pst= conn.prepareStatement(sql);
	            pst.execute();
	            JOptionPane.showMessageDialog(null, "Update");
	            updateTable();
	            search_user();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	        
	    }
	
	@FXML
	void search_user() {          
		updateTable();
		
		dataList = ConnectionUtil.getData();
		tableview.setItems(dataList);
		FilteredList<person> filteredData = new FilteredList<>(dataList, b -> true);  
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}    
				String lowerCaseFilter = newValue.toLowerCase();
				if (person.getId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches id
				}else if (person.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name
				} else if (person.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name
				}else if (person.getBirthday().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches birthday NullPointer!??
				}else if (person.getSalary().toLowerCase().indexOf(lowerCaseFilter)!=-1)
					return true;// Filter matches salary

				else  
					return false; // Does not match.
			});
		});  
		SortedList<person> sortedData = new SortedList<>(filteredData);  
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());  
		tableview.setItems(sortedData);      
	}
	
	private void updateTable() {
		id.setCellValueFactory(new PropertyValueFactory<person,String>("id"));
		fname.setCellValueFactory(new PropertyValueFactory<person,String>("fname"));
		lname.setCellValueFactory(new PropertyValueFactory<person,String>("lname"));
		birthday.setCellValueFactory(new PropertyValueFactory<person,String>("birthday"));
		salary.setCellValueFactory(new PropertyValueFactory<person,String>("salary"));	
	
		listM = ConnectionUtil.getData();
		tableview.setItems(listM);
	}
	
	//getData was here
	
	public void exitButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) exitMainPanelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void getSelected(MouseEvent event) {
		dataList = ConnectionUtil.getData();
		index = tableview.getSelectionModel().getSelectedIndex();
		if(index<=-1) {
			return;
		}
		txt_id.setText(id.getCellData(index).toString());
		txt_user.setText(dataList.get(index).getUser().toString());
		txt_pass.setText(dataList.get(index).getPass().toString());
		txt_salary.setText(dataList.get(index).getSalary().toString());
		txt_branch.setText(dataList.get(index).getBranch().toString());
		txt_super.setText(dataList.get(index).getSupervisor().toString());

	}
