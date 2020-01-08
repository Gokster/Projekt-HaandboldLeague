package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MainMenuLabelTitle {

	public MainMenuLabelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 130)); 
		obj.setTextFill(Color.web("#707070"));
		
		grid.setColumnSpan(obj, 2);
		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
}
