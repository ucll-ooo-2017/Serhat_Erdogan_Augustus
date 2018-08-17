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
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;

public class CreateCategoryHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller = Controller.getInstance();
	CategoryOverviewPane categoryOverviewPane = CategoryOverviewPane.getInstance();
	CategoryDetailPane categoryDetailPane;

	public CreateCategoryHandler() {
		
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			this.categoryDetailPane = new CategoryDetailPane();

			this.stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(categoryDetailPane);
			Scene scene = new Scene(pane);
			scene.getStylesheets().add(getClass().getResource("DetailPane.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			categoryDetailPane.setSaveAction(
					new SaveCategoryHandler(categoryDetailPane, stage));
			categoryDetailPane.setCancelAction(new CancelCategoryHandler(stage, categoryDetailPane));
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