import java.io.InputStream;

public class Player extends Thread {

    private static boolean play;
    private static javazoom.jl.player.advanced.AdvancedPlayer AP;

    static void playMusic() {
       javazoom.jl.player.advanced.PlaybackListener pl = new javazoom.jl.player.advanced.PlaybackListener() {};
        try {
            InputStream url = Player.class.getClassLoader().getResourceAsStream("super-mario.mp3");
            assert url != null;
            AP = javazoom.jl.player.advanced.jlap.playMp3(url, 0, Integer.MAX_VALUE, pl);
            play = true;
        } catch (Exception e) { e.printStackTrace(); }
    }
    static void stopMusic() {
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
