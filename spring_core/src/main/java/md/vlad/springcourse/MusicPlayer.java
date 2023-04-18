package md.vlad.springcourse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//@Component
public class MusicPlayer {
    // dependency injection via field
//    private Music rockMusic;
//    private Music classicalMusic;

    private List<Music> musicList;

    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;

    public MusicPlayer(List<Music> musicList){
        this.musicList = new ArrayList<>(musicList);
    }

//    @Autowired
//    public MusicPlayer(@Qualifier("rockMusic") Music rockMusic, @Qualifier("classicalMusic") Music classicalMusic) {
//        this.rockMusic = rockMusic;
//        this.classicalMusic = classicalMusic;
//    }

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
    public void playMusic() {
        int randomNumber = new Random().nextInt(musicList.size());
        System.out.println("Playing : " + musicList.get(randomNumber).getSong());
    }
}
