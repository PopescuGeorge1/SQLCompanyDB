package Home;

import java.net.URL;
import java.util.ResourceBundle;

import Utils.createNewAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterController implements Initializable{

	@FXML
	private Button cancelButton;
	@FXML
	private Button signInRegData;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField repasswordField;
	@FXML
	private Label passMatchLabel;
	
	public void cancelButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		createLoginPanel();
	}
	
	public void signInButtonOnAction(ActionEvent e) {
		String user = emailField.getText();
		String pass = passwordField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String checkPass = repasswordField.getText();
		if(pass.equals(checkPass)) {
			if(createNewAccount.createAccount(user, checkPass, firstName, lastName)) {
				System.out.println("Account created successfully");
				createLoginPanel();
				Stage stage = (Stage) signInRegData.getScene().getWindow();
				stage.close();
			}
			else
				System.out.println("Account not created");
		}
		else {
			passMatchLabel.setText("Passwords don't match");
		}
	}
	
//	private String logIn() {
//		String status = "Success";
//		String email= userField.getText().toString();
//		String pass = passField.getText().toString();

//		String sql = "SELECT * FROM employees where emp_mail="+email+" and emp_pass="+pass+";";
//
//		try {
//			prepStat = con.prepareStatement(sql);
//			prepStat.setString(1, email);
//			prepStat.setString(2, pass);
//			resSet = prepStat.executeQuery();
//			if(!resSet.next())
//			{
//				errorLbl.setText("Enter Correct Email/Password");
//				status="Error";
//			}
//			else
//			{
//				errorLbl.setText("Login Successfull");
//				return "Success";
//				
//			}
//		}catch(Exception e) {}
//		return status;
//	}
	
	public void createLoginPanel() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage registerStage = new Stage();
			registerStage.initStyle(StageStyle.UNDECORATED); //undecorated makes the upper toolbar dissapear
			registerStage.setScene(new Scene(root, 600, 400));
			registerStage.show();
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
