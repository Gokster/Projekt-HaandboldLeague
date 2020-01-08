package presentation;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SpecificMatchComboBoxSmall {
	public SpecificMatchComboBoxSmall(GridPane grid, int row, int col, ComboBox obj) {
		
		obj.getStylesheets().add("/presentation/SpecificMatchComboBoxSmallCss.css");
		
		obj.setMinWidth(100);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(100);

		obj.getSelectionModel().select(0);
		defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void enterEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.HAND);

	}

	private void defaultEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(7), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5 0 0 5; -fx-border-color: #707070; -fx-border-width: 3 1 3 3;");
		obj.setCursor(Cursor.DEFAULT);

	}
}