package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MatchMakingTextField {
	public MatchMakingTextField(GridPane grid, int row, int col, TextField obj) {
		
		obj.getStylesheets().add("/presentation/MatchMakingTextFieldCss.css");
		
		obj.setFont(Font.font("Calibri", 24));
		obj.setAlignment(Pos.CENTER);
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void enterEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

	}

	private void defaultEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

	}
}
