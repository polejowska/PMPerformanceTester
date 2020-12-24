import javax.sound.sampled.*;


public class AppThirdTestSound {

    Clip clip;
    FloatControl gainControl;

    public AppThirdTestSound (float soundVolume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("Resources/Sounds/1_0.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(soundVolume);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}