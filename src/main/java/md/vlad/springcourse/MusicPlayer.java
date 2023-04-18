package md.vlad.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class MusicPlayer {
    // dependency injection via field
    private Music rockMusic;
    private Music classicalMusic;

    private String name;
    private int volume;

    @Autowired
    public MusicPlayer(@Qualifier("rockMusic") Music rockMusic, @Qualifier("classicalMusic") Music classicalMusic) {
        this.rockMusic = rockMusic;
        this.classicalMusic = classicalMusic;
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
    public void playMusic(MusicGenre musicGenre) {
        int randomNumber = new Random().nextInt(3);
        String song;
        if (MusicGenre.CLASSICAL == musicGenre) {
            song = classicalMusic.getSongs().get(randomNumber);
        } else if (MusicGenre.ROCK == musicGenre) {
            song = rockMusic.getSongs().get(randomNumber);
        } else {
            System.out.println("Song not found for given genre : " + musicGenre);
            return;
        }
        System.out.println("Playing : " + song);
    }
}
