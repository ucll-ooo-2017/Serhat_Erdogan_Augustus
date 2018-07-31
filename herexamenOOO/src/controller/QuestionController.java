package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Categorie;
import model.domain.Question;
import model.facade.Service;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;

public class QuestionController implements Observer {

	private final Service service;
	private Stage stage;

	public QuestionController() {
		service = new Service();
		service.addObserver(this);
		this.stage = new Stage();
	}
	public Service getService(){
		return this.service;
	}

	public void addQuestion(String question, String statement, ArrayList<String> statements, String categorie,
			String feedback) {
		Question ques = new Question(question, statement, statements, categorie, feedback);
		service.addQuestion(ques);
	}
	
	public ArrayList<Question> getQuestion(){
		return service.getQuestions();
	}
	
	public void startQuestionDetail(){
		this.stage = new Stage();
		GridPane view = new QuestionDetailPane();
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}

	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}


}
