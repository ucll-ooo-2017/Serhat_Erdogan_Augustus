package view.panels;

import controller.Controller;
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

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private Controller controller;
	
	public QuestionOverviewPane(Controller controller) {
		this.controller = controller;
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn questionCol = new TableColumn<>("Question");
        TableColumn categoryCol = new TableColumn<>("Category");

        questionCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        categoryCol.setCellValueFactory(new PropertyValueFactory("category"));
		
        table.setItems(controller.getQuestionsObservable());
		table.getColumns().addAll(questionCol,categoryCol);
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public void refreshTable(){
		table.getItems().clear();
		table.setItems(controller.getQuestionsObservable());
	}
	
	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
