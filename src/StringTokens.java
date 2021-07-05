import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class StringTokens {
	String normalWords = "";
	String stopWords = "";
	String unrecognizedWords = "";
	String trainingWords = "";
	//String TestingWords = "";
	
	String[] copy = new String[153];
	
	Scanner stopWordsScanner;
	Scanner textScanner;
	
	StringTokens(){
		OpenFile();	
		ReadFile();
		CloseFile();
	}
	
	public void OpenFile() {
		try {
			stopWordsScanner = new Scanner(new File("StopWords.txt"));
			
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "No StopWords File Detected!");
		}	
	}

	public void CloseFile() {
		stopWordsScanner.close();
	}
	
	

	public void ReadFile() {
		int x = 0;
		while(stopWordsScanner.hasNext()) {
			copy[x] = stopWordsScanner.next();
			x++;
		}
	}
	
	public boolean TextReader(String _word) {
		try {
			textScanner = new Scanner(new File("TextData.txt"));
			while(textScanner.hasNext()) {
				if(_word.compareToIgnoreCase(textScanner.next()) == 0) {
					return false;
				}
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "No TextData File Detected!");
		}
		finally{
			textScanner.close();
		}
		return true;
		
	}
	
	//Methods Start Here-=--=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-
	
	public void TokenizeString(String _words) {
		StringTokenizer ST = new StringTokenizer(_words, " ");
		String word;
		while(ST.hasMoreTokens()) {
			word = ST.nextToken();
			if(RemoveStopWords(word)) {
				stopWords += word + " ";
			}
			else if(TextReader(word)) {
				unrecognizedWords += word + " ";
				trainingWords += word + " ";
			}
			else {
				normalWords += word + " ";
				trainingWords += word + " ";
			}
		}
	}
	
	public boolean RemoveStopWords(String _word) {
		for(int x = 0; x < copy.length; x++) {
			if(_word.compareToIgnoreCase(copy[x]) == 0) {
				return true;
			}
		}
		return false;
	}
}
