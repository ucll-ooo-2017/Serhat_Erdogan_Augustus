package view.panels;


import controller.Controller;
import handler.EditCategoryHandler;
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
import model.domain.Category;

public class CategoryOverviewPane extends GridPane {
	private TableView table;
	private Button btnNew;
	private Controller controller = Controller.getInstance();
	private EditCategoryHandler handler;
	private volatile static CategoryOverviewPane uniqueInstance;

	
	public CategoryOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Categories:"), 0, 0, 1, 1);

		// CATEGORY OVERVIEW
		TableColumn nameCol = new TableColumn<>("Name");
		TableColumn descriptionCol = new TableColumn<>("description");

		nameCol.setCellValueFactory(new PropertyValueFactory("title"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));

		table = new TableView<String>();

		table.setRowFactory(tv -> {
			TableRow<Category> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					Category data = row.getItem();
					CategoryDetailPane detailPane = new CategoryDetailPane(data.getTitle(),data.getDescription());
					this.handler = new EditCategoryHandler(detailPane);
					this.handler.open();
				}
			});
			return row;
		});

		table.setPrefWidth(REMAINING);
		table.setItems(controller.getCategorieObservable());
		table.getColumns().addAll(nameCol, descriptionCol);
		this.add(table, 0, 1, 2, 6);

		// ADD NEW BUTTON
		btnNew = new Button("New");
		btnNew.setId("new");
		this.add(btnNew, 0, 11, 1, 1);
	}
	
	public static CategoryOverviewPane getInstance() {
		if (uniqueInstance == null) {
			synchronized (CategoryOverviewPane.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new CategoryOverviewPane();
				}
			}
		}
		return uniqueInstance;
	}

	public void refreshTable() {
		table.getItems().clear();
		table.setItems(controller.getCategorieObservable());
	}

	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

}
