package view.panels;

import java.awt.Frame;
import java.awt.event.WindowEvent;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import controller.CategoryController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.BooleanStringConverter;

public class CategoryDetailPane extends GridPane {
	private Button btnOK, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox<String> categoryField;
	private CategoryController controller;

	public CategoryDetailPane(CategoryController controller, Stage stage) {
		this.controller = controller;
		
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Title:"), 0, 0, 1, 1);
		titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);

		this.add(new Label("Description:"), 0, 1, 1, 1);
		descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);

		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		categoryField = new ComboBox<String>();
		categoryField.getItems().addAll("Main","Sub");
		this.add(categoryField, 1, 2, 1, 1);

		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(new setCancelAction());
		this.add(btnCancel, 0, 3, 1, 1);

		btnOK = new Button("Save");
		btnOK.isDefaultButton();
		btnOK.setOnAction(new setSaveAction());
		this.add(btnOK, 1, 3, 1, 1);
		
	}
	
	class setSaveAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			try{
			String title = titleField.getText();
			String description = descriptionField.getText();
			controller.AddCategorie(title, description, true);
			} catch (Exception e) {
				Scene scene = new Scene(new GridPane());
				showAlert(Alert.AlertType.ERROR, scene.getWindow(), "Form Error!", e.getMessage());
			}
		}
	}
	
	
	class setCancelAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
		controller.closeCatergoryDetail();
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
