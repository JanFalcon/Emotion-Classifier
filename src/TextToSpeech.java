import com.sun.speech.freetts.*;

public class TextToSpeech {

	private static final String VOICE_NAME = "kevin16";
	
	Voice voice;
	VoiceManager vm;
	
	public void Talk(String words) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	    vm = VoiceManager.getInstance();
	    voice = vm.getVoice(VOICE_NAME);
//	    voice = VoiceManager.getInstance().getVoice("kevin16");
	    if (voice != null) {
	        voice.allocate();// Allocating Voice
	        try {
	            voice.setRate(100);// Setting the rate of the voice
	            voice.setPitch(120);// Setting the Pitch of the voice
//	            voice.setVolume(3);// Setting the volume of the voice
	            for(int x = 0; x < 1; x++)
	            	voice.speak(words);// Calling speak() method

	        } catch (Exception ee) {
	            ee.printStackTrace();
	        }
	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	
	public static void main(String[] args) {
		new TextToSpeech();
	}
	
}
 