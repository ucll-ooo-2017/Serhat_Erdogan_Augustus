package view.panels;

import controller.Controller;
import handler.EditQuestionHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.domain.Question;

public class QuestionOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private Controller controller = Controller.getInstance();
	private EditQuestionHandler handler;
	private volatile static QuestionOverviewPane uniqueInstance;

	public QuestionOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Questions:"), 0, 0, 1, 1);

		TableColumn questionCol = new TableColumn<>("Question");
		TableColumn categoryCol = new TableColumn<>("Category");

		questionCol.setCellValueFactory(new PropertyValueFactory<>("question"));
		categoryCol.setCellValueFactory(new PropertyValueFactory("category"));

		table = new TableView<>();
		table.setRowFactory(tv -> {
			TableRow<Question> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					Question data = row.getItem();
					QuestionDetailPane detailPane = new QuestionDetailPane(data.getQuestion());
					this.handler = new EditQuestionHandler(detailPane);
					this.handler.open();
				}
			});
			return row;
		});

		table.setPrefWidth(REMAINING);
		table.setItems(controller.getQuestionsObservable());
		table.getColumns().addAll(questionCol, categoryCol);
		this.add(table, 0, 1, 2, 6);

		btnNew = new Button("New");
		btnNew.setId("new");

		this.add(btnNew, 0, 11, 1, 1);
	}

	public static QuestionOverviewPane getInstance() {
		if (uniqueInstance == null) {
			synchronized (QuestionOverviewPane.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new QuestionOverviewPane();
				}
			}
		}
		return uniqueInstance;
	}

	public void refreshTable() {
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
