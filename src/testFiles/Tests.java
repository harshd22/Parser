package testFiles;

import java.io.File;


import org.junit.Test;

import junit.framework.Assert;
import mainProgram.Parser;

public class Tests {

	
	
	@SuppressWarnings("deprecation")
	@Test
	public void paragraphTest() {
		Parser parse = new Parser(new File("paragraph.txt"));
		String Html  = parse.getSymbolParser().getHtml().toString().replaceAll("\n", "");
		Assert.assertEquals( "<p>I am the 1st paragraph </p><p>I am the 2nd paragraph </p>",Html );
		
	}
	
	@Test
	public void headingTest() {
		Parser parse = new Parser(new File("heading.txt"));
		String Html  = parse.getSymbolParser().getHtml().toString().replaceAll("\n", "");
		Assert.assertEquals( "<h1>Heading1</h1><h2>Heading2</h2><h3>Heading3</h3>",Html );
		
	}
	
	@Test
	public void boldTest() {
		Parser parse = new Parser(new File("bold.txt"));
		String Html  = parse.getSymbolParser().getHtml().toString().replaceAll("\n", "");
		
		Assert.assertEquals( "<p>he<strong>ll</strong>ooo </p>",Html );
		
	}
	
	@Test
	public void italicsTest() {
		Parser parse = new Parser(new File("italics.txt"));
		String Html  = parse.getSymbolParser().getHtml().toString().replaceAll("\n", "");
		
		Assert.assertEquals( "<p>I<em>tal</em>ics </p>",Html );
		
	}
	

}
	
	

