import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music 
{
    private String filename;
    private AdvancedPlayer musicPlayer; 

    public Music(String filename) 
    {
        this.filename = filename;
    }

    public void close() { if (musicPlayer != null) musicPlayer.close(); }

    public void play() {
        try 
        {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            musicPlayer = new AdvancedPlayer(bufferedInputStream);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // Play Music in background
        new Thread() {
            public void run() {
                try { musicPlayer.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }
}