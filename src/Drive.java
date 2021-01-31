import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Drive extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Home/Login.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED); //undecorated makes the upper toolbar dissapear
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
