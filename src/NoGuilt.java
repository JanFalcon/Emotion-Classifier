import java.util.Scanner;
import java.io.*;


public class NoGuilt {
	Scanner scan;
	File fw;
	
	NoGuilt(){
		OpenFile();
		ReadFile();
		CloseFile();
	}
	
	public void OpenFile() {
		try{
			scan = new Scanner(new File("WANDW - NOREVISION.txt"));
			fw = new File("WANDW.txt");
		}
		catch(Exception e){}
	}

	public void ReadFile() {
		while(scan.hasNext()) {
			String word = scan.next();
			if(word.compareToIgnoreCase("fear") == 0){
				word = scan.nextLine();
				WriteFile(word , fw);
			}
			else
				word = scan.nextLine();
		}
		
	}
	
	public void WriteFile(String _word, File f) {
		try {
			FileWriter fileWriter = new FileWriter(f, true);
			fileWriter.write(_word + "\n");
			fileWriter.close();
		}
		catch(Exception e) {}
	}
	
	public void CloseFile() {
		scan.close();
	}
	
	public static void main(String[] args) {
		new NoGuilt();
	}
}
