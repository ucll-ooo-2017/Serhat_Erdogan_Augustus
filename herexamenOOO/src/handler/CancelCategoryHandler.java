//@author Serhat Erdogan

package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.panels.CategoryDetailPane;

public class CancelCategoryHandler implements EventHandler<ActionEvent> {

	Stage stage;
	CategoryDetailPane categoryDetailPane;
	
	public CancelCategoryHandler(Stage stage,CategoryDetailPane categoryDetailPane) {
		this.stage = stage;
		this.categoryDetailPane = categoryDetailPane;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		stage.close();
	}
}
