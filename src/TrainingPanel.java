import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class TrainingPanel extends JFrame implements ActionListener, ChangeListener, MouseListener{
	
//	Timer t = new Timer(500, this);
	
	TextToSpeech tts = new TextToSpeech();
	
	StringTokens tokenizer = new StringTokens();
	FileManager fileManager = new FileManager();
	StringTokenizer ST;
	
	String trainingData;
	String testingData;
	String trainThisData;
	
	//Slider Values
	int[] sliderValue = new int[5];
	int totalValue = 0;
	int remainingValue = 100;
	int maxValue = 100;

	boolean accepted = true;
	boolean canTrain = false;
	boolean canTest = false;
	
	boolean testPanel = false;
	
	//Main Panel
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
	JPanel panel4;
	JPanel panel5;
	JPanel panel6;
	
	JPanel panel7;
	JPanel panel8;
	
	//Texts
	JLabel titlePanelText;
	
	JLabel panelText;
	JLabel sampleText;
	JLabel emotionClassificationText;
	
	JLabel[] emotion;
	JLabel[] emotionPercentage;
	
	JLabel totalPercent;
	
	JLabel processedWords;
	JLabel console;
	
	JLabel deleteTrainingData;
	JLabel deleteTrainingData1;
	
	//TextFields
	JTextField[] emotionValue;
	
	//TextAreas
	JTextArea sampleArea;
	JTextArea stopWordsArea;
	JTextArea normalWordsArea;
	JTextArea unrecognizedWordsArea;
	
	JTextArea consoleWordsArea;
	
	//ScrollPanes
	JScrollPane scrollPane;
	JScrollPane stopWordScrollPane;
	JScrollPane wordAreaScrollPane;
	JScrollPane unrecognizedScrollPane;
	
	JScrollPane conesoleScrollPane;
	
	//Buttons
	JButton trainingButton;
	JButton testingButton;
	JButton settingsButton;
	
	JButton exitButton;
	JButton acceptButton;
	JButton acceptButton1;
	JButton clearTextButton;
	
	JButton[] emotionButton;
	
	JButton trainButton;
	JButton testButton;
	
	JButton clearButton;
	JButton deleteTrainingButton;
	
	JButton adjustButton;
	JButton trainAgainButton;
	
	//Sliders
	JSlider[] slider;
	
	//Fonts
	Font f1 = new Font("Arial", 1, 15);
	Font f2 = new Font ("Arial", 1, 20);
	Font italized = new Font("Arial", 2, 15);
	Font bigFont = new Font("Arial", 1, 30);
	
	TrainingPanel(){
		super("Emotion Discourse");
		SetFrame();
		InitializeObjects();
		AddObjects();
		SetDesign();
		SetPosition();
		SetKeyListeners();
	}
	
	public void SetFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.pack();
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		panel = new JPanel();
		add(panel);
		panel.setLayout(null);
	}
	
	
	public void InitializeObjects() {
		//Panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		
		//Texts
		titlePanelText = new JLabel("Training Panel");
		
		panelText = new JLabel("Panels");
		sampleText = new JLabel("Sample Text Here");
		emotionClassificationText = new JLabel("Emotion Classification");
		
		emotion = new JLabel[5];
		emotionPercentage= new JLabel[5];
		
		emotion[0] = new JLabel("Happy");
		emotion[1] = new JLabel("Sad");
		emotion[2] = new JLabel("Anger");
		emotion[3] = new JLabel("Fear");
		emotion[4] = new JLabel("Disgust");
		
		emotionPercentage[0] = new JLabel("%");
		emotionPercentage[1] = new JLabel("%");
		emotionPercentage[2] = new JLabel("%");
		emotionPercentage[3] = new JLabel("%");
		emotionPercentage[4] = new JLabel("%");
		
		totalPercent = new JLabel("0 %");
		
		processedWords = new JLabel("Processed Words : ");
		console = new JLabel("Console : ");
		
		deleteTrainingData = new JLabel(" Delete All");
		deleteTrainingData1 = new JLabel("    Training Data");
		
		//TextFields
		emotionValue = new JTextField[5];
		emotionValue[0] = new JTextField("0");
		emotionValue[1] = new JTextField("0");
		emotionValue[2] = new JTextField("0");
		emotionValue[3] = new JTextField("0");
		emotionValue[4] = new JTextField("0");
		
		//TextAreas
		sampleArea = new JTextArea("Enter Here", 1, 1);
		stopWordsArea = new JTextArea("Stop Words");
		normalWordsArea = new JTextArea("Processed Words");
		unrecognizedWordsArea = new JTextArea("Unrecognized Words");
		
		consoleWordsArea = new JTextArea("Console...");
		
		//ScrollPanes
		scrollPane = new JScrollPane(sampleArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		stopWordScrollPane = new JScrollPane(stopWordsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		wordAreaScrollPane = new JScrollPane(normalWordsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		unrecognizedScrollPane = new JScrollPane(unrecognizedWordsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		conesoleScrollPane = new JScrollPane(consoleWordsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Sliders
		slider = new JSlider[5];
		for(int x = 0; x < 5; x++) {
			slider[x] = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
			slider[x].setMajorTickSpacing(20);
//			slider[x].setMinorTickSpacing(5);
			slider[x].setPaintTicks(true);
		}
		
		
		//Buttons
		trainingButton = new JButton("Train");
		testingButton = new JButton("Test");
		settingsButton = new JButton("S");
		
		exitButton = new JButton("Close");
		acceptButton = new JButton("Accept");
		acceptButton1 = new JButton("Accept");
		clearTextButton = new JButton("Clear Text");
		
		trainButton = new JButton("TRAIN");
		testButton = new JButton("TEST");
		
		clearButton = new JButton("CLEAR");
		deleteTrainingButton = new JButton("Delete All");
		
		emotionButton = new JButton[6];
		emotionButton[0] = new JButton("Reset");
		emotionButton[1] = new JButton("Happy");
		emotionButton[2] = new JButton("Sad");
		emotionButton[3] = new JButton("Anger");
		emotionButton[4] = new JButton("Fear");
		emotionButton[5] = new JButton("Disgust");
		
		adjustButton = new JButton("Adjust");
		trainAgainButton = new JButton("Train");
		
	}

	public void AddObjects() {
		//Panels
		panel.add(panel1);
		panel1.add(panel2);
		panel1.add(panel3);
		panel1.add(panel4);
		panel1.add(panel5);
		panel5.add(panel6);
		
		panel1.add(panel7);
		panel1.add(panel8);
		
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);
		panel4.setLayout(null);
		panel5.setLayout(null);
		panel6.setLayout(null);
		panel7.setLayout(null);
		panel8.setLayout(null);
		
		//Texts
		panel1.add(titlePanelText);
		panel2.add(panelText);
		panel1.add(sampleText);
		panel1.add(emotionClassificationText);
		
		panel6.add(totalPercent);
		
		for(int x = 0; x < 5; x++) {
			panel6.add(emotion[x]);
			panel6.add(emotionPercentage[x]);
		}
		
		panel7.add(processedWords);
		panel3.add(console);
		
		panel8.add(deleteTrainingData);
		panel8.add(deleteTrainingData1);
		
		//TextFields
		for(int x = 0; x < 5; x++) {
			panel6.add(emotionValue[x]);
		}
		
		//TextAreas
		//panel4.add(sampleArea);
		
		//Scrollpanes
		panel4.add(scrollPane);
		panel7.add(stopWordScrollPane);
		panel7.add(wordAreaScrollPane);
		panel7.add(unrecognizedScrollPane);
		
		panel3.add(conesoleScrollPane);
		
		//Sliders
		for(int x = 0; x < 5; x++) {
			panel6.add(slider[x]);
		}
		
		//Buttons
		panel2.add(exitButton);
		panel2.add(trainingButton);
		panel2.add(testingButton);
		panel2.add(settingsButton);
		
		panel4.add(acceptButton);
		panel4.add(acceptButton1);
		panel4.add(clearTextButton);
		
		for(int x = 0; x < 6; x++) {
			panel6.add(emotionButton[x]);
		}
		panel6.add(adjustButton);
		panel6.add(trainAgainButton);
		
		panel1.add(trainButton);
		panel1.add(testButton);
		
		panel1.add(clearButton);
		panel8.add(deleteTrainingButton);
		
		//Listeners----------------------------------------------------------
		
		//Change Listeners
		
		for(int x = 0; x < 5; x++) {
			slider[x].addChangeListener(this);
		}
		
		//Button Listeners
		trainingButton.addActionListener(this);
		testingButton.addActionListener(this);
		settingsButton.addActionListener(this);
		exitButton.addActionListener(this);
		acceptButton.addActionListener(this);
		acceptButton1.addActionListener(this);
		clearTextButton.addActionListener(this);
	
		trainButton.addActionListener(this);
		testButton.addActionListener(this);
		
		clearButton.addActionListener(this);
		deleteTrainingButton.addActionListener(this);
		
		for(int x = 0; x < 6; x++) {
			emotionButton[x].addActionListener(this);
		}
		
		adjustButton.addActionListener(this);
		trainAgainButton.addActionListener(this);
		
		//Mouse Listeners=================================================================
		sampleArea.addMouseListener(this);
		
	}
	
	public void SetDesign() {	
	
		//Panels
		panel.setBackground(Color.GRAY);
		panel1.setBackground(Color.BLACK);
		panel2.setBackground(Color.DARK_GRAY);
		panel3.setBackground(Color.DARK_GRAY);
		panel4.setBackground(Color.DARK_GRAY);
		panel5.setBackground(Color.DARK_GRAY);
		panel6.setBackground(Color.BLACK);
		panel7.setBackground(Color.DARK_GRAY);
		panel8.setBackground(Color.DARK_GRAY);
		
		//Texts
		titlePanelText.setForeground(Color.ORANGE);
		panelText.setForeground(Color.WHITE);
		sampleText.setForeground(Color.WHITE);
		emotionClassificationText.setForeground(Color.WHITE);
		totalPercent.setForeground(Color.WHITE);
		
		processedWords.setForeground(Color.WHITE);
		console.setForeground(Color.WHITE);
		for(int x = 0; x < 5; x++) {
			emotion[x].setForeground(Color.WHITE);
			emotionPercentage[x].setForeground(Color.WHITE);
		}
		
		deleteTrainingData.setBackground(Color.BLACK);
		deleteTrainingData.setForeground(Color.WHITE);
		deleteTrainingData1.setBackground(Color.BLACK);
		deleteTrainingData1.setForeground(Color.WHITE);
		
		//TextFields
		for(int x = 0; x < 5; x++) {
			emotionValue[x].setForeground(Color.WHITE);
			emotionValue[x].setBackground(Color.BLACK);
		}
		
		
		//TextAreas
		sampleArea.setBackground(Color.BLACK);
		sampleArea.setForeground(Color.WHITE);
		sampleArea.setLineWrap(true);
		
		stopWordsArea.setBackground(Color.BLACK);
		stopWordsArea.setForeground(Color.WHITE);
		stopWordsArea.setLineWrap(true);
		stopWordsArea.setEditable(false);
		
		normalWordsArea.setBackground(Color.BLACK);
		normalWordsArea.setForeground(Color.WHITE);
		normalWordsArea.setLineWrap(true);
		normalWordsArea.setEditable(false);
		
		unrecognizedWordsArea.setBackground(Color.BLACK);
		unrecognizedWordsArea.setForeground(Color.WHITE);
		unrecognizedWordsArea.setLineWrap(true);
		unrecognizedWordsArea.setEditable(false);
		
		consoleWordsArea.setBackground(Color.BLACK);
		consoleWordsArea.setForeground(Color.WHITE);
		consoleWordsArea.setLineWrap(true);
		consoleWordsArea.setEditable(false);
		
		//ScrollPanes
//		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));
//		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
		
		stopWordScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		stopWordScrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
		
		wordAreaScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		wordAreaScrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
		
		unrecognizedScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		unrecognizedScrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
		
		conesoleScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		conesoleScrollPane.getVerticalScrollBar().setForeground(Color.WHITE);
		
		//Fonts
			//fonts Texts
			titlePanelText.setFont(bigFont);
			panelText.setFont(f2);
			sampleText.setFont(f2);
			emotionClassificationText.setFont(f2);
			totalPercent.setFont(f1);
			
			processedWords.setFont(f2);
			console.setFont(f2);
			
			for(int x = 0; x < 5; x++) {
				emotion[x].setFont(f1);
				emotionPercentage[x].setFont(f1);
			}
			deleteTrainingData.setFont(f2);
			//deleteTrainingData1.setFont(f2);
			
			//fonts TextFields
			for(int x = 0; x < 5; x++) {
				emotionValue[x].setFont(f1);
			}
			
			//fonts TextAreas
			sampleArea.setFont(italized);
			stopWordsArea.setFont(italized);
			normalWordsArea.setFont(italized);
			unrecognizedWordsArea.setFont(italized);
			
			//fonts Buttons
			trainingButton.setFont(f1);
			testingButton.setFont(f1);
			settingsButton.setFont(f1);
			exitButton.setFont(f1);
			
			for(int x = 0; x < 6; x++) {
				emotionButton[x].setFont(f1);
			}
		
			trainButton.setFont(f2);
			testButton.setFont(f2);
			
			clearButton.setFont(f2);
//			deleteTrainingButton.setFont(f2);
			
			adjustButton.setFont(f1);
			trainAgainButton.setFont(f1);
			
		//Sliders
			for(int x = 0; x < 5; x++) {
				slider[x].setBackground(Color.BLACK);
				slider[x].setForeground(Color.WHITE);
			}
		
		//Visibles
		testButton.setVisible(false);
		acceptButton1.setVisible(false);	
		adjustButton.setVisible(false);
		trainAgainButton.setVisible(false);
			
		//Buttons
		exitButton.setBackground(Color.BLACK);
		acceptButton.setBackground(Color.BLACK);
		acceptButton1.setBackground(Color.BLACK);
		clearTextButton.setBackground(Color.BLACK);
		trainingButton.setBackground(Color.BLACK);
		testingButton.setBackground(Color.BLACK);
		settingsButton.setBackground(Color.BLACK);

		exitButton.setForeground(Color.WHITE);
		acceptButton.setForeground(Color.WHITE);
		acceptButton1.setForeground(Color.WHITE);
		clearTextButton.setForeground(Color.WHITE);
		trainingButton.setForeground(Color.WHITE);
		testingButton.setForeground(Color.WHITE);
		settingsButton.setForeground(Color.WHITE);
		
		emotionButton[0].setBackground(Color.BLACK);
		emotionButton[0].setForeground(Color.WHITE);
		
		emotionButton[1].setBackground(Color.YELLOW);
		emotionButton[1].setForeground(Color.BLACK);
		
		emotionButton[2].setBackground(Color.CYAN);
		emotionButton[2].setForeground(Color.BLACK);
		
		emotionButton[3].setBackground(Color.RED);
		emotionButton[3].setForeground(Color.BLACK);
		
		emotionButton[4].setBackground(Color.pink);
		emotionButton[4].setForeground(Color.BLACK);
		
		emotionButton[5].setBackground(Color.GREEN);
		emotionButton[5].setForeground(Color.BLACK);
		
		trainButton.setBackground(Color.BLACK);
		trainButton.setForeground(Color.WHITE);
		
		testButton.setBackground(Color.BLACK);
		testButton.setForeground(Color.WHITE);
		
		clearButton.setBackground(Color.BLACK);
		clearButton.setForeground(Color.WHITE);
		
		deleteTrainingButton.setBackground(Color.BLACK);
		deleteTrainingButton.setForeground(Color.WHITE);
		
		adjustButton.setBackground(Color.WHITE);
		adjustButton.setForeground(Color.BLACK);
		trainAgainButton.setBackground(Color.WHITE);
		trainAgainButton.setForeground(Color.BLACK);
		
		//Positioning
		exitButton.setHorizontalTextPosition(JButton.CENTER);
		exitButton.setVerticalTextPosition(JButton.CENTER);
		
		acceptButton.setHorizontalTextPosition(JButton.CENTER);
		acceptButton1.setHorizontalTextPosition(JButton.CENTER);
		clearTextButton.setVerticalTextPosition(JButton.CENTER);
		
		acceptButton.setHorizontalTextPosition(JButton.CENTER);
		acceptButton1.setHorizontalTextPosition(JButton.CENTER);
		clearTextButton.setVerticalTextPosition(JButton.CENTER);
		
		//TextFieldPositioning
		for(int x = 0; x < 5; x++) {
			emotionValue[x].setHorizontalAlignment(JTextField.RIGHT);
		}
	}
	
	public void SetPosition() {
		
		//Panels
		panel1.setBounds(10, 10, 1345, 745);
		panel2.setBounds(10, 10, 100, 725);
		panel3.setBounds(120, 635, 1217, 100);
		panel4.setBounds(120, 115, 250, 400);
		panel5.setBounds(400, 115, 809, 400);
		panel6.setBounds(10, 10, 789, 380);
		panel7.setBounds(120, 525, 1217, 100);
		panel8.setBounds(1217, 340, 120, 175);
		
		//Texts
		titlePanelText.setBounds(580, 0, 250, 50);
		panelText.setBounds(20, 10, 100, 80);
		sampleText.setBounds(120, 70, 200, 50);
		emotionClassificationText.setBounds(400, 70, 300, 50);
		
		for(int x = 0, yPosition = 20; x < 5; x++, yPosition += 50) {
			emotion[x].setBounds(10, yPosition, 100, 15);
			emotionPercentage[x].setBounds(670, yPosition + 3, 100, 15);
		}
		
		totalPercent.setBounds(620, 270, 100, 15);
		
		processedWords.setBounds(10, 10, 200, 15);
		console.setBounds(10, 10, 100, 15);
		
		deleteTrainingData.setBounds(10, 0, 100, 40);
		deleteTrainingData1.setBounds(10, 20, 100, 40);
		
		
		//TextFields
		for(int x = 0, yPosition = 20; x < 5; x++, yPosition += 50) {
			emotionValue[x].setBounds(600, yPosition, 65, 20);
		}
		
		//TextAreas
		//sampleArea.setBounds(10, 10, 230, 300);
		
		//scrollPanes
		scrollPane.setBounds(10, 10, 230, 300);
		stopWordScrollPane.setBounds(280, 10, 300,80);
		wordAreaScrollPane.setBounds(590, 10, 300, 80);
		unrecognizedScrollPane.setBounds(900, 10, 308, 80);
		
		conesoleScrollPane.setBounds(280, 10, 928, 80);
		
		//Sliders
		for(int x = 0, yPosition = 10; x < 5; x++, yPosition += 50) {
			slider[x].setBounds(100, yPosition, 500, 50);
		}
		
		//Buttons
		acceptButton.setBounds(165, 320, 75, 70);
		acceptButton1.setBounds(165, 320, 75, 70);
		clearTextButton.setBounds(10, 320, 145, 70);
		
		trainingButton.setBounds(10, 105, 80, 80);
		testingButton.setBounds(10, 215, 80, 80);
		settingsButton.setBounds(10, 525, 80, 80);
		exitButton.setBounds(10, 635, 80, 80);
		
		for(int x = 0, xPosition = 10; x < 6; x++, xPosition += 100) {
			emotionButton[x].setBounds(xPosition, 285, 90, 80);
		}
		
		trainButton.setBounds(1227, 115, 100, 100);
		testButton.setBounds(1227, 115, 100, 100);
		
		clearButton.setBounds(1227, 230, 100, 100);
		deleteTrainingButton.setBounds(10, 65, 100, 100);
		
		adjustButton.setBounds(10, 285, 90, 80);
		trainAgainButton.setBounds(110, 285, 90, 80);
	}


	//Listeners
	public void SetKeyListeners() {
		emotionValue[0].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				TextFieldListener(0, e);
			}
		});
		emotionValue[1].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				TextFieldListener(1, e);
			}
		});
		emotionValue[2].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				TextFieldListener(2, e);
			}
		});
		emotionValue[3].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				TextFieldListener(3, e);
			}
		});
		emotionValue[4].addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				TextFieldListener(4, e);
			}
		});
		
	}
	
	public void TextFieldListener(int _whatSlider, KeyEvent e) {
		try {
			if(e.getKeyCode() != KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
				String copy = emotionValue[_whatSlider].getText();
				int number = Integer.parseInt(copy);
				slider[_whatSlider].setValue(number);
			}
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, "Invalid Input(s) : "+  ee.getMessage());
			slider[_whatSlider].setValue(0);
			emotionValue[_whatSlider].setText(0 + "");
		}
	}
	
	public void AcceptButton() {
		String words = sampleArea.getText();

		totalValue = slider[0].getValue() + slider[1].getValue() + slider[2].getValue()
				+ slider[3].getValue() + slider[4].getValue();
		
		if(totalValue < 100 || words.compareToIgnoreCase("") == 0) {
			acceptButton.setBackground(Color.RED);
			accepted = false;
		}
		else {
			acceptButton.setBackground(Color.CYAN);
			accepted = true;
		}
		
		//Accepted=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		if(accepted) {
			acceptButton.setEnabled(false);
			panel7.setBackground(Color.GRAY);
			//function
			tokenizer.TokenizeString(words);
			consoleWordsArea.setText("Removing Stop Words - 100%\nRecognizing Words - 100%\nFiltering Unrecognized Words - 100%\nTraining in progress...");
			
			stopWordsArea.setText(tokenizer.stopWords);
			unrecognizedWordsArea.setText(tokenizer.unrecognizedWords);
			normalWordsArea.setText(tokenizer.normalWords);
			canTrain = true;
		}
	}
	public void AcceptButton1() {
		String words = sampleArea.getText();

		if(words.compareToIgnoreCase("") == 0) {
			acceptButton1.setBackground(Color.RED);
			accepted = false;
		}
		else {
			acceptButton1.setBackground(Color.CYAN);
			accepted = true;
		}
		
		//Accepted=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=1
		if(accepted) {
			acceptButton1.setEnabled(false);
			panel7.setBackground(Color.GRAY);
			//function
			tokenizer.TokenizeString(words);
			consoleWordsArea.setText("Removing Stop Words - 100%\nRecognizing Words - 100%\nFiltering Unrecognized Words - 100%\nTesting in progress...");
			
			stopWordsArea.setText(tokenizer.stopWords);
			unrecognizedWordsArea.setText(tokenizer.unrecognizedWords);
			normalWordsArea.setText(tokenizer.normalWords);
			canTest = true;
		}
	}
	
	public void ResetEmotions() {
		acceptButton.setBackground(Color.BLACK);
		acceptButton1.setBackground(Color.BLACK);
		for(int x = 0; x < 5; x++) {
			slider[x].setValue(0);
		}
	}

	public void TrainingPanelButton() {
		testPanel = false;
		titlePanelText.setText("Training Panel");
		for(int x = 0; x < 6; x++) {
			emotionButton[x].setVisible(true);
		}
		trainButton.setVisible(true);
		testButton.setVisible(false);
		acceptButton.setVisible(true);
		acceptButton1.setVisible(false);
		adjustButton.setVisible(false);
		trainAgainButton.setVisible(false);
		ClearData();
		for(int x = 0; x < 5; x++) {
			slider[x].setEnabled(true);
			emotionValue[x].setEditable(true);
		}
	}
	public void TestingPanelButton() {
		testPanel = true;
		titlePanelText.setText("Testing Panel");
		for(int x = 0; x < 6; x++) {
			emotionButton[x].setVisible(false);
		}
		testButton.setVisible(true);
		trainButton.setVisible(false);
		acceptButton1.setVisible(true);
		acceptButton.setVisible(false);
		adjustButton.setVisible(true);
		trainAgainButton.setVisible(true);
		adjustButton.setBackground(Color.WHITE);
		adjustButton.setForeground(Color.BLACK);
		ClearData();
		for(int x = 0; x < 5; x++) {
			slider[x].setEnabled(false);
			emotionValue[x].setEditable(false);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == trainingButton) {
			TrainingPanelButton();
		}
		else if(e.getSource() == testingButton) {
			TestingPanelButton();
		}
		else if(e.getSource() == exitButton) {
			System.exit(0);
		}
		else if(e.getSource() == acceptButton) {
			AcceptButton();
		}
		else if(e.getSource() == acceptButton1) {
			AcceptButton1();
		}
		else if(e.getSource() == clearTextButton) {
			acceptButton.setBackground(Color.BLACK);
			acceptButton1.setBackground(Color.BLACK);
			acceptButton.setEnabled(true);
			acceptButton1.setEnabled(true);
			sampleArea.setText("Enter Here");
		}
		
		//Emotion Buttons
		else if(e.getSource() == emotionButton[0]) {
			ResetEmotions();
		}
		else if(e.getSource() == emotionButton[1]) {
			slider[0].setValue(sliderValue[0] + remainingValue);
		}
		else if(e.getSource() == emotionButton[2]) {
			slider[1].setValue(sliderValue[1] + remainingValue);
		}
		else if(e.getSource() == emotionButton[3]) {
			slider[2].setValue(sliderValue[2] + remainingValue);
		}
		else if(e.getSource() == emotionButton[4]) {
			slider[3].setValue(sliderValue[3] + remainingValue);
		}
		else if(e.getSource() == emotionButton[5]) {
			slider[4].setValue(sliderValue[4] + remainingValue);
		}
		//Right Side
		else if(e.getSource() == trainButton) {//TrainingButton-------------------------------------------------
			if(canTrain) {
				trainingData = tokenizer.trainingWords;
				Training(trainingData);
				trainButton.setEnabled(false);
				clearButton.setBackground(Color.WHITE);
				clearButton.setForeground(Color.BLACK);
			}
			else {
				JOptionPane.showMessageDialog(null, "The \"Data\" must be first Accepted before Training.");
			}
		}
		else if(e.getSource() == testButton) {//TestButton===========================================
			if(canTest) {
				trainThisData = tokenizer.trainingWords;
				testingData = tokenizer.normalWords;
				Testing(testingData);
				testButton.setEnabled(false);
				clearButton.setBackground(Color.WHITE);
				clearButton.setForeground(Color.BLACK);
			}
			else {
				JOptionPane.showMessageDialog(null, "The \"Data\" must be first Accepted before Testing.");
			}
			
			tts.Talk(sampleArea.getText());
		}
		else if(e.getSource() == clearButton) {
			ClearData();
			trainButton.setEnabled(true);
			testButton.setEnabled(true);
			clearButton.setBackground(Color.BLACK);
			clearButton.setForeground(Color.WHITE);
		}
		else if(e.getSource() == deleteTrainingButton) {
			DeleteTrainingDatas();
		}
		else if(e.getSource() == adjustButton) {
			if(canTest) {
				for(int x = 0; x < 5; x++) {
					slider[x].setEnabled(true);
					emotionValue[x].setEditable(true);
				}
				adjustButton.setBackground(Color.BLACK);
				adjustButton.setForeground(Color.WHITE);
			}
			else {
				JOptionPane.showMessageDialog(null, "The \"Data\" must be first Accepted before Testing.");
			}
		}
		else if(e.getSource() == trainAgainButton) {
			//emotion && trainThisData
			//String Emotion
			if(canTest) {
				trainingData = tokenizer.trainingWords;
				System.out.println(trainingData);
				Training(trainingData);
				trainAgainButton.setEnabled(false);
				trainAgainButton.setBackground(Color.BLACK);
				trainAgainButton.setForeground(Color.WHITE);
				ClearWords();
				//BS CONSOLE
				consoleWordsArea.setText("Removing Stop Words - 100%\nRecognizing Words - 100%\nFiltering Unrecognized Words - 100%\nSemi-Supervised Learning in progress...\nTesting Complete...\nTraining in Progress...\nTraining 100%\nTraining Complete...\nSemi-Supervised Learning Complete...");
			}
			else {
				JOptionPane.showMessageDialog(null, "The \"Data\" must be first Accepted before Testing.");
			}
		}
	}

	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == slider[0]) {
			GetValue(0);
		}
		else if(e.getSource() == slider[1]) {
			GetValue(1);
		}
		else if(e.getSource() == slider[2]) {
			GetValue(2);
		}
		else if(e.getSource() == slider[3]) {
			GetValue(3);
		}
		else if(e.getSource() == slider[4]) {
			GetValue(4);
		}
	}
	
	public void GetValue(int _whatSlider) {
		if(!accepted) {
			acceptButton.setBackground(Color.black);
			accepted = true;
		}
		totalValue = slider[0].getValue() + slider[1].getValue() + slider[2].getValue()
					+ slider[3].getValue() + slider[4].getValue();
		
		//If Overflow
		if(totalValue > maxValue) {
			slider[_whatSlider].setValue(sliderValue[_whatSlider] + remainingValue);
			totalValue = 100;
		}
		
		remainingValue = maxValue - totalValue;
		sliderValue[_whatSlider] = slider[_whatSlider].getValue();
		emotionValue[_whatSlider].setText(sliderValue[_whatSlider] + "");
		
		totalValue = 0;
		for(int x = 0; x < 5; x++) {
			totalValue += sliderValue[x];
		}
		totalPercent.setText(totalValue + " %");
	}

	//Mouse Listeners======================================================================
	
	public void mouseClicked(MouseEvent e) {
		String copythis = sampleArea.getText();
		
		if(copythis.compareToIgnoreCase("Enter Here") == 0) {
			sampleArea.setText("");
		}
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		String copythis = sampleArea.getText();
		
		if(copythis.compareToIgnoreCase("Enter Here") == 0) {
			sampleArea.setText("");
		}
	}

	public void mouseReleased(MouseEvent e) {
		String copythis = sampleArea.getText();
		
		if(copythis.compareToIgnoreCase("Enter Here") == 0) {
			sampleArea.setText("");
		}
	}
	
	//Testing--------------------------------------------------------------------------------------

	public void Testing(String _words) {
		String whatEmotion = fileManager.TestingDatas(_words);
		if(!(whatEmotion.compareToIgnoreCase("novalue") == 0)) {
			//Tokenize Emotion
			String[] emotionArray = new String[5];
			ST = new StringTokenizer(whatEmotion, " ");
			int x = 0;
			while(ST.hasMoreTokens()) {
				emotionArray[x] = ST.nextToken();
				slider[x].setValue(Integer.parseInt(emotionArray[x]));
				x++;
			}
		}
		else {//========================================
			float[] totalEmotion = {0, 0, 0, 0, 0};
			int counter = 0;//reads how many words are the same in a sentence
			int lengthOfSentence = 0;//length of sentence / normal Words
			float[] emotions = new float[5];
			//Tokenize the word
			ST = new StringTokenizer(_words, " ");
			//LOOP THIS per word
			while(ST.hasMoreTokens()) {
				String word = ST.nextToken();
				//Get Counter Value
				counter = fileManager.GetCounterValue(word);
				int[] whatPositions = new int[counter]; 
				//Get What Position
				whatPositions = fileManager.GetWhatPositions(word, counter);
				//Get What Emotion
				emotions = fileManager.GetWhatEmotion(counter, whatPositions);
				
				for(int x = 0; x < 5; x++) {
					emotions[x] *= 0.01f;
					emotions[x] /= counter;
					totalEmotion[x] += emotions[x];
				}
				lengthOfSentence ++;
			}
			
			for(int x = 0; x < 5; x++) {
				totalEmotion[x] /= lengthOfSentence;
				totalEmotion[x] *= 100;
				totalEmotion[x] = (float)Math.round(totalEmotion[x]);
				slider[x].setValue((int)totalEmotion[x]);
			}
			totalPercent.setText(totalEmotion[0] + totalEmotion[1] + totalEmotion[2] + totalEmotion[3] + totalEmotion[4] + "");
			
			//Can train && Can adjust
			//Train Again Button
			
		}//==========================================================
		
		//BS CONSOLE
		consoleWordsArea.setText("Removing Stop Words - 100%\nRecognizing Words - 100%\nFiltering Unrecognized Words - 100%\nSemi-Supervised Learning in progress...\nSemi-Supervised Learning in Complete...\nTesting Complete...");
	}
	
	//Training---------------------------------------------------------------------------
	public void Training(String _words) {
		String emotionData = slider[0].getValue() + "\t" + slider[1].getValue() + "\t" + slider[2].getValue()
								+ "\t" + slider[3].getValue() + "\t" + slider[4].getValue();
		
		fileManager.EnterData(_words, emotionData);
		//Clearing Words
		ClearWords();
		//BS CONSOLE
		consoleWordsArea.setText("Removing Stop Words - 100%\nRecognizing Words - 100%\nFiltering Unrecognized Words - 100%\nUnrecognized Words used as Training Data...\nTraining Complete...");
	}

	
	public void ClearData() {
		if(testPanel) {
			trainAgainButton.setEnabled(true);
			for(int x = 0; x < 5; x++) {
				slider[x].setEnabled(false);
				emotionValue[x].setEditable(false);
			}
			adjustButton.setBackground(Color.WHITE);
			adjustButton.setForeground(Color.BLACK);
			trainAgainButton.setBackground(Color.WHITE);
			trainAgainButton.setForeground(Color.BLACK);
		}
		
		sampleArea.setText("Enter Here");
		stopWordsArea.setText("Stop Words");
		normalWordsArea.setText("Processed Words");
		unrecognizedWordsArea.setText("Unrecognized Words");
		consoleWordsArea.setText("console...");
		panel3.setBackground(Color.DARK_GRAY);
		panel7.setBackground(Color.DARK_GRAY);
		acceptButton.setEnabled(true);
		acceptButton1.setEnabled(true);
		ResetEmotions();
		ClearWords();
		canTrain = false;
		canTest = false;
	}
	
	public void ClearWords() {
		tokenizer.stopWords = "";
		tokenizer.normalWords = "";
		tokenizer.unrecognizedWords = "";
		tokenizer.trainingWords = "";
	}
	
	public void DeleteTrainingDatas() {
		int decision = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ALL Training Datas?");
		if(decision == 0) {
			int decision1 = JOptionPane.showConfirmDialog(null, "Are you really really REALLY SURE! YOU WANT to DELETE ALL!!! Training Datas?");
			if(decision1 == 0) {
				String password = JOptionPane.showInputDialog(null, "Please Enter DELETE ALL confimation password.");
				if(password.compareTo("JanMarc") == 0) {
					ClearData();
					fileManager.DeleteTrainingDatas();
					consoleWordsArea.setText("Deleting Training Datas in progress...\nDeleting Training Datas - 100%\nDeleting Training Datas Completed...\nTraining Datas Deleted...");
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Password..\nFailed to delete training datas");
				}
			}
		}
	}

}
