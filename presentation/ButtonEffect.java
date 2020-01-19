package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonEffect {

	public void enterEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	public void defaultEffect(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#000000"));
	}
	
	public void enterEffectGrey(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

		obj.setTextFill(Color.web("#FFFFFF"));
	}

	public void defaultEffectGrey(Button obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#dddddd"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

		obj.setTextFill(Color.web("#868686"));
	}
	public void enterEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

	}
	public void defaultEffect(ComboBox obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

	}
	public void enterEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#707070"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);

		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.HAND);

	}
	public void defaultEffect(TextField obj) {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10), Insets.EMPTY);
		Background background = new Background(background_fill);
		
		obj.setBackground(background);
		obj.setStyle("-fx-border-radius: 5; -fx-border-color: #707070; -fx-border-width: 3;");
		obj.setCursor(Cursor.DEFAULT);

	}
	
	
}
