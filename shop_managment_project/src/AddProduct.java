package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddProduct extends Application{

	@Override
	public void start(Stage productStage) throws Exception {
		productStage.setTitle("Add product");
		HBox hbox = new HBox();
		TextField txt1 = new TextField("Enter the product Name");
		TextField txt2 = new TextField("Enter the price for the shop");
		TextField txt3 = new TextField("Costumer price");
		TextField txt4 = new TextField("Enter the costumer Name");
		TextField txt5 = new TextField("Enter the phone number");
		hbox.setSpacing(20);
		hbox.getChildren().addAll(txt1, txt2, txt3, txt4, txt5);
		Scene scene = new Scene(hbox, 500,200);
		productStage.setScene(scene);
		productStage.show();
		
	}
}
