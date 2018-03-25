package mainProgram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

/**
 * View class shows the Jframe in which the Html text is shown.
 * @author Harsh
 * 
 */
public class View extends JFrame {

	private JButton loadFile;
	private Model model;
	public JEditorPane editor;

	View(Model model) {
		this.model = model;
		loadFile = new JButton("Load File");
		loadFile.addActionListener((e) -> this.model.controller.chooseFile());

		editor = new JEditorPane();
		editor.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(editor);
		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		editor.setEditorKit(htmlEditorKit);

		JToolBar toolbar = new JToolBar();
		toolbar.setLayout(new FlowLayout());
		toolbar.add(loadFile);
		this.pack();
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setSize(400, 400);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.pack();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.add(toolbar , BorderLayout.SOUTH);

	}


}
