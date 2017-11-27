import java.io.File;

public class Player extends Thread {

    private static boolean play;

    private static javazoom.jl.player.advanced.AdvancedPlayer AP;

    static void play_music() {
        javazoom.jl.player.advanced.PlaybackListener pl = new javazoom.jl.player.advanced.PlaybackListener() {
        };
        try {
            AP = javazoom.jl.player.advanced.jlap.playMp3(new File(".//src//super-mario.mp3"), pl);
            //D://src//super-mario.mp3
            //Thread.sleep(1000);
            play = true;
        } catch (Exception e) { e.printStackTrace(); }
    }

    static void stop_music() {
        if (play) {
            try {
                AP.stop();
                AP.close();
                play = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
