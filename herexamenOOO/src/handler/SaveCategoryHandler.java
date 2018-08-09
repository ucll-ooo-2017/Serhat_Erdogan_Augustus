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

public class SaveCategoryHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller;
	CategoryOverviewPane categoryOverviewPane;
	CategoryDetailPane categoryDetailPane;
	
	
	public  SaveCategoryHandler(CategoryDetailPane detailPane, CategoryOverviewPane overviewPane, Controller controller,Stage stage) {
		this.controller = controller;
		this.categoryDetailPane = detailPane;
		this.categoryOverviewPane = overviewPane;
		this.stage = stage;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		try {
			String title = categoryDetailPane.getTitle();
			String description = categoryDetailPane.getDescription();
			Boolean mainCategory = categoryDetailPane.isMainCategory();
			controller.AddCategory(title, description, mainCategory);
			categoryDetailPane.emptyFields();
			stage.close();
			categoryOverviewPane.refreshTable();
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