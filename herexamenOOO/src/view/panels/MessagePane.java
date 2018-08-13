package view.panels;

import java.io.IOException;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MessagePane extends GridPane {
	private Button testButton;
	private Text l;
	private Controller controller = Controller.getInstance();
	private volatile static MessagePane uniqueInstance;

	public MessagePane() throws IOException {

		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		testButton = new Button("Evaluate");

		add(testButton, 0, 1, 1, 1);
		l = new Text();
		this.showEvaluation();
		add(l, 0, 0, 1, 1);
		setHalignment(testButton, HPos.CENTER);
	}

	public static MessagePane getInstance() throws IOException {
		if (uniqueInstance == null) {
			synchronized (MessagePane.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new MessagePane();
				}
			}
		}
		return uniqueInstance;
	}

	public void setStartAction(EventHandler<ActionEvent> startAction) {
		testButton.setOnAction(startAction);
	}

	public void showEvaluation() throws IOException {
		if (controller.getEvaluation().getPropValue("test").equals("false")) {
			l.setText("You never did this Test!");

		} else {
			
			l.setText("You already did this Test!");

		}
	}

}
