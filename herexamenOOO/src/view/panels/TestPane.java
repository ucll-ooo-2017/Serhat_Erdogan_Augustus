package view.panels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TestPane extends GridPane {
	private Label questionField,questionNumber;
	private Button submitButton,backButton;
	private ToggleGroup statementGroup;
	private Controller controller = Controller.getInstance();
	private int i = 1;

	public TestPane() {
		
		this.setPrefHeight(300);
		this.setPrefWidth(500);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		questionField = new Label();
		questionField.setId("testTitle");
		questionField.setText(controller.getNextQuestion());
		add(questionField, 0, 0, 1, 1);

		String number =  Integer.toString(controller.getQuestionNumber());
		String maxQuestion = Integer.toString(controller.getQuestions().size());
		questionNumber = new Label("Question: " +number+ "/" + maxQuestion);
		questionNumber.setId("questionNumber");
		add(questionNumber, 6, 0, 1, 1);


		statementGroup = new ToggleGroup();
		ArrayList<String> stats = controller.getStatements(questionField.getText());
		Collections.shuffle(stats);
		for (String s : stats) {

			RadioButton rb1 = new RadioButton(s);
			rb1.setId("radioButton");
			rb1.setToggleGroup(statementGroup);
			rb1.setUserData(s);
			add(rb1, 0, i, 1, 1);
			i++;
		}
	
		submitButton = new Button("Submit");
		add(submitButton, 0, i , 1, 1);
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public String getAnwser() {
		return statementGroup.getSelectedToggle().getUserData().toString();
	}

	public List<String> getSelectedStatements() {
		List<String> selected = new ArrayList<String>();
		if (statementGroup.getSelectedToggle() != null) {
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

	public int getTotalScore() {
		return 0;

	}
}
