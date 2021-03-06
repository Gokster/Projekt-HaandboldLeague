package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import data.DatabaseController;
import entities.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.DatePickerSkin;
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
import logic.Match;

public class MatchCreateMenu {
	
	private Button dateButton;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private Date date;
	private DatabaseController dbController = new DatabaseController();
	private LocalDate selectedDate = LocalDate.now();
	private Stage primaryStage;
	private ArrayList<Team> arrTeams = dbController.getAllTeams();
	private ArrayList<Match> arrMatches = dbController.getAllMatches();
	private ArrayList<Match> checkTeamMatchList = new ArrayList<Match>();
	private ComboBox<String> team1CB;
	private ComboBox<String> team2CB;

	public MatchCreateMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox row1 = new HBox(team1(), team2());
		row1.setAlignment(Pos.CENTER);

		HBox row2 = new HBox(dateOfMatch());
		row2.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, row1, row2, done(typerOfUser));
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	/***********************************
	 * CREATE A MATCH
	 ***********************************/

	private void createMatch() {
		Team team1 = convertCBToTeam(team1CB);
		Team team2 = convertCBToTeam(team2CB);
		dbController.createMatch(team1, team2, date);
	}
	
	private VBox done(String typerOfUser) {
		GridPane doneGrid = new GridPane();
		Button doneButton = new Button("Done");
		gridRowOptions(doneGrid);
		matchMakingButtonLayout(doneGrid, 0, 0, doneButton);
		doneButton.setOnAction(e -> {
			if (team1CB.getValue() != team2CB.getValue()) {
				date = Date.valueOf(selectedDate);
				checkMatchList(arrMatches);
				alertBoxTeamNotAvailable(typerOfUser);
			} else {
				alertBoxTeamTheSame();
			}
		});
		VBox vbox = new VBox(doneGrid);

		return vbox;
	}

	/***********************************
	 * DATEPICKER FUNCTIONALITY
	 ***********************************/

	private VBox dateOfMatch() {
		GridPane dateLabelGrid = new GridPane();

		gridRowOptions(dateLabelGrid);
		newLabel(dateLabelGrid, 0, 0, "Date");

		GridPane dateButtonGrid = new GridPane();
		gridRowOptions(dateButtonGrid);
		dateButtonGrid.setPadding(new Insets(30));

		datePickerButtonMethod(dateButtonGrid, LocalDate.now().toString());

		VBox vbox = new VBox(dateLabelGrid, dateButtonGrid);

		return vbox;
	}

	private void datePickerButtonMethod(GridPane dateButtonGrid, String date) {
		dateButton = new Button(date);
		matchMakingButtonCalendarLayout(dateButtonGrid, 1, 1, dateButton);

		dateButtonGrid.getChildren().add(dateButton);
		dateButton.setOnAction(e -> {
			dateButtonGrid.getChildren().remove(dateButton);
			datePickerCalendarMethod(dateButtonGrid);
		});
	}

	private void datePickerCalendarMethod(GridPane dateButtonGrid) {
		DatePicker dp = new DatePicker();
		DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
		Node popupContent = datePickerSkin.getPopupContent();

		dp.setShowWeekNumbers(false);

		dp.getStylesheets().add("/presentation/MatchMakingDatePicker.css");

		popupContent.setOnMouseClicked(e -> {
			selectedDate = dp.getValue();
			datePickerButtonMethod(dateButtonGrid, selectedDate.toString());
			dateButtonGrid.getChildren().remove(popupContent);
		});
		dateButtonGrid.getChildren().add(popupContent);
	}
	
	/***********************************
	 * TEAM COMBOBOXES
	 ***********************************/

	private VBox team1() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		labelTitle(gridLabel, 1, 1, "Team 1");

		GridPane gridOptions = new GridPane();
		gridRowOptions(gridOptions);
		team1Dropdowns(gridOptions);

		VBox vbox = new VBox(gridLabel, gridOptions);

		return vbox;
	}

	private void team1Dropdowns(GridPane grid) {
		team1CB = new ComboBox<String>();
		for (int i = 0; i < arrTeams.size(); i++) {
			team1CB.getItems().add(arrTeams.get(i).getTeamName());
		}
		matchMakingComboBoxLayout(grid, 1, 2, team1CB);
	}

	private VBox team2() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		labelTitle(gridLabel, 1, 1, "Team 2");

		GridPane gridOptions = new GridPane();
		gridRowOptions(gridOptions);
		team2Dropdowns(gridOptions);

		VBox vbox = new VBox(gridLabel, gridOptions);

		return vbox;
	}

	private void team2Dropdowns(GridPane grid) {
		team2CB = new ComboBox<String>();
		for (int i = 0; i < arrTeams.size(); i++) {
			team2CB.getItems().add(arrTeams.get(i).getTeamName());
		}

		matchMakingComboBoxLayout(grid, 1, 2, team2CB);
	}

	/***********************************
	 * COMBOBOX FUNCTIONALITY
	 ***********************************/

	private ArrayList<Match> checkMatchList(ArrayList<Match> arrMatches) {
		for (int i = 0; i < arrMatches.size(); i++) {
			String homeTeamName = arrMatches.get(i).getHomeTeam().getTeamName();
			String awayTeamName = arrMatches.get(i).getAwayTeam().getTeamName();
			String team1Name = team1CB.getValue();
			String team2Name = team2CB.getValue();
			if (homeTeamName.equals(team1Name) || awayTeamName.equals(team1Name)) {
				checkTeamMatchList.add(arrMatches.get(i));
			}
			if (homeTeamName.equals(team2Name) || awayTeamName.equals(team2Name)) {
				checkTeamMatchList.add(arrMatches.get(i));
			}
		}
		return checkTeamMatchList;
	}
	
	private Team convertCBToTeam(ComboBox<String> cb) {
		Team team = null;
		for (int i = 0; i < arrTeams.size(); i++) {
			if (cb.getValue() == arrTeams.get(i).getTeamName()) {
				team = arrTeams.get(i);
				break;
			}
		}
		return team;
	}
	
	/***********************************
	 * ALERTBOXES
	 ***********************************/
	
	private void alertBoxTeamTheSame() {
		Alert matchExists = new Alert(AlertType.ERROR);
		matchExists.getDialogPane().setPrefHeight(280);
		matchExists.getDialogPane().setPrefWidth(1000);
		matchExists.getDialogPane().getStylesheets()
				.add(getClass().getResource("AlertBoxPopUpCss.css").toExternalForm());
		matchExists.setTitle("Error");
		matchExists.setHeaderText(null);
		matchExists.setContentText("Cloning isn't possible yet.");

		matchExists.showAndWait();
	}
	
	private boolean alertBoxTeamNotAvailable(String typerOfUser) {
		for (int i = 0; i < checkTeamMatchList.size(); i++) {
			Date checkMatchDate = checkTeamMatchList.get(i).getMatchDate();
			if (checkMatchDate.compareTo(date) == 0) {
				Alert matchExists = new Alert(AlertType.ERROR);
				matchExists.getDialogPane().setPrefHeight(280);
				matchExists.getDialogPane().setPrefWidth(1000);
				matchExists.getDialogPane().getStylesheets()
						.add(getClass().getResource("AlertBoxPopUpCss.css").toExternalForm());
				matchExists.setTitle("Error");
				matchExists.setHeaderText(null);
				matchExists.setContentText("One or both teams are not available on the this date " + date + ".");

				matchExists.showAndWait();
				return false;
			}
		}
		createMatch();
		new ScheduleMenu(primaryStage).init(typerOfUser);
		return true;
	}

	/***********************************
	 * LABEL
	 ***********************************/

	public void labelTitle(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);
		obj.setUnderline(true);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void newLabel(GridPane grid, int row, int col, String text) {
		Label obj = new Label(text);

		obj.setFont(Font.font("Calibri", 60));
		obj.setTextFill(Color.web("#707070"));
		obj.setMinWidth(200);
		obj.setAlignment(Pos.CENTER);

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	/***********************************
	 * TEXTFIELD
	 ***********************************/

	public void newTextField(GridPane grid, int row, int col, TextField obj) {
		obj.getStylesheets().add("/presentation/MatchMakingTextFieldCss.css");

		obj.setFont(Font.font("Calibri", 24));
		obj.setAlignment(Pos.CENTER);
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

	/***********************************
	 * MATCHMAKING LAYOUT
	 ***********************************/

	public void matchMakingButtonLayout(GridPane grid, int row, int col, Button obj) {
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

	public void matchMakingButtonCalendarLayout(GridPane grid, int row, int col, Button obj) {
		obj.setFont(Font.font("Calibri", 30));
		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));
	}

	public void matchMakingComboBoxLayout(GridPane grid, int row, int col, ComboBox<String> obj) {
		obj.getStylesheets().add("/presentation/MatchMakingComboBoxCss.css");

		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(60);
		obj.setMaxWidth(120);

		obj.getSelectionModel().select(0);
		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}

	public void comboBoxLayout(GridPane grid, int row, int col, ComboBox<?> obj) {
		obj.getStylesheets().add("/presentation/MatchMakingComboBoxCss.css");

		obj.setMinWidth(400);
		obj.setMinHeight(60);
		obj.setMaxHeight(120);
		obj.setMaxWidth(120);

		obj.getSelectionModel().select(0);
		buttonEffect.defaultEffect(obj);

		obj.onMouseEnteredProperty().set(e -> buttonEffect.enterEffect(obj));
		obj.onMouseExitedProperty().set(e -> buttonEffect.defaultEffect(obj));

		GridPane.setConstraints(obj, row, col);
		grid.getChildren().add(obj);
	}
	
	/***********************************
	 * NAVIGATION BUTTONS
	 ***********************************/

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Matchmaking");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		navigationButtonLayout(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		navigationButtonLayout(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
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

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(15);
		grid.setAlignment(Pos.CENTER);
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
