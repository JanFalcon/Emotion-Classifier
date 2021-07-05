import java.util.*;
import java.io.*;

public class SwitchPanel{
	
	Scanner scanf;
	
	SwitchPanel(){
		OpenFile();
		ReadFile();
		CloseFile();
	}
	
	public void OpenFile() {
		try {
			scanf = new Scanner(new File("TrainingData.txt"));
		}
		catch(Exception ee) {
			System.out.println("No file Detected");
		}
	}
	public void CloseFile() {
		scanf.close();
	}
	
	public void ReadFile() {
		while(scanf.hasNext()) {
			System.out.println(scanf.next());
		}
	}
	
	public static void main(String[] args) {
		new SwitchPanel();
	}
}
