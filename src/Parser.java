import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.modelmbean.RequiredModelMBean;
import javax.swing.text.html.HTMLEditorKit;

public class Parser {

	
	private BufferedReader reader;
	private File data;
	private StringBuilder Html;
	private SymbolParser symbolParser;
	
	
	Parser(File data) {
		this.data = data;
		Html = new StringBuilder();
		readFile();
		parseFile();

	}

	private void parseFile() {
		String line;
		Html.append("<html>" + "\n" + "<body>" + "\n");

		try {
			while ((line = reader.readLine()) != null) {

				if (line.isEmpty()) {// New Paragraph
					checkList();
					parseNewParagraph();

				} else if (line.startsWith("#")) {// New Heading
					checkList();
					parseHeading(line);

				} else if (line.startsWith("* ")) {// Un- ordered list (doesn't check for the nested list)
					parseUnorderedList(line);

				} else if (line.startsWith("---")) { // Horizontal rule
					Html.append("</p>" + "\n");
					Html.append("<hr/>" + "\n" + "<p>");

				} else if (line.startsWith("> ")) { // BlockQuote
					checkList();
					parseBlockQuote(line);
				} else { // Simple Text
					checkPtag();
					Html.append(line + " ");

				}

			}
			
			parseSymbols();
			

			System.out.println(Html.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	private void parseSymbols() {
		if (!Html.toString().endsWith("</h>") || !Html.toString().endsWith("</ul>") ||  Html.toString().endsWith("<p>")) {
			Html.append("</p>");
			}
		symbolParser = new SymbolParser(Html.toString().replaceAll("<p></p>", ""));
		
	}

	private void parseBlockQuote(String line) throws IOException {
		Html.append("<blockquote>" + "<p>"  + line.substring(2, line.length()));
		while((line = reader.readLine()) != null ) {
			if (line.isEmpty() || line.startsWith("#") || line.startsWith("* ") || line.startsWith("> ")) {
				break;
			} else {
				Html.append(line);
			}
		}
		Html.append("</p>" + "</blockquote>" + "\n" + "<p>");

	}

	private void checkList() {
		if(Html.toString().endsWith("</li>")) {
			Html.append("</ul>" + "\n" + "<p>");
				}
		
	}

	private void checkPtag() {
		if (!Html.toString().contains("<p>") ) {
			Html.append("<p>");
		}
		
		
	}


	private void parseUnorderedList(String line) {
		if (!Html.toString().endsWith("</li>")) {
			Html.append("<ul>");
		}
		Html.append("<li>");
		Html.append(line.substring(2, line.length()));
		Html.append("</li>");
		// how to end the tag.

	}

	private void parseNewParagraph() {
		Html.append("</p>" + "\n" + "<p>");

	}

	private void parseHeading(String line) {
		if(Html.toString().contains("<p>"))
			Html.append("</p>" + "\n");
		int Level = checkHeadingLevel(line);
		Html.append("<h" + Level + ">" + line.substring(Level, line.length()) + "</h" + Level + ">" + "\n");
		Html.append("<p>");
	}

	private int checkHeadingLevel(String line) {
		int index = 0;
		char checkLevel[] = line.toCharArray();
		for (index = 0; index < checkLevel.length; index++) {
			if (checkLevel[index] != '#')
				break;

		}
		return index;
	}

	private void readFile() {

		try {
			reader = new BufferedReader(new FileReader(data));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public StringBuilder getHtml() {
		return Html;
	}

	public SymbolParser getSymbolParser() {
		return symbolParser;
	}


}
