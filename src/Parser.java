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

/**
 * This is the main Parsing class in which file is parsed line by line and converted to html 
 * then passed to the other object for checking the symbols.
 * 
 * @author Harsh
 *
 */
public class Parser {

	
	private BufferedReader reader;
	private File data;
	private StringBuilder Html;
	private SymbolParser symbolParser;
	
	
	/**
	 * 
	 * Parser constructer takes the data file and calls the function to start parsing.
	 * @param data
	 * 
	 */
	Parser(File data) {
		this.data = data;
		Html = new StringBuilder();
		readFile();
		parseFile();

	}

	/**
	 * Reads file line by line and parse it to html.
	 */
	private void parseFile() {
		String line;
		reader.
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
					parseHorizintalRule(line);
					

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


	/**
	 * Parses the Horizontal rule
	 * @param line
	 */
	private void parseHorizintalRule(String line) {
		char chars[] = line.toCharArray();
		for(int i = 0 ; i < chars.length ; i++) {
			if(chars[i] != '-') {
				return;
			}
		}
		
			Html.append("</p>" + "\n");
			Html.append("<hr/>" + "\n" + "<p>");
		
		
	}

	/**
	 * Parses the Symbols
	 */
	private void parseSymbols() {
		if (!Html.toString().endsWith("</h>") || !Html.toString().endsWith("</ul>") ||  Html.toString().endsWith("<p>")) {
			Html.append("</p>");
			}
		symbolParser = new SymbolParser(Html.toString().replaceAll("<p></p>", ""));
		
	}

	/**
	 * Parses the block Quotes
	 * @param line
	 * @throws IOException
	 */
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

	/**
	 * Checks if list is ending or not.
	 */
	private void checkList() {
		if(Html.toString().endsWith("</li>")) {
			Html.append("</ul>" + "\n" + "<p>");
				}
		
	}

	/**
	 * checks if the html contains the p tag or not.
	 */
	private void checkPtag() {
		if (!Html.toString().contains("<p>") ) {
			Html.append("<p>");
		}
		
		
	}


	/**
	 * Parses the unordered list.
	 * 
	 * @param line
	 */
	private void parseUnorderedList(String line) {
		if (!Html.toString().endsWith("</li>")) {
			Html.append("<ul>");
		}
		Html.append("<li>");
		Html.append(line.substring(2, line.length()));
		Html.append("</li>");
		// how to end the tag.

	}

	/**
	 * Adds the tag If new paragraph has to start
	 */
	private void parseNewParagraph() {
		Html.append("</p>" + "\n" + "<p>");

	}

	/**
	 * Parse the headings.
	 * 
	 * @param line
	 */
	private void parseHeading(String line) {
		if(Html.toString().contains("<p>"))
			Html.append("</p>" + "\n");
		int Level = checkHeadingLevel(line);
		Html.append("<h" + Level + ">" + line.substring(Level, line.length()) + "</h" + Level + ">" + "\n");
		Html.append("<p>");
	}

	/**
	 * 
	 * Check the level of heading .
	 * 
	 * @param line
	 * @return
	 */
	private int checkHeadingLevel(String line) {
		int index = 0;
		char checkLevel[] = line.toCharArray();
		for (index = 0; index < checkLevel.length; index++) {
			if (checkLevel[index] != '#')
				break;

		}
		return index;
	}

	/**
	 * Reads the file with buffered reader
	 */
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
