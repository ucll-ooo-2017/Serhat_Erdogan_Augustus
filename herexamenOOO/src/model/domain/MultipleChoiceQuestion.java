package model.domain;

import java.util.ArrayList;

import model.strategyQuestion.MultipleChoice;

public class MultipleChoiceQuestion extends Question {

	public MultipleChoiceQuestion(String question, String correctStatement, ArrayList<String> statements,
			String category, String feedback) {
		super(question, correctStatement, statements, category, feedback);
		questionType = new MultipleChoice();
	
	}


}
