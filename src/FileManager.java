import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

public class FileManager {
	
	Scanner trainingDataScanner;
	Scanner emotionDataScanner;
	Scanner textDataScanner;
	Scanner digitDataScanner;
	
	File trainingDataReader = new File("TrainingData.txt");
	File emotionDataReader = new File("EmotionData.txt");
	File labelDataReader = new File("Label.txt");
	File textDataReader = new File("TextData.txt");
	File digitDataReader = new File("DigitData.txt");
	
	public void EnterData(String _words, String _emotionData) {
		try {		
			WriteTrainingData(_words, trainingDataReader);
			WriteEmotionData(_emotionData, emotionDataReader);
			WriteLabelData(_words, _emotionData, labelDataReader);
			
			StringTokenizer ST = new StringTokenizer(_words, " ");
			while(ST.hasMoreTokens()) {
				WriteTextData(ST.nextToken(), textDataReader);
				WriteDigitData(_emotionData, digitDataReader);
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data");
		}
	}
	
	public void WriteTextData(String _data, File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, true);
			fileWriter.write(_data + "\n");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data\nSauce : TextData");
		}
	}
	public void WriteDigitData(String _digitData, File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, true);
			fileWriter.write(_digitData + "\n");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data\nSauce : DigitData");
		}
	}
	public void WriteTrainingData(String _trainingData, File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, true);
			fileWriter.write(_trainingData + "\n");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data\nSauce : TrainingData");
		}
	}
	public void WriteEmotionData(String _emotionData1, File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, true);
			fileWriter.write(_emotionData1 + "\n");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data\nSauce : EmotionData");
		}
	}
	public void WriteLabelData(String _labelData, String _emotionData1, File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, true);
			fileWriter.write(_labelData + "\t" + _emotionData1 + "\n");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Train Data\nSauce : LabelData");
		}
	}
	
	public void DeleteTrainingDatas() {
		DeletingTrainingDatas(trainingDataReader);
		DeletingTrainingDatas(emotionDataReader);
		DeletingTrainingDatas(labelDataReader);
		DeletingTrainingDatas(textDataReader);
		DeletingTrainingDatas(digitDataReader);
	}
	public void DeletingTrainingDatas(File _file) {
		try {
			FileWriter fileWriter = new FileWriter(_file, false);
			fileWriter.write("");
			fileWriter.close();
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Delete Training Datas\nSauce : Delete Datas");
		}
	}
	
	public String TestingDatas(String _words) {
		try {
			trainingDataScanner = new Scanner(new File("TrainingData.txt"));
			int whatPosition = 0;
			while(trainingDataScanner.hasNext()) {
				String words = trainingDataScanner.nextLine();
				if(_words.compareToIgnoreCase(words) == 0) {
					//GetWhatEmotion
					String emotion = GetEmotion(whatPosition);
					//SetValues
					if(!(emotion.compareToIgnoreCase("") == 0)) {
						return emotion;
					}
					break;
				}
				whatPosition++;
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Test Data\nSauce : Testing Datas");
		}
		finally {
			trainingDataScanner.close();
		}
		return "novalue";
	}
	
	public String GetEmotion(int _whatPosition) {
		try {
			emotionDataScanner = new Scanner(new File("EmotionData.txt"));
			int number = 0;
			while(emotionDataScanner.hasNext()) {
				String copyEmotion = "";
				for(int x = 0; x < 5; x++) {
					copyEmotion += emotionDataScanner.next() + " ";
				}
				if(number == _whatPosition) {
					return copyEmotion;
				}
				number++;
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Test Data\nSauce : GetEmotion");
		}
		finally {
			emotionDataScanner.close();
		}
		return "";
	}
	
	//Get the counter Value
	public int GetCounterValue(String _word) {
		int counter = 0;
		try {
			textDataScanner = new Scanner(new File("TextData.txt"));
			while(textDataScanner.hasNext()) {
				if(_word.compareToIgnoreCase(textDataScanner.nextLine()) == 0) {
					counter++;
				}
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Get Counter Data\nSauce : Get Counter Value");
		}
		finally {
			textDataScanner.close();
		}
		return counter;
	}
	
	//Get What Positions
	public int[] GetWhatPositions(String _word, int _counter) {
		int[] whatPositions = new int[_counter];
		int positionCounter = 0;
		int ctr = 0;
		try {
			textDataScanner = new Scanner(new File("TextData.txt"));
			while(textDataScanner.hasNext()) {
				if(_word.compareToIgnoreCase(textDataScanner.nextLine()) == 0) {
					whatPositions[positionCounter] = ctr;
					positionCounter++;
				}
				ctr++;
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Get Positions\nSauce : Get What Positions");
		}
		finally {
			textDataScanner.close();
		}
		return whatPositions;
	}
	//Return whatEmotion===========================================================
	public float[] GetWhatEmotion(int _counter, int[] _whatPositions) {
		int[] emotions = {0, 0, 0, 0, 0};
		float[] emotionValue = {0, 0, 0, 0, 0};
		
		int whatPositionCounter = 0;
		int ctr = 0;
		try {
			digitDataScanner = new Scanner(new File("DigitData.txt"));
			while(digitDataScanner.hasNext()) {
				if(_counter > 0) {
					for(int x = 0; x < 5; x++) {
						String emotion = digitDataScanner.next();
						emotions[x] = Integer.parseInt(emotion);
					}
					if(ctr == _whatPositions[whatPositionCounter]) {
						//get emotion
						for(int x = 0; x < 5; x++) {
							emotionValue[x] += emotions[x];
						}
						if(whatPositionCounter < _counter-1) {
							whatPositionCounter++;
						}
					}
				}
				ctr++;
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Unable to Get Emotion\nSauce : Get What Emotion" + ee);
		}
		finally {
			digitDataScanner.close();
		}
		return emotionValue;
	}
}
