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

public class EditQuestionHandler  {
	Stage stage;
	Controller controller;
	QuestionOverviewPane questionOverviewPane;
	QuestionDetailPane questionDetailPane;

	public EditQuestionHandler(QuestionDetailPane detailPane, QuestionOverviewPane overviewPane,
			Controller controller) {
		this.controller = controller;
		this.questionDetailPane = detailPane;
		this.questionOverviewPane = overviewPane;
	}
	
	public void open() {
		try {
			this.stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(questionDetailPane);
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			questionDetailPane.setEditAction(
					new SaveEditQuestionHandler(questionDetailPane, questionOverviewPane, controller, stage));
			questionDetailPane.setCancelAction(new CancelQuestionHandler(stage, questionDetailPane));
		} catch (Exception e) {
			Scene scene = new Scene(new GridPane());
			showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", "Oops something whent wrong.. Please close the app and start over.");
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