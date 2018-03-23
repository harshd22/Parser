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

	private Controller controller;
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
		sb.append("<html>" + "\n" + "<body>" + "\n" );
		
		try {
			while ((line = reader.readLine()) != null) {

				if (line.isEmpty()) {// New Paragraph
					sb.append("</p>");
					sb.append("\n");
					sb.append("<p>");
				} else if (line.startsWith("#")) {// New Heading
					parseHead(line);

				} else {
					if(!sb.toString().contains("<p>")) {
						sb.append("<p>");
					}
					sb.append(line);

				}

			}
			System.out.print(sb.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parseHead(String line) {
		int i = 0;
		char checkhead[] = line.toCharArray();
		for (i = 0; i < checkhead.length; i++) {
			if (checkhead[i] != '#') {
				break;
			}
		}

		sb.append("<h" + i + ">");
		sb.append(line.substring(i + 1, line.length()));
		sb.append("</h" + i + ">" + "\n");
		
	}

	private void parseBold(String line, int index) {

		for(int i = index ; i < chars.length; i++) {
			if(chars[i] == '*' && chars[i+1] == '*') {
				
			}
		}
		
		
	}

	private int parseItalics(String line, int index) {
		for (int i = index; i < chars.length; i++) {
			
			if (chars[i] == '*' && chars[i + 1] != '*') { // if found another

				
				sb.append("<em>");
				sb.append(line.substring(index,i));
				sb.append("</em>");
				return i;
			}
		}
		sb.append(chars[index-1]);
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
