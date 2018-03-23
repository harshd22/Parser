import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.modelmbean.RequiredModelMBean;

public class Parser {

	
	private BufferedReader reader;
	private File data;
	private StringBuilder Html;
	private parseSymbols symbolParser;
	
	
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
					parseNewParagraph();

				} else if (line.startsWith("#")) {// New Heading
					
					parseHeading(line);

				} 
//				else if (line.startsWith("* ")) {
//					parseUnorderedList(line);
//
//				} else if (line.startsWith(Character.DECIMAL_DIGIT_NUMBER + ". ")) {
//					parseOrderedList(line);
//
//				} else if (line.startsWith("---") && !line.contains("A-z")) {
//					Html.append("</p>" + "\n");
//					Html.append("<hr/>" + "\n");
//
//				} else if (line.startsWith("> ")) {
//
//				} 
				else {
					checkPtag();
					Html.append(line);

				}

			}

			symbolParser = new parseSymbols(Html.toString());
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void checkPtag() {
		if (!Html.toString().contains("<p>")) {
			Html.append("<p>");
		}
		
	}

	private void parseOrderedList(String line) {
		// TODO Auto-generated method stub

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
		Html.append("</p>" + "\n");
		int Level = checkHeadingLevel(line);
		Html.append("<h" + Level + ">" + line.substring(Level, line.length()) + "</h" + Level + ">" + "\n");

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

	public parseSymbols getSymbolParser() {
		return symbolParser;
	}


}
