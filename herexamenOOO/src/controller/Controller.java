package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Categorie;
import model.domain.Question;
import model.facade.Service;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;
import view.panels.TestPane;

public class Controller implements Observer {
	private final Service service;
	private Stage stage;

	public Controller() {
		service = new Service();
		service.addObserver(this);
		this.stage = new Stage();
		Categorie cat1 = new Categorie("Design principles", "The SOLID design principles.", true);
		Categorie cat2 = new Categorie("Java", "java extra's.", true);
		ArrayList<String> statements = new ArrayList<>();
		statements.add("Daarom");
		statements.add("Gewoon");
		statements.add("zwijg");
		statements.add("geen idee");
		Question question1 = new Question("Waarom ben ik hier?", "Daarom",statements , "Java", "Zwijg gwn");
		service.addCategorie(cat1);
		service.addCategorie(cat2);
		service.addQuestion(question1);

	}

	public Service getService() {
		return this.service;
	}

	public void AddCategorie(String name, String description, boolean mainCategorie) {
		Categorie categorie = new Categorie(name, description, mainCategorie);
		this.service.addCategorie(categorie);
		closeCatergoryDetail();
	}

	public ArrayList<Categorie> getCategories() {
		return service.getCategories();
	}

	public ArrayList<String> getCategoriesTitle() {
		ArrayList<String> categories = new ArrayList<String>();
		for (Categorie s : this.service.getCategories()) {
			categories.add(s.getTitle());
		}
		return categories;
	}

	public void startCatergoryDetail() {
		this.stage = new Stage();
		GridPane view = new CategoryDetailPane(this, stage);
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}

	public void closeCatergoryDetail() {
		this.stage.close();
	}

	public ObservableList<Categorie> getCategorie() {
		ObservableList<Categorie> categories = FXCollections.observableArrayList();
		for (Categorie cat : service.getCategories()) {
			categories.add(cat);
		}
		return categories;
	}

	// questionController
	public void addQuestion(String question, String statement, ArrayList<String> statements, String categorie,
			String feedback) {
		Question ques = new Question(question, statement, statements, categorie, feedback);
		service.addQuestion(ques);
		closeQuestionDetail();
	}

	public ArrayList<Question> getQuestion() {
		return service.getQuestions();
	}

	public String getNextQuestion() {
		return "Waarom ben ik hier?";
	}
	
	public ArrayList<String> getStatements(String question){
		ArrayList<String> statements = new ArrayList<String>();
		for(Question cat: service.getQuestions()){
			if(cat.getQuestion().equals(question)){
				statements =cat.getStatements();
			}
		}
		return statements;
		
	}

	public void startQuestionDetail() {
		this.stage = new Stage();
		GridPane view = new QuestionDetailPane(this, stage);
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}

	public void closeQuestionDetail() {
		this.stage.close();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	// MessagePane
	public void startTest() {
		this.stage = new Stage();
		GridPane view = new TestPane(this, stage);
		Scene scene = new Scene(view);
		stage.setScene(scene);
		stage.show();
	}

}
