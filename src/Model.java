import java.io.File;


/**
 * 
 * Model class handles all the instances .
 * 
 * @author Harsh
 *
 */
public class Model {

	
	Controller controller;
	Parser parser;
	View view;

	Model() {
		controller = new Controller(this);
		view = new View(this);
		
	}
	
	/**
	 * Takes the file from controller and sends for parsing.
	 * @param file
	 */
	public void startParsing(File file){
		parser = new Parser(file);
		view.editor.setText(parser.getSymbolParser().getHtml().toString());
	}

	public static void main(String args[]) {
		new Model();
	}

}
