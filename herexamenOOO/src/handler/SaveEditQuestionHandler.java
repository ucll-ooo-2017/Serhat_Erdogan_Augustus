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
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;

public class SaveEditQuestionHandler implements EventHandler<ActionEvent> {
	Stage stage;
	Controller controller;
	QuestionOverviewPane questionOverviewPane;
	QuestionDetailPane questionDetailPane;
	
	
	public  SaveEditQuestionHandler(QuestionDetailPane detailPane, QuestionOverviewPane overviewPane, Controller controller,Stage stage) {
		this.controller = controller;
		this.questionDetailPane = detailPane;
		this.questionOverviewPane = overviewPane;
		this.stage = stage;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		try {
			String oldQuestion = questionDetailPane.getOldQuestion();
			String question = questionDetailPane.getQuestion();
			String answer = questionDetailPane.getStatement();
			String categorie = (String) questionDetailPane.getCategory();
			String feedback = questionDetailPane.getFeedback();
			ArrayList<String> statements = questionDetailPane.getStatements();
			System.out.println("question: " +question +"  statements: "+ statements);
			controller.editQuestion(oldQuestion,question,answer, statements, categorie, feedback);
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