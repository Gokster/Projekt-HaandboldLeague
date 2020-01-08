package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MatchMakingLabel {

	public MatchMakingLabel(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		
		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
}
