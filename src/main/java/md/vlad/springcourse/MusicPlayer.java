package md.vlad.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MusicPlayer {
    // dependency injection via field
//    @Autowired
//    private Music music;

    private String name;
    private int volume;

    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;

    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

    // dependency injection via constructor
    //    @Autowired
//    public MusicPlayer(Music music) {
//        this.music = music;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    // dependency injection via setter
    //    @Autowired
//    public void setMusic(Music music) {
//        this.music = music;
//    }

    // IoC
    public String playMusic() {
        return new StringBuilder("Playing : ").append(classicalMusic.getSong()).append(" ; ").append(rockMusic.getSong()).toString();
    }
}
