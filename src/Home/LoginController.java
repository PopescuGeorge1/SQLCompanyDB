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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
	
	
	public void cancelButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
		
	public void loginButtonOnAction(ActionEvent e) {
		loginMessage.setText("You are trying to login");
		createRegisterForm();
		Stage stage = (Stage) loginButton.getScene().getWindow();
		stage.close();
	}
	
	
	
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
	

}
