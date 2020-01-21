package presentation;

import data.DatabaseController;
import entities.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.LeagueRanking;

public class TeamMenu {
	
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();
	private LeagueRanking leagueRanker = new LeagueRanking();
	private Stage primaryStage;

	public TeamMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();

		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		HBox hbox = new HBox(tableMaker(typerOfUser));
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(40));

		VBox OuterBox = new VBox(topBarGrid, hbox);
		OuterBox.setBackground(background());
		OuterBox.setAlignment(Pos.TOP_CENTER);

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}
	
	/***********************************
	 * TABLE
	 ***********************************/
	
	private TableView<Team> tableMaker(String typerOfUser) {
		ObservableList<Team> teamsList = FXCollections.observableArrayList();
		teamsList.addAll(dbController.getAllTeams());

		leagueRanker.LeagueRanker(teamsList);
		
		TableColumn<Team, Integer> teamPlacement = new TableColumn<Team, Integer>("Rank");
		teamPlacement.setCellValueFactory(new PropertyValueFactory<Team, Integer>("ranking"));

		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team");
		teamNameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));

		TableColumn<Team, Integer> teamPointsCol = new TableColumn<Team, Integer>("Points");
		teamPointsCol.setCellValueFactory(new PropertyValueFactory<Team, Integer>("teamPoints"));

		TableView<Team> table = new TableView<Team>();
		tableLayout(table, teamsList);
		accessTeamSchedule(table, typerOfUser);
		
		table.setItems(teamsList);
		table.getColumns().addAll(teamPlacement, teamNameCol, teamPointsCol);
		
		return table;
	}
		
	private TableRow<Team> accessTeamSchedule(TableView<Team> table, String typerOfUser) {
		table.setRowFactory(e -> {
			TableRow<Team> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Team rowData = row.getItem();
					new TeamSchedule(primaryStage, rowData).init(typerOfUser);
				}
			});
			return row;
		});
		return null;
	}
	
	/***********************************
	 * NAVIGATION BUTTONS
	 ***********************************/

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "League Ranking");
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
		Button createTeam = new Button("Create\n Team");
		NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new TeamCreateMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete\n Team");
		NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new TeamDeleteMenu(primaryStage).init(typerOfUser));
	}	
	
	/***********************************
	 * LAYOUT
	 ***********************************/

	private Background background() {
		BackgroundFill background_fill = new BackgroundFill(Color.web("#9A9A9A"), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		return background;
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
	
	private void tableLayout(TableView<Team> table, ObservableList<Team> teamsList) {
		table.getStylesheets().add("/presentation/LeaguesMenuTableViewCss.css");
		table.setPrefWidth(800);
		table.setPrefHeight(69 + (48 * teamsList.size()));
		GridPane.setColumnSpan(table, 3);
		GridPane.setRowSpan(table, 14);
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
