import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Player extends Thread {

    private static boolean play;

    private static javazoom.jl.player.advanced.AdvancedPlayer AP;

    static void play_music() {
       javazoom.jl.player.advanced.PlaybackListener pl = new javazoom.jl.player.advanced.PlaybackListener() {
        };
        try {
            URL url = Player.class.getClassLoader().getResource("super-mario.mp3");
            assert url != null;
            File mario = new File(url.toURI());
            AP = javazoom.jl.player.advanced.jlap.playMp3(mario,pl);

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
