package view;

import controller.Controller;
import javafx.application.*;
import model.Model;
public class Main{
	
	private final static String FILE_NAME = "allProducts.txt";

	public static void main(String[] args) {
		Model model = new Model(FILE_NAME);
		View view = new View();
		Controller controller = new Controller(view, model);
		Application.launch(view.getClass(),args);
	}

}
