package Fixtures;

import fit.ColumnFixture;
import java.io.*;
import java.util.regex.*;
import fit.Fixture;

/**
 *
 * @author Richard Winn - pragmatic.rich@gmail.com
 * @version 1
 */

public class Regex extends ColumnFixture {
    public String value;
    public String pattern;
    public String debug = "";
    public String fullResult;
    private String workspace;

    public String result() {
	debug = "";
	workspace=null;

        value = Common.Symbol.parseSymbols(value);

	// If VALUE is a file path, process the file. Otherwise, process the string.
	if (value.toLowerCase().startsWith("!file:")){
		value = value.substring(6).trim();
		workspace = matchPatternFromFile(pattern, value);
		debug += "Successfully captured file content.<br><br>";
	} else {
		workspace = matchPattern(pattern, value);
	}
	return workspace;
    } //END: result()

    public String matchPattern(String regex, CharSequence data){
	Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
	String output = "";
	try {
		m.find();
		fullResult =  m.group();
		output = m.group(m.groupCount());
	}
	catch (Throwable ex){
		output = "";
	}
	return output;
    } //END: matchPattern

	//public Object symbolValue(String s){
	//		return Fixture.getSymbol(s);
	//	}



    public String matchPatternFromFile(String regex, String strFile) {
	File aFile = new File(strFile);
	String output = "";
	debug += "Reading file..<br><br>";

	try {
		BufferedReader input =  new BufferedReader(new FileReader(aFile));
		try {
			String line = null;
			while (( line = input.readLine()) != null && output.isEmpty()){
				output = matchPattern(regex, line);
			}
		}
		finally {
			debug += "Closing input stream..<br><br>";
			input.close();
		}
	}
	catch (IOException ex){
		ex.printStackTrace();
	}
	debug += "Returning contents of file as String..<br><br>";
	return output;
    }
}
