package presentation;

import java.time.LocalDate;

import data.DataLayer;
import data.DatabaseController;
import data.Teams;
import entities.Team;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MatchMakingMenu {
	private Stage primaryStage;
	ComboBox team1CB;
	ComboBox team2CB;
	Button dateButton;

	public MatchMakingMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {

		GridPane topBarGrid = new GridPane();
		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox row1 = new HBox(team1(), middleSelections(), team2());
		row1.setAlignment(Pos.CENTER);

		HBox row2 = new HBox(dateOfMatch(), timeOuts(), matchTitle());
		row2.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, row1, row2, done());
		OuterBox.setBackground(background());

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "Matchmaking");
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		new NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		new NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
	}

	private VBox team1() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new MatchMakingLabelTitle(gridLabel, 1, 1, "Team 1");

		GridPane gridOptions = new GridPane();
		gridRowOptions(gridOptions);
		team1Dropdowns(gridOptions);

		VBox vbox = new VBox(gridLabel, gridOptions);

		return vbox;
	}

	private void team1Dropdowns(GridPane grid) {

		// Nice To Have
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("League 1");
		leagueCB.getItems().add("League 2");
		leagueCB.getItems().add("League 3");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);

		team1CB = new ComboBox();
		team1CB.getItems().add(readTeams());
		new MatchMakingComboBox(grid, 1, 2, team1CB);
	}

	private VBox middleSelections() {
		GridPane typeLabel = new GridPane();
		gridRowOptions(typeLabel);
		new MatchMakingLabel(typeLabel, 1, 1, "Type");

		GridPane typeOptions = new GridPane();
		gridRowOptions(typeOptions);
		typeDropdowns(typeOptions);

		GridPane halftimesLabel = new GridPane();
		gridRowOptions(halftimesLabel);
		new MatchMakingLabel(halftimesLabel, 1, 1, "Halftimes");

		GridPane halftimesOptions = new GridPane();
		gridRowOptions(halftimesOptions);
		halftimesDropdowns(halftimesOptions);

		GridPane gameTimeLabel = new GridPane();
		gridRowOptions(gameTimeLabel);
		new MatchMakingLabel(gameTimeLabel, 1, 1, "Game Time");

		GridPane gameTimeOptions = new GridPane();
		gridRowOptions(gameTimeOptions);
		gameTimeDropdowns(gameTimeOptions);

		VBox typeVBox = new VBox(typeLabel, typeOptions);
		VBox talftimesVBox = new VBox(halftimesLabel, halftimesOptions);
		VBox GameTimeVBox = new VBox(gameTimeLabel, gameTimeOptions);

		VBox vbox = new VBox(typeVBox, talftimesVBox, GameTimeVBox);

		return vbox;
	}

	private VBox team2() {
		GridPane gridLabel = new GridPane();
		gridRowOptions(gridLabel);
		new MatchMakingLabelTitle(gridLabel, 1, 1, "Team 2");

		GridPane gridOptions = new GridPane();
		gridRowOptions(gridOptions);
		team2Dropdowns(gridOptions);

		VBox vbox = new VBox(gridLabel, gridOptions);

		return vbox;
	}

	private void team2Dropdowns(GridPane grid) {

		// Nice To Have
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("League 1");
		leagueCB.getItems().add("League 2");
		leagueCB.getItems().add("League 3");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);

		team2CB = new ComboBox();
		team2CB.getItems().add(readTeams());
		new MatchMakingComboBox(grid, 1, 2, team2CB);
	}

	// Nice To Have
	private void typeDropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("Show Match");
		leagueCB.getItems().add("League Match");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);
	}

	// Nice To Have
	private void halftimesDropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("1");
		leagueCB.getItems().add("2");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);
	}

	private void gameTimeDropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("30");
		leagueCB.getItems().add("40");
		leagueCB.getItems().add("50");
		leagueCB.getItems().add("60");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);
	}

	private VBox dateOfMatch() {
		GridPane dateLabelGrid = new GridPane();
		gridRowOptions(dateLabelGrid);
		new MatchMakingLabel(dateLabelGrid, 1, 1, "Date");

		GridPane dateButtonGrid = new GridPane();
		gridRowOptions(dateButtonGrid);

		datePickerButtonMethod(dateButtonGrid, LocalDate.now().toString());

		VBox vbox = new VBox(dateLabelGrid, dateButtonGrid);

		return vbox;
	}

	private void datePickerButtonMethod(GridPane dateButtonGrid, String date) {
		dateButton = new Button(date);
//		new MatchMakingButton(dateButtonGrid, 1, 1, dateButton);

		dateButtonGrid.getChildren().add(dateButton);
		dateButton.setOnAction(e -> {
			dateButtonGrid.getChildren().remove(dateButton);
			datePickerCalendarMethod(dateButtonGrid);
		});
	}

	private void datePickerCalendarMethod(GridPane dateButtonGrid) {
//		DatePicker dp = new DatePicker(LocalDate.now());
//		DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
//		Node popupContent = datePickerSkin.getPopupContent();
//
//		dateButtonGrid.getChildren().add(dp2);
//		dp2.setOnMouseClicked(e -> {
//			datePickerButtonMethod(dateButtonGrid);
//			dateButtonGrid.getChildren().remove(dp2);
//			LocalDate value = dp2.getValue();
//			System.out.println(value);
//		});

		DatePicker dp = new DatePicker();
		DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
		Node popupContent = datePickerSkin.getPopupContent();

		popupContent.setOnMouseClicked(e -> {
			LocalDate i = dp.getValue();
			datePickerButtonMethod(dateButtonGrid, i.toString());
			dateButtonGrid.getChildren().remove(popupContent);
		});

		dateButtonGrid.getChildren().add(popupContent);

	}

	private VBox timeOuts() {
		GridPane timeOutsGrid = new GridPane();
		gridRowOptions(timeOutsGrid);
		new MatchMakingLabel(timeOutsGrid, 1, 1, "Time Outs");

		GridPane timeOutsCBGrid = new GridPane();
		gridRowOptions(timeOutsCBGrid);
		ComboBox timeOutsCB = new ComboBox();
		timeOutsCB.getItems().add("1");
		timeOutsCB.getItems().add("2");
		timeOutsCB.getItems().add("3");
		timeOutsCB.getItems().add("4");
		new MatchMakingComboBox(timeOutsCBGrid, 1, 2, timeOutsCB);

		VBox vbox = new VBox(timeOutsGrid, timeOutsCBGrid);

		return vbox;
	}

	private VBox matchTitle() {
		GridPane matchTitleGrid = new GridPane();
		gridRowOptions(matchTitleGrid);
		new MatchMakingLabel(matchTitleGrid, 1, 1, "Match Title");

		GridPane matchTitleOutsCBGrid = new GridPane();
		gridRowOptions(matchTitleOutsCBGrid);
		TextField matchTitleOutsCB = new TextField();
		new MatchMakingTextField(matchTitleOutsCBGrid, 1, 2, matchTitleOutsCB);

		VBox vbox = new VBox(matchTitleGrid, matchTitleOutsCBGrid);

		return vbox;
	}

	private VBox done() {

		GridPane doneGrid = new GridPane();
		Button doneButton = new Button("Done");
		gridRowOptions(doneGrid);
		new MatchMakingButton(doneGrid, 1, 2, doneButton);
		doneButton.setOnAction(e -> System.out.println(""));

		VBox vbox = new VBox(doneGrid);

		return vbox;
	}

	private String readTeams() {

		DataLayer dataLayer = new DataLayer();

		Teams teams = new Teams(dataLayer.getConnection());

		Team team = teams.readTeamById(1);

		String teamName = team.getTeamName();

		return teamName;
	}

	private void createMatch() {
		DatabaseController dbController = new DatabaseController();
//		dbController.createMatch(team1CB.toString(), team2CB.toString(), dateCB);
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
