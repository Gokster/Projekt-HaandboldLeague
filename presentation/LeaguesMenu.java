package presentation;

import java.util.ArrayList;
import java.util.Collections;

import data.DatabaseController;

import entities.Team;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
import javafx.util.Callback;

public class LeaguesMenu {
	private Stage primaryStage;
	private ButtonEffect buttonEffect = new ButtonEffect();
	private DatabaseController dbController = new DatabaseController();

	public LeaguesMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void init(String typerOfUser) {
		GridPane topBarGrid = new GridPane();

		topBarGridOptions(topBarGrid);

		topBarElements(topBarGrid, typerOfUser);

		ObservableList<Team> teamsList = FXCollections.observableArrayList();
		teamsList.addAll(dbController.getAllTeams());
		
		//***Test***
		Team team1 = new Team(1, "Ikast FC", 26);
		Team team2 = new Team(2, "Herning FC", 23);
		Team team3 = new Team(3, "Viborg FC", 20);
		Team team4 = new Team(4, "Vejle FC", 24);
		teamsList.add(team1);
		teamsList.add(team2);
		teamsList.add(team3);
		teamsList.add(team4);
		//***Test***
		
		Collections.sort(teamsList, Collections.reverseOrder());
		
		//Sets ranks for all teams
		for (int i = 0; i < teamsList.size(); i++) {
			teamsList.get(i).setRanking(i+1);
		}
		
		TableColumn<Team, Integer> teamPlacement = new TableColumn<Team, Integer>("Rank");
		teamPlacement.setCellValueFactory(new PropertyValueFactory<Team, Integer>("ranking"));
		
		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team");
		teamNameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));

		TableColumn<Team, Integer> teamPointsCol = new TableColumn<Team, Integer>("Points");
		teamPointsCol.setCellValueFactory(new PropertyValueFactory<Team, Integer>("teamPoints"));
		
		TableView<Team> table = new TableView<Team>();
		table.getStylesheets().add("/presentation/LeaguesMenuTableViewCss.css");
		table.setMinWidth(350);
		GridPane.setColumnSpan(table, 3);
		GridPane.setRowSpan(table, 14);

		// select team in row
		table.setRowFactory(e -> {
			TableRow<Team> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					int rowData = row.getItem().getTeamId();
					// syso for test -> call Team specific schedule instead
					//System.out.println(rowData);

					new ScheduleMenu(primaryStage).init(typerOfUser);
				}
			});
			return row;
		});

		table.setItems(teamsList);
		table.getColumns().addAll(teamPlacement, teamNameCol, teamPointsCol);
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
		Button createTeam = new Button("Create Team");
		NavigationButton(grid, 4, 1, createTeam);
		createTeam.setOnAction(e -> new NewLeagueCreateMenu(primaryStage).init(typerOfUser));

		Button deleteTeam = new Button("Delete Team");
		NavigationButton(grid, 5, 1, deleteTeam);
		deleteTeam.setOnAction(e -> new NewLeagueDeleteMenu(primaryStage).init(typerOfUser));
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
