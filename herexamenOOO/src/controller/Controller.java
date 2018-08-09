package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Question;
import model.facade.Service;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;
import view.panels.TestPane;

public class Controller implements Observer {
	private final Service service;
	private Stage stage;
	private int questionNumber = 0;

	public Controller() {
		service = new Service();
		service.addObserver(this);
		this.stage = new Stage();

		Category cat1 = new Category("Design principles", "The SOLID design principles.", true);
		Category cat2 = new Category("Java", "java extra's.", true);
		ArrayList<String> statements = new ArrayList<>();
		statements.add("Daarom");
		statements.add("Gewoon");
		statements.add("zwijg");
		statements.add("geen idee");
		Question question1 = new Question("Waarom ben ik hier?", "Daarom", statements, "Java", "Zwijg gwn");
		Question question2 = new Question("Hoe oud ben je?", "Daarom", statements, "Java", "Zwijg gwn");
		Question question3 = new Question("hoe laat is het?", "Daarom", statements, "Java", "Zwijg gwn");
		service.addCategorie(cat1);
		service.addCategorie(cat2);
		service.addQuestion(question1);
		service.addQuestion(question2);
		service.addQuestion(question3);

	}

	public Service getService() {
		return this.service;
	}

	public void AddCategorie(String name, String description, boolean mainCategorie) {
		Category categorie = new Category(name, description, mainCategorie);
		this.service.addCategorie(categorie);

	}

	public ArrayList<Category> getCategories() {
		return service.getCategories();
	}

	public ArrayList<String> getCategoriesTitle() {
		ArrayList<String> categories = new ArrayList<String>();
		for (Category s : this.service.getCategories()) {
			categories.add(s.getTitle());
		}
		return categories;
	}

	public ObservableList<Category> getCategorieObservable() {
		ObservableList<Category> categories = FXCollections.observableArrayList(service.getCategories());
		return categories;
	}

	// questionController
	public void addQuestion(String question, String statement, ArrayList<String> statements, String category,
			String feedback) {
		Question ques = new Question(question, statement, statements, category, feedback);
		service.addQuestion(ques);
	}

	public ArrayList<Question> getQuestions() {
		return service.getQuestions();
	}

	public ObservableList<Question> getQuestionsObservable() {
		ObservableList<Question> questions = FXCollections.observableArrayList(service.getQuestions());
		return questions;
	}

	public String getNextQuestion() {
		String question = "";
		if (!getQuestions().isEmpty()) {
			if (questionNumber < getQuestions().size()) {
				question = getQuestions().get(questionNumber).getQuestion();
				questionNumber++;
			}
		}
		System.out.println(questionNumber + ": " + question);
		return question;
	}

	public Boolean lastQuestion() {
		if (questionNumber == getQuestions().size()) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean submitAnwser() {
		return true;
	}

	public ArrayList<String> getStatements(String question) {
		ArrayList<String> statements = new ArrayList<String>();
		for (Question cat : service.getQuestions()) {
			if (cat.getQuestion().equals(question)) {
				statements = cat.getStatements();
			}
		}
		return statements;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}
}
