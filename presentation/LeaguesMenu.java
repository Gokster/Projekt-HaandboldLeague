package presentation;

import java.util.ArrayList;

import data.DatabaseController;
import entities.Ranking;
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
import javafx.stage.Stage;

public class LeaguesMenu {
	private Stage primaryStage;
	private DatabaseController dbController = new DatabaseController();

	public LeaguesMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();

		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		final ObservableList<Team> teams = FXCollections.observableArrayList();
		teams.addAll(dbController.getAllTeams());

		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team Name");
		teamNameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));

		TableColumn<Team, Integer> teamPointsCol = new TableColumn<Team, Integer>("Team Points");
		teamPointsCol.setCellValueFactory(new PropertyValueFactory<Team, Integer>("teamPoints"));

		// TableColumn<Ranking, Integer> teamPlacement = new TableColumn<Ranking,
		// Integer>("Rank");
//		final ObservableList<Ranking> teamPR = FXCollections.observableArrayList();
//		ArrayList<Ranking> teamRanking = new ArrayList<Ranking>();
//		for (int i = 0; i < teams.size(); i++) {
//			int rank = i;
//			Ranking ranking = new Ranking(rank);
//			teamRanking.add(ranking);
//		}
//		
//		teamPR.addAll(teamRanking);

		TableView<Team> table = new TableView<Team>();
		GridPane.setColumnSpan(table, 3);
		GridPane.setRowSpan(table, 14);

		//select team in row
		table.setRowFactory(e -> {
			TableRow<Team> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					int rowData = row.getItem().getTeamId();
					//syso for test -> call Team specific schedule instead
		            System.out.println(rowData);
		            return rowData;
					new MainMenu(primaryStage).init(typerOfUser);
				}
			});
			return row;
		});
		
		table.setItems(teams);
		table.getColumns().addAll(teamNameCol, teamPointsCol);
		topBarGrid.getChildren().add(table);

		HBox hbox = new HBox(table);
		hbox.setAlignment(Pos.CENTER);

		VBox OuterBox = new VBox(topBarGrid, hbox);
		OuterBox.setBackground(background());
		OuterBox.setAlignment(Pos.TOP_CENTER);

		Scene scene = new Scene(OuterBox, 1800, 1000);
		stageMods(scene);
	}

	private void topBarElements(GridPane grid, String typerOfUser) {
		buttonsNavigation(grid, typerOfUser);
		new HeadlineLabelTitle(grid, 3, 1, "League Rankings");
		buttonsCRUD(grid, typerOfUser);
	}

	private void buttonsNavigation(GridPane grid, String typerOfUser) {
		Button home = new Button("Home");
		new NavigationButton(grid, 1, 1, home);
		home.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));

		Button back = new Button("Back");
		new NavigationButton(grid, 2, 1, back);
		back.setOnAction(e -> new MainMenu(primaryStage).init(typerOfUser));
	}

	private void buttonsCRUD(GridPane grid, String typerOfUser) {
		Button createTeam = new Button("Create Team");
		new NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new NewLeagueCreateMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete Team");
		new NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewLeagueDeleteMenu(primaryStage).init(typerOfUser));
	}

	private void topBarGridOptions(GridPane grid) {
		grid.setHgap(40);
		grid.setVgap(40);
		grid.setAlignment(Pos.CENTER_LEFT);
	}

	private void gridRowOptions(GridPane grid) {
		grid.setHgap(44);
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
