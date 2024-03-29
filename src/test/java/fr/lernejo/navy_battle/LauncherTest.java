package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {
    @Test
    void GoodArgumentLauncher(){
        new Launcher();
        try {
            String[] arg = new String[]{"9876"};
            Launcher.main(arg);

            arg = new String[]{"9875", "http://localhost:9876"};

            Launcher.main(arg);
        }catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            Assertions.assertEquals(0,1,"Number of exception");
        }
        Assertions.assertEquals(0,0,"Number of exception");
    }

    @Test
    void BadArgumentLauncher()
    {
        try {
            String[] arg = new String[]{"@@"};
            Launcher.main(arg);
            Assertions.assertEquals(1,0,"Number of exception");
        }catch (IllegalArgumentException e) {
            Assertions.assertEquals(1, 1, "Number of IllegalArgumentException");
        } catch (IOException | InterruptedException | URISyntaxException e) {
            Assertions.assertEquals(1, 0, "Number of IllegalArgumentException");
        };

        try {
            String[] arg = new String[]{"3905","Kappa is a good kappa"};
            Launcher.main(arg);
            Assertions.assertEquals(1,0,"Number of exception");
        } catch (URISyntaxException e) {
            Assertions.assertEquals(1, 1, "Number of URISyntaxException");
        }catch (IOException | InterruptedException e) {
            Assertions.assertEquals(1, 0, "Number of URISyntaxException");
        }
    }
}
