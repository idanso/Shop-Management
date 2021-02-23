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
import shop_managment_project.*;

public class View extends Application {
		
	private Stage window;
	private TableView table;
	private Button backB;
	private TextField productName, productNumber, priceForShop, priceForCostumer, costumerName, phoneNumber, deleteProduct;
	private RadioButton notificationForCostumer, sortUp, sortDown, sortOrder;
	private Alert alert;

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
		alert = new Alert(AlertType.NONE);
		Label head = new Label("Choose type of sorting:");
		head.setFont(new Font("Arial", 22));
		sortUp = new RadioButton("Sorting by Alpha-Bet");
		sortDown = new RadioButton("Sorting by revers Alpha-Bet");
		sortOrder = new RadioButton("Sorting by order input");
		Button submmitSorting = new Button ("Submmit");
		submmitSorting.setOnAction(e -> {
			
			if (sortUp.isSelected()==false && sortDown.isSelected()==false && sortOrder.isSelected()==false) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("Choose one of the option");
				alert.show();
			} else {
				mainWindow();
			}
			
		});
		VBox vbox = new VBox();
		vbox.getChildren().addAll(head, sortUp, sortDown, sortOrder, submmitSorting);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 80, 20, 80));
		vbox.setSpacing(10);
		
		
//		FileInputStream input = null;
//		try {
//			input = new FileInputStream("pexels-photo-586744.jpeg");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} 
//		  
//        // create a image 
//        Image image = new Image(input); 
//
//        // create a background image 
//        BackgroundImage backgroundimage = new BackgroundImage(image,  
//                                         BackgroundRepeat.NO_REPEAT,  
//                                         BackgroundRepeat.NO_REPEAT,  
//                                         BackgroundPosition.DEFAULT,  
//                                            BackgroundSize.DEFAULT); 
//
//        // create Background 
//        Background background = new Background(backgroundimage); 
//        
//        vbox.setBackground(background);
		window.setScene(new Scene(vbox));
	}
	
	public void mainWindow() {
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
		notificationForCostumer = new RadioButton("Send notification");
		alert = new Alert(AlertType.NONE);
		
		Label head = new Label("Add product:");
		head.setFont(new Font("Arial", 22));
		
		productName = new TextField();
		productName.setText("Product name");

		productNumber = new TextField();
		productNumber.setPromptText("Product Number (makat)");

		priceForShop = new TextField();
		priceForShop.setPromptText("Price for the shop");

		priceForCostumer = new TextField();
		priceForCostumer.setPromptText("Costumer price");
		
		costumerName = new TextField();
		costumerName.setPromptText("Costumer Name");
		
		phoneNumber = new TextField();
		phoneNumber.setPromptText("Phone number");
		grid.add(head, 0, 0);
		grid.add(productName, 1, 0);
		grid.add(productNumber, 0, 1);
		grid.add(priceForShop, 1, 1);
		grid.add(priceForCostumer, 0, 2);
		grid.add(costumerName, 1, 2);
		grid.add(phoneNumber, 0, 3);
		grid.add(notificationForCostumer, 1, 3);
		grid.add(hbox1, 0, 4);
		grid.add(backB, 1, 4);

		hbox1.getChildren().addAll(submmitB, clearB);
		submmitB.setOnAction(e -> {
			if (productName.getText().isEmpty() || productNumber.getText().isEmpty() || priceForShop.getText().isEmpty() || priceForCostumer.getText().isEmpty()
					|| costumerName.getText().isEmpty() || phoneNumber.getText().isEmpty()) {
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
			productName.clear();
			productNumber.clear();
			priceForShop.clear();
			priceForCostumer.clear();
			costumerName.clear();
			phoneNumber.clear();
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
		deleteProduct = new TextField();
		deleteProduct.setMaxWidth(150);
		deleteProduct.setPromptText("Enter the product number");
		Button submmitSearchB = new Button("Submmit");
		HBox hbox = new HBox(8);
		hbox.getChildren().addAll(submmitSearchB, backB);
		vboxSearch.getChildren().addAll(deleteProduct, hbox, table);
		submmitSearchB.setOnAction(e -> {
			if(deleteProduct.getText().isEmpty()) {
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
	
	public String getProductName() {
		return this.productName.getText();
	}
	
	public String getProductNumber () {
		return this.productNumber.getText();
	}
	
	public int getPriceForShop() {
		return Integer.parseInt(this.priceForShop.getText());
	}
	
	public int getPriceForCostumer() {
		return Integer.parseInt(this.priceForCostumer.getText());
	}
	
	public String getCostumerName() {
		return this.costumerName.getText();
	}
	
	public String getCostumerPhoneNumber() {
		return this.phoneNumber.getText();
	}
	
	public boolean getNewsCostumer () {
		return this.notificationForCostumer.isSelected();
	}
	
	public EProductSortType getTypeOfSorting() {
		if(this.sortUp.isSelected() == true) return EProductSortType.FROM_UP;
		else {
			if(this.sortDown.isSelected() == true) return EProductSortType.FROM_DOWN;
		}
		return EProductSortType.ENTER_ORDER;
	}
	
	public String getDeleteProductNumber () {
		return this.deleteProduct.getText();
	}
	
	
	
	
	
	

}