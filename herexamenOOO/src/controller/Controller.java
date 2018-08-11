package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.domain.Category;
import model.domain.Question;
import model.domain.Score;
import model.facade.Service;
import view.panels.TestPane;

public class Controller implements Observer {
	private final Service service;
	private Stage stage;
	private int questionNumber = 0;
	Main main;
	private boolean refreshTable;
	private boolean testDone;
	
	public Controller(Main main) {
		refreshTable = false;
		testDone = false;
		this.main = main;
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
		service.addCategory(cat1);
		service.addCategory(cat2);
		service.addQuestion(question1);
		service.addQuestion(question2);
		service.addQuestion(question3);

	}

	public Service getService() {
		return this.service;
	}

	public void AddCategory(String name, String description, boolean mainCategorie) {
		Category categorie = new Category(name, description, mainCategorie);
		refreshTable = true;
		this.service.addCategory(categorie);
	}

	public void editCategory(String oldName, String newName, String description, boolean mainCategorie) {
		Category categorie = new Category(newName, description, mainCategorie);
		refreshTable = true;
		this.service.editCategory(oldName, categorie);

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

	public Category getCategory(String title) {
		Category category = null;
		for (Category c : this.getCategories()) {

			if (c.getTitle().equals(title)) {
				category = c;
			}
		}
		return category;
	}

	public void editQuestion(String oldQuestion, String question, String answer, ArrayList<String> statements,
			String category, String feedback) {
		Question ques = new Question(question, answer, statements, category, feedback);
		refreshTable = true;
		service.editQuestion(oldQuestion, ques);

	}

	public ObservableList<Category> getCategorieObservable() {
		ObservableList<Category> categories = FXCollections.observableArrayList(service.getCategories());
		return categories;
	}

	// questionController
	public void addQuestion(String question, String correctStatement, ArrayList<String> answers, String category,
			String feedback) {
		Question ques = new Question(question, correctStatement, answers, category, feedback);
		refreshTable = true;
		service.addQuestion(ques);
	}

	public Question getQuestion(String title) {
		Question question = null;
		for (Question c : this.getQuestions()) {

			if (c.getQuestion().equals(title)) {
				question = c;
			}
		}
		return question;
	}

	public ArrayList<Question> getQuestions() {
		return service.getQuestions();
	}

	public ObservableList<Question> getQuestionsObservable() {
		ObservableList<Question> questions = FXCollections.observableArrayList(service.getQuestions());
		return questions;
	}

	public void setQuestionNumberNul() {
		this.questionNumber = 0;
	}

	public String getNextQuestion() {
		String question = "";
		if (!getQuestions().isEmpty()) {
			if (questionNumber < getQuestions().size()) {
				question = getQuestions().get(questionNumber).getQuestion();
				questionNumber++;
			}
		}

		return question;
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

	public void changeQuestionCategory(String oldCategory, String newCategory) {
		for (Question q : service.getQuestions()) {
			if (q.getCategory().equals(oldCategory)) {
				q.setCategory(newCategory);
			}
		}
	}

	public Evaluation getEvaluation() {
		return service.getEvaluation();
	}

	public void newTest() {
		service.newTest();
	}

	public Score getScore() {
		testDone = true;
		return service.getScore();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (this.refreshTable) {
			this.main.getCategoryOverviewPane().refreshTable();
			this.main.getQuestionOverviewPane().refreshTable();
			refreshTable = false;
		}
		if(this.testDone){
			try {
				this.main.getMessagePane().showEvaluation();
				this.testDone= false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
