//@author Serhat Erdogan

package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.domain.Category;
import model.domain.Evaluation;
import model.domain.MultipleChoiceQuestion;
import model.domain.Question;
import model.domain.Score;
import model.domain.YesNoQuestion;
import model.facade.Service;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionOverviewPane;

public class Controller implements Observer {
	private final Service service;
	private int questionNumber = 0;
	private boolean refreshTable;
	private boolean testDone;
	private volatile static Controller uniqueInstance;

	public Controller() {
		refreshTable = false;
		testDone = false;
		service = new Service();
		service.addObserver(this);
/*
		Category cat1 = new Category("Design principles", "The SOLID design principles.", true);
		Category cat2 = new Category("Java", "java extra's.", true);
		ArrayList<String> statements = new ArrayList<>();
		statements.add("juist");
		statements.add("fout");
		statements.add("fout");
		statements.add("fout");
		statements.add("fout");
		Question question1 = new Question(
				"Welke patroon definieert een familie van algoritmes, kapselt ze in een en maakt ze verwisselbaar?",
				"juist", statements, "Java", "Het juiste antwoord is juist");
		Question question2 = new Question("Welk ontwerp principe is het minst van toepassing op het strategy?", "juist",
				statements, "Java", "Het juiste antwoord is juist");
		Question question3 = new Question("hoe laat is het?", "juist", statements, "Java",
				"Het juiste antwoord is juist");
		service.addCategory(cat1);
		service.addCategory(cat2);
		service.addQuestion(question1);
		service.addQuestion(question2);
		service.addQuestion(question3);*/
	}

	public static Controller getInstance() {
		if (uniqueInstance == null) {
			synchronized (Controller.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Controller();
				}
			}
		}
		return uniqueInstance;
	}

	public Service getService() {
		return this.service;
	}

	public void AddCategory(String name, String description, boolean mainCategorie) {
		Category categorie = new Category(name, description, mainCategorie);
		refreshTable = true;
		this.service.addCategory(categorie);
	}

	public void editCategory(String oldName,String oldDescription, String newName, String description, boolean mainCategorie) {
		Category categorie = new Category(newName, description, mainCategorie);
		refreshTable = true;
		this.service.editCategory(oldName,oldDescription, categorie);

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
		Question ques = null;
		if (answers.size() > 2) {
			 ques = new MultipleChoiceQuestion(question, correctStatement, answers, category, feedback);

		} else {
			 ques = new YesNoQuestion(question, correctStatement, answers, category, feedback);

		}
		this.refreshTable = true;
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

	public int getQuestionNumber() {
		return this.questionNumber;
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
	public Score getScoreFeedback() {
		return service.getScore();
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if (this.refreshTable) {
			CategoryOverviewPane.getInstance().refreshTable();
			QuestionOverviewPane.getInstance().refreshTable();
			refreshTable = false;
		}
		if (this.testDone) {
			try {
				this.testDone = false;
				MessagePane.getInstance().showEvaluation();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
