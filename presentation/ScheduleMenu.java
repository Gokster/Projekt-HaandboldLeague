package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import data.DatabaseController;
import entities.Match;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScheduleMenu {
	private Stage primaryStage;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private ArrayList<Match> arrMatches = dbController.getAllMatchesNotDone();
	String typeOfUser;

	public ScheduleMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		typeOfUser = typerOfUser;

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox calenderTimeline = new HBox(readMatchesNotDone());
		calenderTimeline.setAlignment(Pos.BASELINE_CENTER);

		VBox OuterBox = new VBox(topBarGrid, calenderTimeline);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void sortArrayList() {
		Collections.sort(arrMatches, Match.dateCompare);
	}

	private HBox readMatchesNotDone() {
		sortArrayList();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BASELINE_CENTER);
		Date tempDate = arrMatches.get(0).getMatchDate();
		boolean firstDateSet = false;

		for (int i = 0; i < arrMatches.size(); i++) {
			if (arrMatches.get(i).getMatchDate().compareTo(tempDate) > 0) {

				tempDate = arrMatches.get(i).getMatchDate();
				hbox.getChildren().add(matchDateVBox(i));
			} else if (firstDateSet == false) {
				hbox.getChildren().add(matchDateVBox(i));
				firstDateSet = true;
			}
		}
		return hbox;
	}

	private VBox matchDateVBox(int i) {
		Label title = new Label(arrMatches.get(i).getMatchDate().toString());
		DateOfMatchLabel(title);

		if (arrMatches.get(i).getMatchDate().compareTo(Date.valueOf(LocalDate.now())) == 0) {
			title.setTextFill(Color.web("#000000"));
		}

		VBox vbox = new VBox(title);
		vbox.setAlignment(Pos.CENTER);

		for (int j = i; j < arrMatches.size(); j++) {
			if (arrMatches.get(i).getMatchDate().compareTo(arrMatches.get(j).getMatchDate()) == 0) {
				vbox.getChildren().add(infMatchButton(j));
			} else {
				break;
			}
		}
		return vbox;
	}

	private Button infMatchButton(int j) {
		String matchTitle = arrMatches.get(j).getHomeTeam().getTeamName() + " vs. "
				+ arrMatches.get(j).getAwayTeam().getTeamName();
		Button btn = null;

		// Allerede spillede kampe
		if (arrMatches.get(j).getMatchDate().compareTo(Date.valueOf(LocalDate.now())) < 0) {
			btn = new Button(matchTitle);
			MatchButtonsPlayed(btn);
			btn.setOnAction(e -> new SpecificMatchMenu(primaryStage, arrMatches.get(j)).init(matchTitle, typeOfUser));
			// Fremtidige kampe
		} else {
			btn = new Button(matchTitle);
			MatchButtonsNotPlayed(btn);
			btn.setOnAction(e -> new SpecificMatchMenu(primaryStage, arrMatches.get(j)).init(matchTitle, typeOfUser));
		}
		return btn;
	}

	public void DateOfMatchLabel(Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		obj.setUnderline(true);
		obj.setTextFill(Color.web("#57504d"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
	}

	public void MatchButtonsPlayed(Button obj) {

		obj.setFont(Font.font("Calibri", 25));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffectGrey(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffectGrey(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffectGrey(obj));
	}

	public void MatchButtonsNotPlayed(Button obj) {

		obj.setFont(Font.font("Calibri", 25));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Schedule");
		buttonsCRUD(grid, typerOfUser);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
	}

	private void buttonsCRUD(GridPane grid, String typerOfUser) {
		Button createTeam = new Button("Create");
		NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new MatchMakingMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete");
		NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewScheduleDeleteMenu(primaryStage).init(typerOfUser));
	}

	public void NavigationButton(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(170);
		obj.setMinHeight(120);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		grid.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(15);
		grid.setAlignment(Pos.CENTER);
	}

	private Background background() {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
	}

	private void stageMods(Scene scene) {
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
