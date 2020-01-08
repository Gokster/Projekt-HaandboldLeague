package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MatchMakingMenu {
	private Stage primaryStage;

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

		Scene scene = new Scene(OuterBox, 1800, 1100);
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
		team1Dropdowns(gridOptions);

		VBox vbox = new VBox(gridLabel, gridOptions);

		return vbox;
	}

	private void team1Dropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("Herning IF");
		leagueCB.getItems().add("København IF");
		leagueCB.getItems().add("Give IF");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);
		
		ComboBox teamCB = new ComboBox();
		teamCB.getItems().add("Team 1");
		teamCB.getItems().add("Team 2");
		teamCB.getItems().add("Team 3");
		new MatchMakingComboBox(grid, 1, 2, teamCB);
	}
	
	private void typeDropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("Show Match");
		leagueCB.getItems().add("League Match");
		new MatchMakingComboBox(grid, 1, 1, leagueCB);
	}
	
	private void halftimesDropdowns(GridPane grid) {
		ComboBox leagueCB = new ComboBox();
		leagueCB.getItems().add("1");
		leagueCB.getItems().add("2");
		leagueCB.getItems().add("3");
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
		
		GridPane dateCBGrid = new GridPane();
		gridRowOptions(dateCBGrid);
		ComboBox dateCB = new ComboBox();
		dateCB.getItems().add("27");
		dateCB.getItems().add("28");
		dateCB.getItems().add("29");
		dateCB.getItems().add("30");
		new MatchMakingComboBox(dateCBGrid, 1, 2, dateCB);
		
		VBox vbox = new VBox(dateLabelGrid, dateCBGrid);
		
		return vbox;
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
		
		VBox vbox = new VBox(doneGrid);
		
		return vbox;
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
