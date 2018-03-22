import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {

	private File file;
	Parser parser;
	Model model;

	public Controller(Model model) {
		this.model = model;
	}

	public void chooseFile() {

		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		chooser.setCurrentDirectory(new File("."));
		chooser.setDialogTitle("Select File");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
			file = chooser.getSelectedFile();
		}
		
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
