package mainProgram;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
	String Html;
	BufferedWriter bWriter = null;
	FileWriter fWriter = null;

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
		Html = parser.getSymbolParser().getHtml().toString().replaceAll("(?m)^[ \t]*\r?\n", "");
		System.out.println(Html);
		view.editor.setText(Html);
		saveFile();
		
	}

	/**
	 * Saves the main parsed file into the directory.
	 */
	private void saveFile() {
		try {
			fWriter = new FileWriter(controller.getFile().getAbsolutePath() + "output");
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(this.Html);
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String args[]) {
		new Model();
	}

}
