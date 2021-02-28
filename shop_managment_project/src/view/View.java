package view;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.javafx.tk.PrintPipeline;

import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Model;
import shop_managment_project.*;

public class View extends Application {

	private Stage window;
	
	private TableView table;
	private TableColumn nameProductTable, priceForShopTable, priceForCostumerTable, nameCostumerTable,
	phoneNumberTable, notificationTable;
	
	private Button backB, submmitAddProductB, submmitSorting;
	private Button addProductMainScene, showAllProductsMainScene, searchForRemoveMainScene,
	sendNotificationsMainScene, undoFuncrionMainScene;
	
	private TextField productName, productNumber, priceForShop, priceForCostumer, costumerName, phoneNumber,
			deleteProduct;
	private ToggleGroup TGSorting;
	private RadioButton notificationForCostumer, sortUp, sortDown, sortOrder;
	private Alert alert;
	static Controller controller;
	private Label head;
	private TextFlow massagesTextFlow;
	NotificationHandler nHandler;
	Button bCloseShowMassagesWindows;
	Button bRemoveAllProducts;
//	private EventHandler<ActionEvent> addProduct;
	
	private final static String FILE_NAME = "allProducts.txt";

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage mainWindow) throws Exception {
		Model model = new Model(FILE_NAME);
		controller = new Controller(this, model);
		table = new TableView();
		this.sortUp = new RadioButton();
		sortDown = new RadioButton();
		sortOrder = new RadioButton();
		submmitSorting = new Button("Submmit");
		submmitAddProductB = new Button("Submmit");
		backB = new Button("<<Back");
		backB.setOnAction(e -> mainWindow());
		
		//mainWindow Initialize
		addProductMainScene = new Button("Add product");
		showAllProductsMainScene = new Button("Show all the products");
		searchForRemoveMainScene = new Button("remove product");
		bRemoveAllProducts = new Button("remove all products");
		sendNotificationsMainScene = new Button("show customers with notification");
		undoFuncrionMainScene = new Button("Undo");
		undoFuncrionMainScene.setOnAction(e -> controller.undoProduct());
		
		//addProductScene
		productName = new TextField();
		productNumber = new TextField();
		priceForShop = new TextField();
		priceForCostumer = new TextField();
		costumerName = new TextField();
		phoneNumber = new TextField();
		
		//show all product
		nameProductTable = new TableColumn<>("Name of product");
		priceForShopTable = new TableColumn<>("Price for the shop");
		priceForCostumerTable = new TableColumn<>("Price at the shop");
		nameCostumerTable = new TableColumn<>("Costumer name");
		phoneNumberTable = new TableColumn<>("Phone number");
		notificationTable = new TableColumn<>("Want to get news?");
		
		window = mainWindow;
		window.setTitle("Main window");
		mainScene();
		window.show();

	}


	public void mainScene() {
		
		head = new Label("Choose type of sorting:");
		TGSorting = new ToggleGroup();
		
		sortUp.setText("Sorting by Alpha-Bet");
		sortUp.setToggleGroup(TGSorting);
		
		sortDown.setText("Sorting by revers Alpha-Bet");
		sortDown.setToggleGroup(TGSorting);
		
		sortOrder.setText("Sorting by order input");
		sortOrder.setToggleGroup(TGSorting);
		
		alert = new Alert(AlertType.NONE);
		head.setFont(new Font("Arial", 22));
		submmitSorting.setOnAction(e -> choosSorting());
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
		
		addProductMainScene.setOnAction(e -> addProductsScene());
		
		//TODO delete the second statment and restore first function
		//showAllProductsMainScene.setOnAction(e -> showProductsScene()); 
		showAllProductsMainScene.setOnAction(e -> printProductsToTetminal()); //to delete
		
		searchForRemoveMainScene.setOnAction(e -> searchProductToRemove());
		sendNotificationsMainScene.setOnAction(e -> showReceivedMassages()); // TODO need to add the function to send notification
		bRemoveAllProducts.setOnAction(e -> controller.deleteAllProducts()); // TODO need to add pop up massage
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
		vbox.getChildren().addAll(addProductMainScene, showAllProductsMainScene, searchForRemoveMainScene, bRemoveAllProducts, sendNotificationsMainScene, undoFuncrionMainScene, exit);
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
		Button clearB = new Button("Clear");
		notificationForCostumer = new RadioButton("Send notification");
		alert = new Alert(AlertType.NONE);

		Label headAddProduct = new Label("Add product:");
		headAddProduct.setFont(new Font("Arial", 22));

		productName.setPromptText("Product name");
		productNumber.setPromptText("Product Number (makat)");
		priceForShop.setPromptText("Price for the shop");
		priceForCostumer.setPromptText("Costumer price");
		costumerName.setPromptText("Costumer Name");
		phoneNumber.setPromptText("Phone number");
		
		
		grid.add(headAddProduct, 0, 0);
		grid.add(productName, 1, 0);
		grid.add(productNumber, 0, 1);
		grid.add(priceForShop, 1, 1);
		grid.add(priceForCostumer, 0, 2);
		grid.add(costumerName, 1, 2);
		grid.add(phoneNumber, 0, 3);
		grid.add(notificationForCostumer, 1, 3);
		grid.add(hbox1, 0, 4);
		grid.add(backB, 1, 4);

		hbox1.getChildren().addAll(submmitAddProductB, clearB);
		submmitAddProductB.setOnAction(e -> addProduct());
		clearB.setOnAction(e -> clear());

		grid.setAlignment(Pos.BASELINE_CENTER);
		window.setScene(new Scene(grid, 500, 300));

	}

	public void showReceivedMassages() {
		Stage massageStage = new Stage();
		VBox receivedMassagesVBox = new VBox(20);
		receivedMassagesVBox.setPadding(new Insets(10, 10, 10, 10));
		Label receivedMassagesL = new Label("Received Customers Massages");
		receivedMassagesL.setFont(new Font("Arial", 22));
		nHandler = new NotificationHandler();
		massagesTextFlow = new TextFlow();
		massagesTextFlow.setPrefSize(400, 300);
		massagesTextFlow.getChildren().add(getnHandler());
		ScrollPane massagesScrollPane = new ScrollPane(massagesTextFlow);
		bCloseShowMassagesWindows = new Button();
		bCloseShowMassagesWindows.setText("Close");
		bCloseShowMassagesWindows.setOnAction(e -> massageStage.close());
		receivedMassagesVBox.getChildren().addAll(receivedMassagesL, massagesScrollPane, bCloseShowMassagesWindows); // need to add backB
		massageStage.setScene(new Scene(receivedMassagesVBox));
		massageStage.show();
		Platform.runLater(() -> controller.showCustomersMassages());
		//window.setScene(new Scene(receivedMassagesVBox));

	}

	public void showProductsScene() {
		table.getColumns().addAll(nameProductTable, priceForShopTable, priceForCostumerTable, nameCostumerTable, phoneNumberTable,
				notificationTable);
		nameProductTable.setMinWidth(150);
		priceForShopTable.setMinWidth(150);
		priceForCostumerTable.setMinWidth(150);
		nameCostumerTable.setMinWidth(150);
		phoneNumberTable.setMinWidth(200);
		notificationTable.setMinWidth(150);
		
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
			if (deleteProduct.getText().isEmpty()) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("The product number is empty");
				alert.show();
					
			}
			else
				controller.deleteProduct();// TODO add the option if the product doesnt exists

		});
		window.setScene(new Scene(vboxSearch, 950, 300));
	}



	@Override // do the final things after exit the program
	public void stop() throws Exception { // TODO *****need to add call to close file in shop*****
		System.out.println("Good bye!");
		System.out.println("After the program");
	}

	public void clear() {
		productName.clear();
		productNumber.clear();
		priceForShop.clear();
		priceForCostumer.clear();
		costumerName.clear();
		phoneNumber.clear();
		notificationForCostumer.setSelected(false);
	}

	public String getProductName() {
		return productName.getText();
	}

	public String getProductNumber() {
		return productNumber.getText();
	}

	public int getPriceForShop() {
		int price = -1;
		try {
			price = Integer.parseInt(priceForShop.getText());
		} catch (Exception e) {
			System.out.println("The price for the shop must be a number");
		}
		return price;
	}

	public int getPriceForCostumer() {
		int price = -1;
		try {
			price = Integer.parseInt(priceForCostumer.getText());
		} catch (Exception e) {
			System.out.println("The price for the shop must be a number");
		}
		return price;
	}

	public String getCostumerName() {
		return costumerName.getText();
	}

	public String getCostumerPhoneNumber() {
		return phoneNumber.getText();
	}

	public boolean getNewsCostumer() {
		return notificationForCostumer.isSelected();
	}

	public EProductSortType getTypeOfSorting() {
		if (sortUp.isSelected() == true)
			return EProductSortType.FROM_DOWN;
		else if (sortDown.isSelected() == true) 
			return EProductSortType.FROM_UP;
		else
			return EProductSortType.ENTER_ORDER;
	}

	public String getDeleteProductNumber() {
		return deleteProduct.getText();
	}

	void addProductListener(ActionListener listenerAddProduuct) {

	}

	public void addProduct() {
		if (productName.getText().isEmpty() || productNumber.getText().isEmpty() || priceForShop.getText().isEmpty()
				|| priceForCostumer.getText().isEmpty() || costumerName.getText().isEmpty()
				|| phoneNumber.getText().isEmpty()) {
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Fill all up before the dreadful idan will kill you!");
			alert.show();
		} else {
			
			controller.addProduct();
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setContentText("The product was successfully added");
			alert.show();
			clear();
		}
	}

	public void choosSorting() {
		if (sortUp.isSelected() == false && sortDown.isSelected() == false && sortOrder.isSelected() == false) {
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Choose one of the option");
			alert.show();
		} else {
			controller.createProductsMap();
			mainWindow();
		}
	}
	
	public void printProductsToTetminal() { // to delete
		controller.printAllProducts(); 
	}

	public NotificationHandler getnHandler() {
		return nHandler;
	}

	
	

}
