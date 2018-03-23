
public class parseSymbols {

	private StringBuilder Html;
	private char dataArray[];
	private String data;

	public parseSymbols(String data) {
		this.data = data;
		Html = new StringBuilder();
		parseAsterix();

	}

	private void parseAsterix() {

		
		dataArray = data.toCharArray();

		for (int index = 0; index < dataArray.length; index++) {

			if (dataArray[index] == '*' && dataArray[index + 1] == '*' && dataArray[index + 2] != '\n') {
				index = parseBold(this.data, index + 2);
			} else
				Html.append(dataArray[index]);
		}

		data = Html.toString();
		dataArray = data.toCharArray();
		Html = new StringBuilder();

		for (int index = 0; index < dataArray.length; index++) {

			if (dataArray[index] == '*' && dataArray[index + 1] != '*' && dataArray[index + 1] != '\n') {
				index = parseItalics(this.data, index + 1);
			} else
				Html.append(dataArray[index]);
		}
		
		data = Html.toString();
		 Html.append("</body> " + "</html>");
		//System.out.println(Html.toString());
	}

	private int parseItalics(String data, int index) {
		for (int i = index; dataArray[i] != '\n'; i++) {

			if (dataArray[i] == '*' && dataArray[i + 1] != '*') { // if found another

				Html.append("<em>" + data.substring(index, i) + "</em>");

				return i;
			}
		}
		Html.append(dataArray[index - 1]);
		return index-1;

	}

	private int parseBold(String data, int index) {

		for (int i = index; dataArray[i] != '\n'; i++) {

			if (dataArray[i] == '*' && dataArray[i + 1] == '*') {

				Html.append("<strong>" + data.substring(index, i) + "</strong>");
				return i + 1;
			}
		}
		
		Html.append(dataArray[index-2]);
		
		
		return index - 2;
	}

	public StringBuilder getHtml() {
		return Html;
	}


}
