package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpecificMatchButtonMedium {

	public SpecificMatchButtonMedium(GridPane grid, int row, int col, Button obj) {

		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(300);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(300);

		defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void enterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	private void defaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 0 5 5 0; -fx-border-color: #707070; -fx-border-width: 3 3 3 1;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#707070"));
	}
}
