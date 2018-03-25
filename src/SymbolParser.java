
/**
 * This class Parses all the symbols in the text.
 * 
 * @author Harsh
 *
 */
public class SymbolParser {

	private StringBuilder Html;
	private char dataArray[];
	private String data;

	public SymbolParser(String data) {
		this.data = data;
		Html = new StringBuilder();
		parseAsterix();

	}

	/**
	 * Parses all the Asterix occurences in the text.
	 */
	private void parseAsterix() {

		dataArray = data.toCharArray();
		// Search for the single Asterix for Italics
		for (int index = 0; index < dataArray.length; index++) {

			if (dataArray[index] == '*' && dataArray[index + 1] == '*' && dataArray[index + 2] != '\n') {
				index = parseBold(this.data, index + 2);
			} else
				Html.append(dataArray[index]);
		}

		newInstance();
		// Search for the double occurrence of Asterix for Bold
		for (int index = 0; index < dataArray.length; index++) {

			if (dataArray[index] == '*' && dataArray[index + 1] != '*' && dataArray[index + 1] != '\n') {
				index = parseItalics(this.data, index + 1);
			} else
				Html.append(dataArray[index]);
		}

		data = Html.toString();
		//Html.append("</body> " + "\n" + "</html>");
		System.out.println(Html.toString());

	}

	/**
	 * After each feature is parsed , This function is called to create a new
	 * instance of String builder and copy its data into a String.
	 */
	private void newInstance() {
		data = Html.toString();
		dataArray = data.toCharArray();
		Html = new StringBuilder();

	}

	/**
	 * Parses the text surrounded by single Asterix to Italics .
	 * 
	 * @param data
	 * @param index
	 * @return
	 */
	private int parseItalics(String data, int index) {
		for (int i = index; dataArray[i] != '\n'; i++) {

			if (dataArray[i] == '*' && dataArray[i + 1] != '*') { // if found another asterix

				Html.append("<em>" + data.substring(index, i) + "</em>");
				// Return the new index where it is found
				return i;
			}
		}
		// if not found then Return the old index to treat it as normal text.
		Html.append(dataArray[index - 1]);
		return index - 1;

	}

	private int parseBold(String data, int index) {

		for (int i = index; dataArray[i] != '\n'; i++) {

			if (dataArray[i] == '*' && dataArray[i + 1] == '*') {// if found another pair of asterix

				Html.append("<strong>" + data.substring(index, i) + "</strong>");
				// Return the new index where it is found
				return i + 1;
			}
		}
		// if not found then Return the old index to treat it as normal text.
		Html.append(dataArray[index - 2]);
		return index - 2;

	}

	public StringBuilder getHtml() {
		return Html;
	}

}
