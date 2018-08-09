package model.domain;

import java.util.ArrayList;

public class Question {

	private String question;
	private String correctStatement;
	private ArrayList<String> Statements = new ArrayList<>();
	private String category;
	private String feedback;

	public Question(String question,String correctStatement, ArrayList<String> statements, String category, String feedback) {
		setQuestion(question);
		setCorrectStatement(correctStatement);
		this.Statements = statements;
		setCategory(category);
		setFeedback(feedback);
	}

	public String getCorrectStatement() {
		return correctStatement;
	}

	public void setCorrectStatement(String correctStatement) {
		if(correctStatement== null || correctStatement.isEmpty()){
			throw new DomainException("Anwser can not be empty");
		}
		this.correctStatement = correctStatement;
	}

	public String getQuestion() {
		return question;
	}

	public ArrayList<String> getStatements() {
		return Statements;
	}

	public String getCategory() {
		return category;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setQuestion(String question) {
		if (question == null || question.isEmpty()) {
			throw new DomainException("The question can not be empty!");
		}
		this.question = question;
	}

	public void setCategory(String category) {
		if (category == null) {
			throw new DomainException("You have to chose a Categorie!");
		}
		this.category = category;
	}

	public void setFeedback(String feedback) {
		if (feedback == null || feedback.isEmpty()) {
			throw new DomainException("Please provide a feedback.");
		}
		this.feedback = feedback;
	}

}
