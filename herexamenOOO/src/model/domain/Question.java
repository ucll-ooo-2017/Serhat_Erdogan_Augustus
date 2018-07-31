package model.domain;

import java.util.ArrayList;

public class Question {

	
	private String question;
	private String Statement;
	private ArrayList<String> Statements = new ArrayList<>();
	private String categorie;
	private String feedback;
	
	
	public Question(String question, String statement, ArrayList<String> statements, String categorie,
			String feedback) {
		
		setQuestion(question);
		setStatement(statement);		
		Statements = statements;
		setCategorie(categorie);
		setFeedback(feedback);	
}

	
	public String getQuestion() {
		return question;
	}
	
	public String getStatement() {
		return Statement;
	}
	
	public ArrayList<String> getStatements() {
		return Statements;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public String getFeedback() {
		return feedback;
	}
	
	public void setQuestion(String question) {
		if(question ==null || question.isEmpty()){
			throw new DomainException("The question can not be empty!");
		}
		this.question = question;
	}
	
	public void setStatement(String statement) {
		if(statement == null || statement.isEmpty()){
			throw new DomainException("The Statements can not be empty!");
		}
		Statement = statement;
	}
	
	public void setCategorie(String categorie) {
		if(categorie ==null){
			throw new DomainException("You have to chose a Categorie!");
		}
		this.categorie = categorie;
	}
	
	public void setFeedback(String feedback) {
		if(feedback== null || feedback.isEmpty()){
			throw new DomainException("Please provide a feedback.");
		}
		this.feedback = feedback;
	}

}
