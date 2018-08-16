package model.domain;

import java.util.ArrayList;

import model.strategyQuestion.YesNo;

public class YesNoQuestion extends Question {

	public YesNoQuestion(String question, String correctStatement, ArrayList<String> statements, String category,
			String feedback) {
		super(question, correctStatement, statements, category, feedback);
		questionType = new YesNo();
}

}
