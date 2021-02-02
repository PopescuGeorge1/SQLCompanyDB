package Home;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Utils.checkLoginData;
import Utils.credentialsDB;
import javafx.event.ActionEvent;


public class LoginController implements Initializable{

	@FXML
	private Button cancelButton;
	@FXML
	private Label loginMessage;
	@FXML
	private ImageView lockImage;
	@FXML
	private Button loginButton;
	@FXML
	private TextField enterUsername;
	@FXML
	private TextField enterPassword;
	@FXML
	private Button regButton;
	
	
	public void cancelButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
		
	public void loginButtonOnAction(ActionEvent e) {
		String user = enterUsername.getText();
		String pass = enterPassword.getText();

		if(checkLoginData.checkUserPass(user,pass)) {
			createMainPanel();
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.close();
		}
		else {
			loginMessage.setText("Invalid UserName or Password");
		}	
		
	}
	
	public void registerButtonOnAction(ActionEvent e) {
		createRegisterForm();//change registered form on successfull login with main panel
		Stage stage = (Stage) regButton.getScene().getWindow();
		stage.close();
	}
	
	//initialize images
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        File lockFile = new File("imgRes/lock1.png");
        Image lockImg = new Image(lockFile.toURI().toString());
        lockImage.setImage(lockImg);
        
		
	}
	
	public void createRegisterForm() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			Stage registerStage = new Stage();
			registerStage.initStyle(StageStyle.UNDECORATED); //undecorated makes the upper toolbar dissapear
			registerStage.setScene(new Scene(root, 270, 450));
			registerStage.show();
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void createMainPanel() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
			Stage mainStage = new Stage();
			mainStage.initStyle(StageStyle.UNDECORATED);
			mainStage.setScene(new Scene(root, 500, 800));
			mainStage.show();
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
}
