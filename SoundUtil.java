import java.util.HashMap;
import java.util.Map;

import greenfoot.GreenfootSound;

public class SoundUtil {

    private static Map<String, GreenfootSound> SOUND_CACHE = new HashMap<String, GreenfootSound>();

    public static void playSound(String file) {
        GreenfootSound sound = getSoundFromCache(file);
        if (!sound.isPlaying()) {
            sound.play();
        }
    }

    public static void loop(String file) {
        GreenfootSound sound = getSoundFromCache(file);
        sound.playLoop();
    }

    public static void stopLoop(String file) {
        GreenfootSound sound = getSoundFromCache(file);
        sound.stop();
    }

    private static GreenfootSound getSoundFromCache(String file) {
        GreenfootSound sound = SOUND_CACHE.get(file);
        if (sound == null) {
            sound = new GreenfootSound(file);
            SOUND_CACHE.put(file, sound);
        }
        return sound;
    }

}
