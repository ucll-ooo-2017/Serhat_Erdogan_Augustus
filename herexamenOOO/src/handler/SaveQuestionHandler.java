//@author Serhat Erdogan

package handler;

import java.util.ArrayList;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;

public class SaveQuestionHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller = Controller.getInstance();
	QuestionOverviewPane questionOverviewPane = QuestionOverviewPane.getInstance();
	QuestionDetailPane questionDetailPane;
	
	
	public  SaveQuestionHandler(QuestionDetailPane detailPane,Stage stage) {
		this.questionDetailPane = detailPane;
		this.stage = stage;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
	
		try {
			String question = questionDetailPane.getQuestion();
			String answer = questionDetailPane.getStatement();
			String categorie = (String) questionDetailPane.getCategory();
			String feedback = questionDetailPane.getFeedback();
			ArrayList<String> statements = questionDetailPane.getStatements();

			if(statements.size()<2){
				throw new IllegalArgumentException("There has to be atleast 2 statements!");
			}
			controller.addQuestion(question,answer, statements, categorie, feedback);
			stage.close();
			//questionOverviewPane.refreshTable();
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