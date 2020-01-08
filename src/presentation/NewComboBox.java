package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class NewComboBox {
	public NewComboBox(GridPane grid, int row, int col, ComboBox obj) {
		
		obj.getStylesheets().add("/presentation/MatchMakingComboBoxCss.css");
		
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		obj.getSelectionModel().select(0);
		defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void enterEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

	}

	private void defaultEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

	}
}
