package handler;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;

public class EditCategoryHandler  {
	Stage stage;
	Controller controller = Controller.getInstance();
	CategoryOverviewPane categoryOverviewPane = CategoryOverviewPane.getInstance();
	CategoryDetailPane categoryDetailPane;

	public EditCategoryHandler(CategoryDetailPane detailPane) {
		this.categoryDetailPane = detailPane;
	}
	
	public void open() {
		try {
			this.stage = new Stage();
			BorderPane pane = new BorderPane();
			pane.setCenter(categoryDetailPane);
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.show();
			categoryDetailPane.setEditAction(
					new SaveEditCategoryHandler(categoryDetailPane, stage));
			categoryDetailPane.setCancelAction(new CancelCategoryHandler(stage, categoryDetailPane));
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