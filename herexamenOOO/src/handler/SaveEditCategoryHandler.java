//@author Serhat Erdogan

package handler;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;

public class SaveEditCategoryHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller = Controller.getInstance();
	CategoryOverviewPane categoryOverviewPane = CategoryOverviewPane.getInstance();
	CategoryDetailPane categoryDetailPane;
	
	
	public  SaveEditCategoryHandler(CategoryDetailPane detailPane,Stage stage) {
		this.categoryDetailPane = detailPane;
		this.stage = stage;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		try {
			String oldTitle = categoryDetailPane.getOldTitle();
			String oldDescription = categoryDetailPane.getOldDescription();
			String title = categoryDetailPane.getTitle();
			String description = categoryDetailPane.getDescription();
			Boolean mainCategory = categoryDetailPane.isMainCategory();
			controller.editCategory(oldTitle,oldDescription, title, description, mainCategory);
			controller.changeQuestionCategory(oldTitle, title);
			stage.close();
			//categoryOverviewPane.refreshTable();
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