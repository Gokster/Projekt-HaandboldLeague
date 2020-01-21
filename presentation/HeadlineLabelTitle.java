package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HeadlineLabelTitle {

	public HeadlineLabelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 90));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(900);
		obj.setAlignment(Pos.CENTER);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
}
