//@author Serhat Erdogan

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
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;

public class CreateQuestionHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller = Controller.getInstance();
	QuestionOverviewPane questionOverviewPane = QuestionOverviewPane.getInstance();
	QuestionDetailPane questionDetailPane;

	public CreateQuestionHandler( ) {
		
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			this.questionDetailPane = new QuestionDetailPane();

			this.stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(questionDetailPane);
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			questionDetailPane.setSaveAction(
					new SaveQuestionHandler(questionDetailPane, stage));
			questionDetailPane.setCancelAction(new CancelQuestionHandler(stage, questionDetailPane));
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