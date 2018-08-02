package view.panels;

import java.util.ArrayList;
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
import javafx.stage.Stage;

public class TestPane extends GridPane {
	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private Controller controller;
	private int i = 1;

	public TestPane(Controller controller, Stage stage) {
		this.controller = controller;
		this.setPrefHeight(300);
		this.setPrefWidth(750);

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		questionField = new Label();
		questionField.setText(controller.getNextQuestion());

		add(questionField, 0, 0, 1, 1);

		statementGroup = new ToggleGroup();

		for (String s : controller.getStatements(controller.getNextQuestion())) {

			RadioButton rb1 = new RadioButton(s);
			rb1.setToggleGroup(statementGroup);
			add(rb1, 0, i, 1, 1);
			i++;
		}

		submitButton = new Button("Submit");
		add(submitButton, 0, i+1, 1, 1);
	}

	public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		List<String> selected = new ArrayList<String>();
		if (statementGroup.getSelectedToggle() != null) {
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}
}
