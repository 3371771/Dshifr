import java.io.File;

public class Player extends Thread {

    private static javazoom.jl.player.advanced.AdvancedPlayer AP;

    static void play_music() {
        javazoom.jl.player.advanced.PlaybackListener pl = new javazoom.jl.player.advanced.PlaybackListener() {
        };
        try {
            AP = javazoom.jl.player.advanced.jlap.playMp3(new File(".//src//super-mario.mp3"), pl);
            //Thread.sleep(1000);
        } catch (Exception e) { e.printStackTrace(); }
    }

    static void stop_music() {
        try {
            AP.stop();
            AP.close();
        } catch (Exception e) {e.printStackTrace(); }
    }
}
