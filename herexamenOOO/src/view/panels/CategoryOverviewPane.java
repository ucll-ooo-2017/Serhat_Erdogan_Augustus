package view.panels;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import controller.CategoryController;
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
	private CategoryController controller;

	public CategoryOverviewPane() {
		controller = new CategoryController();
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Categories:"), 0, 0, 1, 1);

		table = new TableView<>();
		table.setPrefWidth(REMAINING);
		
		TableColumn<List,String> nameCol = new TableColumn<List,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("title"));
		table.getColumns().add(nameCol);
		TableColumn descriptionCol = new TableColumn<>("description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));		
		table.getColumns().add(descriptionCol);
		
		this.add(table, 0, 1, 2, 6);

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
