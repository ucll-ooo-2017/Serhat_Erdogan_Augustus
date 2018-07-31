package view.panels;

import controller.QuestionController;
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
import view.panels.CategoryOverviewPane.setNewAction;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private QuestionController controller;
	
	public QuestionOverviewPane() {
		controller = new QuestionController();
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Questions:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);
        TableColumn nameCol = new TableColumn<>("Question");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);
        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory("category"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);
		
		btnNew = new Button("New");
		btnNew.setOnAction(new setNewAction());
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	class setNewAction implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.startQuestionDetail();
		}
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
