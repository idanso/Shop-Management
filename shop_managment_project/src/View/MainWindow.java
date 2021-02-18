package View;

import java.awt.Font;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application{
	Stage window;
	Scene starter, addProductScene, sorting, showProduct;
	@Override
	public void init() throws Exception{
		//pop-up to select the sorting
		//read and reload the data from the binary file
	}
	
	@Override
	public void start(Stage mainWindow) throws Exception {
		window = mainWindow;
		window.setTitle("Main window");
		Button addProduct = new Button("Add product");
		addProduct.setOnAction(e -> window.setScene(addProductScene));
		Button showProduct = new Button ("Show all the products");
		Button sendNotification = new Button ("Send notification");
		Button exit = new Button ("EXIT");
		exit.setOnAction(e ->{
			System.out.println("Good bye!");
			System.exit(0);
		});
		VBox vbox = new VBox();
		vbox.getChildren().addAll(addProduct, showProduct, sendNotification, exit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20,80,20,80));
		vbox.setSpacing(10);
		starter = new Scene(vbox);
		
		
		
		//addProduct scene
		GridPane grid = new GridPane();
		HBox hbox1 = new HBox(8);
		Alert alert = new Alert(AlertType.NONE);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(10);
		Button submmitB = new Button("Submmit");
		Button clearB = new Button ("Clear");
		
		Label head = new Label ("Add product:");
		
		TextField txt1 = new TextField();
		txt1.setPromptText("Product Name");
		
		TextField txt2 = new TextField();
		txt2.setPromptText("Price for the shop");
		
		TextField txt3 = new TextField();
		txt3.setPromptText("Costumer price");
		TextField txt4 = new TextField();
		
		txt4.setPromptText("Costumer Name");
		TextField txt5 = new TextField();
		
		txt5.setPromptText("Phone number");
		grid.add(head,0,0);
		grid.add(txt1, 0, 1);
		grid.add(txt2, 1, 1);
		grid.add(txt3, 0, 2);
		grid.add(txt4, 1, 2);
		grid.add(txt5, 0, 3);
		grid.add(hbox1, 0, 4);
		
		hbox1.getChildren().addAll(submmitB, clearB);
		submmitB.setOnAction(e -> {
				if(txt1.getText().isEmpty() || txt2.getText().isEmpty() ||
						txt3.getText().isEmpty() || txt4.getText().isEmpty() || txt5.getText().isEmpty()) {
					alert.setAlertType(AlertType.ERROR);
					alert.setContentText("Fill all up before the dreadful idan will kill you!");
					alert.show();
				}
		});
		clearB.setOnAction(e -> {
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt4.clear();
			txt5.clear();
		});
		grid.setAlignment(Pos.BASELINE_CENTER);
		addProductScene = new Scene(grid, 500,300);
		
		
		window.setScene(starter);
		window.show();
	}
	
	
	@Override//do the final things after exit the program
	public void stop() throws Exception{
		System.out.println("After the program");
	}

}
