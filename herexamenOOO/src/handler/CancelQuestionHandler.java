package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;

public class CancelQuestionHandler implements EventHandler<ActionEvent> {

	Stage stage;
	QuestionDetailPane questionDetailPane;
	
	public CancelQuestionHandler(Stage stage,QuestionDetailPane questionDetailPane) {
		this.stage = stage;
		this.questionDetailPane = questionDetailPane;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		stage.close();
	}
}
