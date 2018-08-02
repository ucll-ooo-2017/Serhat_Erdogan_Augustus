package view.panels;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CategoryOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private Controller controller;

	public CategoryOverviewPane(Controller controller) {
		this.controller = controller;
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Categories:"), 0, 0, 1, 1);


		
		//CATEGORY OVERVIEW
		TableColumn nameCol= new TableColumn<>("Name");
		TableColumn descriptionCol = new TableColumn<>("description");

		nameCol.setCellValueFactory(new PropertyValueFactory("title"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));		

		
		table = new TableView<String>();
		table.setPrefWidth(REMAINING);
		table.setItems(controller.getCategorie());
		table.getColumns().addAll(nameCol,descriptionCol);

		
		
		this.add(table, 0, 1, 2, 6);

		
		
		//ADD NEW BUTTON
		btnNew = new Button("New");
		btnNew.setOnAction(new setNewAction());
		this.add(btnNew, 0, 11, 1, 1);
	}

	class setNewAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.startCatergoryDetail();
		}
	}
	

	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
