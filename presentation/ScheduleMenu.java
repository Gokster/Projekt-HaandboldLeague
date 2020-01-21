package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScheduleMenu {
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private Stage primaryStage;
	private String typeOfUser;
	private ArrayList<Match> arrMatches = dbController.getAllMatches();

	public ScheduleMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		VBox OuterBox;
		typeOfUser = typerOfUser;

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		if (arrMatches.size() > 0) {
			HBox calenderTimeline = new HBox(readMatches());
			calenderTimeline.setAlignment(Pos.BASELINE_CENTER);
			OuterBox = new VBox(topBarGrid, calenderTimeline);
			OuterBox.setBackground(background());
		} else {
			OuterBox = new VBox(topBarGrid);
			OuterBox.setBackground(background());
		}

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	/***********************************
	 * MATCH FUNKTIONALITY
	 ***********************************/

	private HBox readMatches() {
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
	
	private void sortArrayList() {
		Collections.sort(arrMatches, Match.dateCompare);
	}
	
	/***********************************
	 * MATCH BUTTONS
	 ***********************************/

	private Button infMatchButton(int j) {
		String matchTitle = arrMatches.get(j).getHomeTeam().getTeamName() + " vs. "
				+ arrMatches.get(j).getAwayTeam().getTeamName();
		Button btn = null;

		if (arrMatches.get(j).getWinningTeam() != 0) {
			btn = new Button(matchTitle);
			matchButtonsPlayed(btn);
			btn.setOnAction(e -> new ShowMatchReport(primaryStage, arrMatches.get(j)).init(typeOfUser));
		} else {
			btn = new Button(matchTitle);
			matchButtonsNotPlayedLayout(btn);
			btn.setOnAction(e -> new SpecificMatchMenu(primaryStage, arrMatches.get(j)).init(matchTitle, typeOfUser));
		}
		return btn;
	}
	
	/***********************************
	 * LABEL LAYOUT
	 ***********************************/

	public void DateOfMatchLabel(Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		obj.setUnderline(true);
		obj.setTextFill(Color.web("#57504d"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
	}
	
	/***********************************
	 * BUTTONS LAYOUT
	 ***********************************/

	public void matchButtonsPlayed(Button obj) {
		obj.setFont(Font.font("Calibri", 18));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffectGrey(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffectGrey(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffectGrey(obj));
	}

	public void matchButtonsNotPlayedLayout(Button obj) {
		obj.setFont(Font.font("Calibri", 18));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));
	}
	
	/***********************************
	 * NAVIGATION BUTTONS
	 ***********************************/

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Schedule");
		buttonsCRUD(grid, typerOfUser);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		navigationButtonLayout(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		navigationButtonLayout(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
	}

	private void buttonsCRUD(GridPane grid, String typerOfUser) {
		Button createTeam = new Button("Create");
		navigationButtonLayout(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new MatchMakingMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete");
		navigationButtonLayout(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewScheduleDeleteMenu(primaryStage).init(typerOfUser));
	}
	
	/***********************************
	 * NAVIGATION LAYOUT
	 ***********************************/

	public void navigationButtonLayout(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(170);
		obj.setMinHeight(120);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	
	/***********************************
	 * GENERAL LAYOUT
	 ***********************************/

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
	
	private Background background() {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}
	
	/***********************************
	 * SCENE
	 ***********************************/

	private void stageMods(Scene scene) {
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
