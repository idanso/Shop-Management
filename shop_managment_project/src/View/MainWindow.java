package View;

import java.awt.Font;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		Button sorting = new Button ("Select type of sorting");
		Button showProduct = new Button ("Show all the product");
		Button exit = new Button ("EXIT");
		exit.setOnAction(e ->{
			System.out.println("Good bye!");
			System.exit(0);
		});
		VBox vbox = new VBox();
		vbox.getChildren().addAll(addProduct, sorting, showProduct, exit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20,80,20,80));
		vbox.setSpacing(10);
		starter = new Scene(vbox);
		
		
		
		//addProduct scene
		HBox hbox1 = new HBox(10);
		HBox hbox2 = new HBox(10);
		HBox hbox3 = new HBox(10);
		VBox vboxAddProduct = new VBox();
		Button btn = new Button("Submmit");
		btn.setOnAction(e -> {
//			System.out.println(txt1);
		});
		Text head = new Text ();
		head.setText("Add product");
		//head.setTextAlignment(Pos.TOP_CENTER);
		//head.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField txt1 = new TextField("Product Name");
//		txt1.getOnAction(e ->{
//			
//		});
		TextField txt2 = new TextField("Price for the shop");
		hbox1.getChildren().addAll(txt1, txt2);
		TextField txt3 = new TextField("Costumer price");
		TextField txt4 = new TextField("Costumer Name");
		hbox2.getChildren().addAll(txt3, txt4);
		TextField txt5 = new TextField("Phone number");
		hbox3.getChildren().addAll(txt5);
		vboxAddProduct.setSpacing(20);
		vboxAddProduct.getChildren().addAll(head, hbox1,hbox2,hbox3);
		addProductScene = new Scene(vboxAddProduct, 500,500);
		
		
		window.setScene(starter);
		window.show();
	}
	
	
	@Override//do the final things after exit the program
	public void stop() throws Exception{
		System.out.println("After the program");
	}

}
