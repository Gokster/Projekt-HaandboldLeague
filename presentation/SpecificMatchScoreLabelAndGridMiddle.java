package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SpecificMatchScoreLabelAndGridMiddle {

	public SpecificMatchScoreLabelAndGridMiddle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(); 
		obj.setText(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 45));
		obj.setTextFill(Color.web("#707070"));
//		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		
		grid.setStyle(
				"-fx-border-radius: 0 0 0 0; -fx-border-color: #707070; -fx-border-width: 1 1 3 1; -fx-background-radius: 0 0 0 0; "
				+ "-fx-background: -fx-accent; -fx-background-color: #FFFFFF;");

		grid.setAlignment(Pos.CENTER);
		grid.setPrefWidth(408);
		
		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
}