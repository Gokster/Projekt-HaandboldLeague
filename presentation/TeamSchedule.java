package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import data.DatabaseController;
import entities.Match;
import entities.Team;
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

public class TeamSchedule {
	private Stage primaryStage;
	private String typeOfUser;
	private Team team;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private ArrayList<Match> arrMatches = dbController.getAllMatches();
	private ArrayList<Match> teamMatchList = new ArrayList<Match>();

	public TeamSchedule(Stage primaryStage, Team team) {
		this.primaryStage = primaryStage;
		this.team = team;
	}
	
	public void init(String typerOfUser) {
		typeOfUser = typerOfUser;

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox calenderTimeline = new HBox(readMatchesNotDone(team));
		calenderTimeline.setAlignment(Pos.BASELINE_CENTER);

		VBox OuterBox = new VBox(topBarGrid, calenderTimeline);
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}
	
	private ArrayList<Match> specificTeamMatchList(Team team){
		for (int i = 0; i < arrMatches.size(); i++) {
			if(arrMatches.get(i).getHomeTeam().getTeamName().compareTo(team.getTeamName()) == 0) {
				teamMatchList.add(arrMatches.get(i));
			}
		}
		return teamMatchList;
	}

	private void sortArrayList() {
		Collections.sort(teamMatchList, Match.dateCompare);
	}

	/***********************************
	 * HBOX/VBOX 
	 ***********************************/
	
	private HBox readMatchesNotDone(Team team) {
		specificTeamMatchList(team);
		sortArrayList();
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BASELINE_CENTER);
		Date tempDate = teamMatchList.get(0).getMatchDate();
		boolean firstDateSet = false;

		for (int i = 0; i < teamMatchList.size(); i++) {
			if (teamMatchList.get(i).getMatchDate().compareTo(tempDate) > 0) {

				tempDate = teamMatchList.get(i).getMatchDate();
				hbox.getChildren().add(matchDateVBox(i));
			} else if (firstDateSet == false) {
				hbox.getChildren().add(matchDateVBox(i));
				firstDateSet = true;
			}
		}
		return hbox;
	}

	private VBox matchDateVBox(int i) {
		Label title = new Label(teamMatchList.get(i).getMatchDate().toString());
		DateOfMatchLabel(title);

		if (teamMatchList.get(i).getMatchDate().compareTo(Date.valueOf(LocalDate.now())) == 0) {
			title.setTextFill(Color.web("#000000"));
		}

		VBox vbox = new VBox(title);
		vbox.setAlignment(Pos.CENTER);

		for (int j = i; j < teamMatchList.size(); j++) {
			if (teamMatchList.get(i).getMatchDate().compareTo(teamMatchList.get(j).getMatchDate()) == 0) {
				vbox.getChildren().add(infMatchButton(j));
			} else {
				break;
			}
		}
		return vbox;
	}
	
	/***********************************
	 * BUTTONS 
	 ***********************************/

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new LeaguesMenu(primaryStage).init(typerOfUser));
	}
	
	private void buttonsCRUD(GridPane grid, String typerOfUser) {
		Button createTeam = new Button("Create Match");
		NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new MatchMakingMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete Match");
		NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewScheduleDeleteMenu(primaryStage).init(typerOfUser));
	}
	
	private HBox createTeamAndCancelButtons(String typerOfUser) {
		GridPane createTeamGrid = new GridPane();
		gridRowOptions(createTeamGrid);
		Button createTeamButton = new Button("Team Blyat vs. Eriks Plovmaend");
		NewButton(createTeamGrid, 1, 1, createTeamButton);
		//createTeamButton.setOnAction(e -> new ShowMatchReport(primaryStage, match).init(typerOfUser));

		HBox hbox = new HBox(createTeamGrid);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(100));

		return hbox;
	}
	
	private Button infMatchButton(int j) {
		String matchTitle = teamMatchList.get(j).getHomeTeam().getTeamName() + " vs. "
				+ teamMatchList.get(j).getAwayTeam().getTeamName();
		Button btn = null;

		// Allerede spillede kampe
		if (teamMatchList.get(j).getWinningTeam() != 0) {
			btn = new Button(matchTitle);
			MatchButtonsPlayed(btn);
			int lastScene = 1;
			btn.setOnAction(e -> new ShowMatchReport(primaryStage, teamMatchList.get(j), team).init(typeOfUser));
			// Fremtidige kampe
		} else {
			btn = new Button(matchTitle);
			MatchButtonsNotPlayed(btn);
			btn.setOnAction(e -> new SpecificMatchMenu(primaryStage, teamMatchList.get(j)).init(matchTitle, typeOfUser));
		}
		return btn;
	}
	
	/***********************************
	 * BUTTONS APPEARANCE
	 ***********************************/
	
	public void MatchButtonsPlayed(Button obj) {
		obj.setFont(Font.font("Calibri", 18));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffectGrey(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffectGrey(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffectGrey(obj));
	}

	public void MatchButtonsNotPlayed(Button obj) {
		obj.setFont(Font.font("Calibri", 18));
		obj.setPrefWidth(300);
		obj.setPrefHeight(50);
		obj.setAlignment(Pos.CENTER);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));
	}

	public void NewButton(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
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

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * LABEL
	 ***********************************/
	
	public void DateOfMatchLabel(Label obj) {
		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		obj.setUnderline(true);
		obj.setTextFill(Color.web("#57504d"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
	}
	
	/***********************************
	 * TOPBAR ELEMENTS
	 ***********************************/
	
	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Team Schedule");
		buttonsCRUD(grid, typerOfUser);
	}
	
	/***********************************
	 * GRID LAYOUT
	 ***********************************/
	
	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(44);
		grid.setAlignment(Pos.CENTER);
	}

	/***********************************
	 * BACKGROUND COLOR
	 ***********************************/
	
	private Background background() {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
	}

	/***********************************
	 * SETSCENE & TITLE
	 ***********************************/
	
	private void stageMods(Scene scene) {
		primaryStage.setTitle("Main Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
