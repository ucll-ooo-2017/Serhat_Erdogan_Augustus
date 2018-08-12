package handler;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.TestPane;

public class StartTestHandler implements EventHandler<ActionEvent> {

	Controller controller = Controller.getInstance();
	Stage stage;
	TestPane testPane;
	
	
	public StartTestHandler() {
		
	}

	@Override
	public void handle(ActionEvent event) {
		controller.setQuestionNumberNul();
		testPane = new TestPane(controller);
		if (controller.getQuestions().isEmpty()) {
			Scene scene = new Scene(new GridPane());
			showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", "There are no question! Try to make questions first before taking the test...");
		} else {
			controller.newTest();
			this.stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(testPane);
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			testPane.setProcessAnswerAction(new SubmitAnswerHandler(testPane,stage));
		}

	}
	
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

}
