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

public class SubmitAnswerHandler implements EventHandler<ActionEvent> {

	private Controller controller;
	private Stage stage;
	private TestPane testpane;

	public SubmitAnswerHandler(Controller controller, TestPane testPane, Stage stage) {
		this.controller = controller;
		this.testpane = testPane;
		this.stage = stage;

	}

	@Override
	public void handle(ActionEvent event) {

		try {
			System.out.println(testpane.getAnwser());

			if(controller.lastQuestion()){
				System.out.println("last question");
				stage.close();
			}else{
				stage.close();
				Stage stage = new Stage();
				BorderPane pane = new BorderPane();
				TestPane testPane = new TestPane(controller);
				pane.setCenter(testPane);
				Scene scene = new Scene(pane);
				stage.setScene(scene);
				stage.show();
				testPane.setProcessAnswerAction(new SubmitAnswerHandler(controller,testPane,stage));

			}
			
		} catch (Exception e) {
			Scene scene = new Scene(new GridPane());
			showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", e.getMessage());
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
