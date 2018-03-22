import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

public class View extends JFrame {

	private JButton loadFile;
	private Model model;
	private JEditorPane editor;

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

		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.add(toolbar , BorderLayout.SOUTH);

	}


}
