import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Controller handles the file choosing.
 * @author Harsh
 *
 */
public class Controller {

	private File file;
	Parser parser;
	Model model;

	public Controller(Model model) {
		this.model = model;
	}

	/**
	 * Opens a Jfilechooser , choose the file and send it for parsing.
	 */
	public void chooseFile() {

		JFileChooser chooser = new JFileChooser();
		//Sets the file filter.
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		chooser.setCurrentDirectory(new File("."));
		chooser.setDialogTitle("Select File");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
			file = chooser.getSelectedFile();
		}
		
		//If file is not null then send it for parsing.
		if(file != null) {
			model.startParsing(file);
		}
		

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
