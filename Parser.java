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

	Scanner scan;
	String HTML;
	BufferedReader reader;
	File data;
	StringBuilder sb;
	char chars[];

	Parser(File data) {
		this.data = data;
		sb = new StringBuilder();
		readFile();
		parseFile();

	}

	private void parseFile() {
		String line;
		sb.append("<html>" + "\n" + "<body>" + "\n");

		try {
			while ((line = reader.readLine()) != null) {

				if (line.isEmpty()) {// New Paragraph
					parseNewParagraph();

				} else if (line.startsWith("#")) {// New Heading
					
					parseHead(line);

				} 
//				else if (line.startsWith("* ")) {
//					parseUnorderedList(line);
//
//				} else if (line.startsWith(Character.DECIMAL_DIGIT_NUMBER + ". ")) {
//					parseOrderedList(line);
//
//				} else if (line.startsWith("---") && !line.contains("A-z")) {
//					sb.append("</p>" + "\n");
//					sb.append("<hr/>" + "\n");
//
//				} else if (line.startsWith("> ")) {
//
//				} 
				else {
					checkPtag();
					sb.append(line);

				}

			}

			parseSymbols ps = new parseSymbols(sb.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void checkPtag() {
		if (!sb.toString().contains("<p>")) {
			sb.append("<p>");
		}
		
	}

	private void parseOrderedList(String line) {
		// TODO Auto-generated method stub

	}

	private void parseUnorderedList(String line) {
		if (!sb.toString().endsWith("</li>")) {
			sb.append("<ul>");
		}
		sb.append("<li>");
		sb.append(line.substring(2, line.length()));
		sb.append("</li>");
		// how to end the tag.

	}

	private void parseNewParagraph() {
		sb.append("</p>" + "\n" + "<p>");

	}

	private void parseHead(String line) {
		sb.append("</p>" + "\n");
		int Level = checkHeadingLevel(line);
		sb.append("<h" + Level + ">" + line.substring(Level, line.length()) + "</h" + Level + ">" + "\n");

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

}
