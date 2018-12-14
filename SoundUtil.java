import java.util.HashMap;
import java.util.Map;

import greenfoot.GreenfootSound;

public class SoundUtil {

    private static Map<String, GreenfootSound> SOUND_CACHE = new HashMap<String, GreenfootSound>();

    public static void playSound(String file) {
        GreenfootSound sound = SOUND_CACHE.get(file);
        if (sound == null) {
            sound = new GreenfootSound(file);
            SOUND_CACHE.put(file, sound);
        }
        if (!sound.isPlaying()) {
            sound.play();
        }
    }

}
