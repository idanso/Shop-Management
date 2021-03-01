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

	private TableColumn nameProductTable, 
						priceForShopTable, 
						priceForCostumerTable, 
						nameCostumerTable,
						phoneNumberTable, 
						notificationTable;
	
	private Button backB, 
				   submitAddProductB, 
				   submitSorting,
				   submitSearchB,
				   addProductMainScene, 
	 			   showAllProductsMainScene, 
	 			   searchForRemoveMainScene,
	 			   sendNotificationsMainScene, 
	 			   undoFunctionMainScene,
	 			   bShowProfitSummaryScene,
	 			   bRemoveAllProducts,
	 			   bCloseShowMassagesWindows;
	
	private TextField productName, 
	                  productNumber, 
	                  priceForShop, 
	                  priceForCostumer, 
	                  costumerName, 
	                  phoneNumber,
	                  deleteProduct;

	private ToggleGroup TGSorting;

	private RadioButton notificationForCostumer, 
						sortUp, 
						sortDown, 
						sortOrder;
	
	
	private Label head, 
				  headAddProduct,
				  profitSummaryLabel, 
				  profitSummaryHeadLabel,
				  searchToRemove;
	
	private VBox vboxSorting, vboxSearch ;
	private HBox hboxAddProduct, hboxSearchProduct;
	
	private GridPane gridAddProduct;
	
	private Alert alert;
	private TextFlow massagesTextFlow;
	private NotificationHandler nHandler;
	private TextFlow profitSummaryTextFlow;
	
	static Controller controller;
	
	private final static String FILE_NAME = "allProducts.txt";

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage mainWindow) throws Exception {
		Model model = new Model(FILE_NAME);
		controller = new Controller(this, model);
		table = new TableView();

		this.createButtons();
		this.createRadioButtons();
		this.createTextFields();
		this.createTablesColumn();

		this.setActions();
		
		window = mainWindow;
		window.setTitle("Main window");
		mainScene();
		window.show();
	}


	public void mainScene() {
		
		setSortingOptionsLabelText();
		
		alert = new Alert(AlertType.NONE);
		
		setVbox();
		createBackground();
	 // vbox.setBackground(background);
		submitSorting.setOnAction(e -> choosSorting());
		window.setScene(new Scene(vboxSorting));
	}

	public void mainWindow() {
		
		addProductMainScene.setOnAction(e -> addProductsScene());
		
		//TODO delete the second statment and restore first function
		//showAllProductsMainScene.setOnAction(e -> showProductsScene()); 
		showAllProductsMainScene.setOnAction(e -> printProductsToTerminal()); //to delete
		
		searchForRemoveMainScene.setOnAction(e -> searchProductToRemove());
		
		sendNotificationsMainScene.setOnAction(e -> showReceivedMassages()); // TODO need to add the function to send notification
		
		bRemoveAllProducts.setOnAction(e -> controller.deleteAllProducts()); // TODO need to add pop up massage
		
		bShowProfitSummaryScene.setOnAction(e -> showProfitSummaryScene());
		
		undoFunctionMainScene.setOnAction(e -> controller.undoProduct());
		
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
		vbox.getChildren().addAll(addProductMainScene, 
								  showAllProductsMainScene, 
								  searchForRemoveMainScene, 
								  bRemoveAllProducts, 
								  sendNotificationsMainScene, 
								  bShowProfitSummaryScene, 
								  undoFunctionMainScene, 
								  exit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 80, 20, 80));
		vbox.setSpacing(10);
		window.setScene(new Scene(vbox));
	}

	public void addProductsScene() {
		gridAddProduct = new GridPane();
		Button clearB = new Button("Clear");
		hboxAddProduct = new HBox(8);
		hboxAddProduct.getChildren().addAll(submitAddProductB, clearB);
		
		alert = new Alert(AlertType.NONE);

		setTextForAddProduct();
		setGridAddProduct();
		
		submitAddProductB.setOnAction(e -> addProduct());
		clearB.setOnAction(e -> clear());

		gridAddProduct.setAlignment(Pos.BASELINE_CENTER);
		window.setScene(new Scene(gridAddProduct, 500, 300));

	}

	public void showReceivedMassages() {
		Stage massageStage = new Stage();
		VBox receivedMassagesVBox = new VBox(20);
		receivedMassagesVBox.setPadding(new Insets(10, 10, 10, 10));
		Label receivedMassagesL = new Label("Received Customers Massages");
		receivedMassagesL.setFont(new Font("Arial", 22));
		nHandler = new NotificationHandler();
		nHandler.setFont(new Font("Arial", 16));
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
		table.getColumns().addAll(nameProductTable, 
								  priceForShopTable, 
								  priceForCostumerTable, 
								  nameCostumerTable, 
								  phoneNumberTable,
								  notificationTable);
		setWidthToColumns();
		
		Label label = new Label("All the products:");
		label.setFont(new Font("Arial", 22));
		VBox vboxTable = new VBox(20);
		vboxTable.getChildren().addAll(label, table, backB);
		window.setScene(new Scene(vboxTable, 950, 500));
	}

	public void searchProductToRemove() {
		// search product to remove
		Alert alert = new Alert(AlertType.NONE);
		vboxSearch = new VBox(10);
		
	    setVboxSearchToRemove();
	    setHboxForButtons();
	    
		vboxSearch.getChildren().addAll(searchToRemove, deleteProduct, hboxSearchProduct, table);
		submitSearchB.setOnAction(e -> searchToRemoveFunction());
		window.setScene(new Scene(vboxSearch, 950, 300));
	}

	public void showProfitSummaryScene() {
		
		VBox ProfitSummaryVBox = new VBox(20);
		ProfitSummaryVBox.setPadding(new Insets(10, 10, 10, 10));
		createLabelsTextForSummary();	
		profitSummaryTextFlow.getChildren().add(profitSummaryLabel);
		ScrollPane massagesScrollPane = new ScrollPane(profitSummaryTextFlow);
		ProfitSummaryVBox.getChildren().addAll(profitSummaryHeadLabel,massagesScrollPane, backB);
		controller.getProfitSummary();
		window.setScene(new Scene(ProfitSummaryVBox));
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
	
	public Label getProfitSummaryLabel() {
		return profitSummaryLabel;
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
	
	public void printProductsToTerminal() { // to delete
		controller.printAllProducts(); 
	}

	public NotificationHandler getnHandler() {
		return nHandler;
	}
	
	private void createButtons() {
		submitSorting = new Button("Submit");
		submitAddProductB = new Button("Submit");
		backB = new Button("<<Back");
		addProductMainScene = new Button("Add product");
		showAllProductsMainScene = new Button("Show all the products");
		searchForRemoveMainScene = new Button("remove product");
		bRemoveAllProducts = new Button("remove all products");
		bShowProfitSummaryScene = new Button("show profit summary");
		sendNotificationsMainScene = new Button("show customers with notification");
		undoFunctionMainScene = new Button("Undo");
	}
	
	private void createTextFields() {
		productName = new TextField();
		productNumber = new TextField();
		priceForShop = new TextField();
		priceForCostumer = new TextField();
		costumerName = new TextField();
		phoneNumber = new TextField();
	}
	
	private void createTablesColumn() {
		nameProductTable = new TableColumn<>("Name of product");
		priceForShopTable = new TableColumn<>("Price for the shop");
		priceForCostumerTable = new TableColumn<>("Price at the shop");
		nameCostumerTable = new TableColumn<>("Costumer name");
		phoneNumberTable = new TableColumn<>("Phone number");
		notificationTable = new TableColumn<>("Want to get news?");
	}

	private void createRadioButtons() {
		sortUp = new RadioButton();
		sortDown = new RadioButton();
		sortOrder = new RadioButton();
	}

	private void setActions() {
		backB.setOnAction(e -> mainWindow());
		undoFunctionMainScene.setOnAction(e -> controller.undoProduct());
	}
	
	private void createLabelsTextForSummary() {
		profitSummaryHeadLabel = new Label("Profit Summary");
		profitSummaryLabel = new Label();
		profitSummaryTextFlow = new TextFlow();
		profitSummaryHeadLabel.setFont(new Font("Arial", 22));
		profitSummaryLabel.setFont(new Font("Arial", 14));
		profitSummaryTextFlow.setPrefSize(400, 300);
	}

	private void setSortingOptionsLabelText () {

		head = new Label("Choose type of sorting:");
		head.setFont(new Font("Arial", 22));
		TGSorting = new ToggleGroup();
	
		sortUp.setText("Sorting by Alpha-Bet");
		sortUp.setToggleGroup(TGSorting);
	
		sortDown.setText("Sorting by revers Alpha-Bet");
		sortDown.setToggleGroup(TGSorting);
	
		sortOrder.setText("Sorting by order input");
		sortOrder.setToggleGroup(TGSorting);
	}
	
	private void setVbox() {
		vboxSorting = new VBox();
		
		vboxSorting.getChildren().addAll(head, sortUp, sortDown, sortOrder, submitSorting);
		vboxSorting.setAlignment(Pos.CENTER);
		vboxSorting.setPadding(new Insets(20, 80, 20, 80));
		vboxSorting.setSpacing(10);
	}
	
	private void createBackground() {
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
	}
	
	private void setWidthToColumns() {
		nameProductTable.setMinWidth(150);
		priceForShopTable.setMinWidth(150);
		priceForCostumerTable.setMinWidth(150);
		nameCostumerTable.setMinWidth(150);
		phoneNumberTable.setMinWidth(200);
		notificationTable.setMinWidth(150);
	}
	
	private void setTextForAddProduct() {
		headAddProduct = new Label("Add product:");
		headAddProduct.setFont(new Font("Arial", 22));
		productName.setPromptText("Product name");
		productNumber.setPromptText("Product Number (makat)");
		priceForShop.setPromptText("Price for the shop");
		priceForCostumer.setPromptText("Costumer price");
		costumerName.setPromptText("Costumer Name");
		phoneNumber.setPromptText("Phone number");
		notificationForCostumer = new RadioButton("Send notification");
	}
	
	private void setGridAddProduct() {
		gridAddProduct.setPadding(new Insets(10, 10, 10, 10));
		gridAddProduct.setVgap(5);
		gridAddProduct.setHgap(10);
		gridAddProduct.add(headAddProduct, 0, 0);
		gridAddProduct.add(productName, 1, 0);
		gridAddProduct.add(productNumber, 0, 1);
		gridAddProduct.add(priceForShop, 1, 1);
		gridAddProduct.add(priceForCostumer, 0, 2);
		gridAddProduct.add(costumerName, 1, 2);
		gridAddProduct.add(phoneNumber, 0, 3);
		gridAddProduct.add(notificationForCostumer, 1, 3);
		gridAddProduct.add(hboxAddProduct, 0, 4);
		gridAddProduct.add(backB, 1, 4);
	}
	
	public void setHboxForButtons() {
		hboxSearchProduct = new HBox(8);
		hboxSearchProduct.getChildren().addAll(submitSearchB, backB);
	}
	
	public void setVboxSearchToRemove() {
		deleteProduct = new TextField();
		deleteProduct.setMaxWidth(150);
	    submitSearchB = new Button("Submmit");
	    searchToRemove = new Label ("Enter the product number (makat):");
	    searchToRemove.setFont(new Font("Arial", 12));
	}
	
	public void searchToRemoveFunction() {
		if (deleteProduct.getText().isEmpty()) {
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("The text field for the product number is empty");
			alert.show();
				
		}
		else
			if(controller.checkIfProductExist(deleteProduct.getText()) ==false) {
				alert.setAlertType(AlertType.ERROR);
				alert.setContentText("The product doesnt exist");
				alert.show();
			}
			else
				controller.deleteProduct();
	}
	
	public void popupWindowMassage(EMassageFromShop massage) {
		
		if(massage.equals(EMassageFromShop.SUCCEES)) {
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setContentText("success");
		}
		else if (massage.equals(EMassageFromShop.DOESNT_EXIST)){
			alert.setAlertType(AlertType.WARNING);
			alert.setContentText("Not exist in system");
		}
		else if (massage.equals(EMassageFromShop.FAILE)){
			alert.setAlertType(AlertType.ERROR);
			alert.setContentText("Error accured, no action was taken");
		}
	}

}
