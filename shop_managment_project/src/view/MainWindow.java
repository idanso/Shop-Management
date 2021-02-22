package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainWindow extends Application {
	Stage window;
	TableView table;
	Button backB;

	@Override
	public void init() throws Exception {
		table = new TableView();
		TableColumn nameProduct = new TableColumn("Name of product");
		TableColumn priceForShop = new TableColumn("Price for the shop");
		TableColumn priceForCostumer = new TableColumn("Price at the shop");
		TableColumn nameCostumer = new TableColumn("Costumer name");
		TableColumn phoneNumber = new TableColumn("Phone number");
		TableColumn notificationC = new TableColumn("Want to get news?");
		table.getColumns().addAll(nameProduct, priceForShop, priceForCostumer, nameCostumer, phoneNumber,
				notificationC);
		nameProduct.setMinWidth(150);
		priceForShop.setMinWidth(150);
		priceForCostumer.setMinWidth(150);
		nameCostumer.setMinWidth(150);
		phoneNumber.setMinWidth(200);
		notificationC.setMinWidth(150);

		backB = new Button("<<Back");
		backB.setOnAction(e -> mainScene());
		
		// pop-up to select the sorting
		// read and reload the data from the binary file
	}

	public void mainScene() {

		Button addProduct = new Button("Add product");
		addProduct.setOnAction(e -> addProductsScene());

		Button showProduct = new Button("Show all the products");
		showProduct.setOnAction(e -> showProductsScene());

		Button removeProductB = new Button("Search and remove product");
		removeProductB.setOnAction(e -> searchProductToRemove());

		Button sendNotificationsB = new Button("send notifications");
		sendNotificationsB.setOnAction(e -> showReceivedMassages()); // need to add the function to send notification

		Button undoB = new Button("Undo");
		Button exit = new Button("EXIT");
		exit.setOnAction(e -> {
			try {
				stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		});
		VBox vbox = new VBox();
		vbox.getChildren().addAll(addProduct, showProduct, removeProductB, sendNotificationsB, undoB, exit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 80, 20, 80));
		vbox.setSpacing(10);
		
		FileInputStream input = null;
		try {
			input = new FileInputStream("pexels-photo-586744.jpeg");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		  
        // create a image 
        Image image = new Image(input); 

        // create a background image 
        BackgroundImage backgroundimage = new BackgroundImage(image,  
                                         BackgroundRepeat.NO_REPEAT,  
                                         BackgroundRepeat.NO_REPEAT,  
                                         BackgroundPosition.DEFAULT,  
                                            BackgroundSize.DEFAULT); 

        // create Background 
        Background background = new Background(backgroundimage); 
        
        vbox.setBackground(background);
		window.setScene(new Scene(vbox));
	}

	public void addProductsScene() {
		GridPane grid = new GridPane();
		HBox hbox1 = new HBox(8);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(10);
		Button submmitB = new Button("Submmit");
		Button clearB = new Button("Clear");
		RadioButton notification = new RadioButton("Send notification");
		Alert alert = new Alert(AlertType.NONE);
		
		Label head = new Label("Add product:");
		head.setFont(new Font("Arial", 22));

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
		grid.add(head, 0, 0);
		grid.add(txt1, 0, 1);
		grid.add(txt2, 1, 1);
		grid.add(txt3, 0, 2);
		grid.add(txt4, 1, 2);
		grid.add(txt5, 0, 3);
		grid.add(notification, 1, 3);
		grid.add(hbox1, 0, 4);
		grid.add(backB, 1, 4);

		hbox1.getChildren().addAll(submmitB, clearB);
		submmitB.setOnAction(e -> {
			if (txt1.getText().isEmpty() || txt2.getText().isEmpty() || txt3.getText().isEmpty()
					|| txt4.getText().isEmpty() || txt5.getText().isEmpty()) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("Fill all up before the dreadful idan will kill you!");
				alert.show();
			} else {
				alert.setAlertType(AlertType.CONFIRMATION);
				alert.setContentText("The product was successfully added");
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
		window.setScene(new Scene(grid, 500, 300));

	}

	public void showReceivedMassages() {
		VBox receivedMassagesVBox = new VBox(20);
		receivedMassagesVBox.setPadding(new Insets(10, 10, 10, 10));
		Label receivedMassagesL = new Label("Received Customers Massages");
		receivedMassagesL.setFont(new Font("Arial", 22));
		TextFlow massagesTextFlow = new TextFlow();
		massagesTextFlow.setPrefSize(400, 300);
		ScrollPane massagesScrollPane = new ScrollPane(massagesTextFlow);
		receivedMassagesVBox.getChildren().addAll(receivedMassagesL, massagesScrollPane, backB); // need to add backB
		window.setScene(new Scene(receivedMassagesVBox));

	}

	public void showProductsScene() {
		Label label = new Label("All the products:");
		label.setFont(new Font("Arial", 22));
		VBox vboxTable = new VBox(20);
		vboxTable.getChildren().addAll(label, table, backB);
		window.setScene(new Scene(vboxTable, 950, 500));
	}

	public void searchProductToRemove() {
		// search product to remove
		Alert alert = new Alert(AlertType.NONE);
		VBox vboxSearch = new VBox(10);
		TextField makat = new TextField();
		makat.setMaxWidth(150);
		makat.setPromptText("Enter the product number");
		Button submmitSearchB = new Button("Submmit");
		HBox hbox = new HBox(8);
		hbox.getChildren().addAll(submmitSearchB, backB);
		vboxSearch.getChildren().addAll(makat, hbox, table);
		submmitSearchB.setOnAction(e -> {
			if(makat.getText().isEmpty()) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("The product number is empty");
				alert.show();
			}//add the option if the product doesnt exists
		});
		window.setScene(new Scene(vboxSearch, 950, 300));
	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		window = mainWindow;
		window.setTitle("Main window");
		mainScene();
		window.show();

	}

	@Override // do the final things after exit the program
	public void stop() throws Exception {
		System.out.println("Good bye!");
		System.out.println("After the program");
	}

}
