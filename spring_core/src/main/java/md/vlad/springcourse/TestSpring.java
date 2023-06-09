package md.vlad.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

//        Music music = context.getBean("rockMusic", Music.class);
//        List<Music> musicList = new ArrayList();
//        musicList.add(music);
//        MusicPlayer musicPlayer = new MusicPlayer(musicList);
//        musicPlayer.playMusic();
//        Music music2 = context.getBean("classicalMusic",Music.class);
//        musicList.clear();
//        musicList.add(music2);
//        MusicPlayer classicalMusicPlayer = new MusicPlayer(musicList);
//        classicalMusicPlayer.playMusic();

//        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer",MusicPlayer.class);
//        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer",MusicPlayer.class);
//
//        boolean comparison = firstMusicPlayer == secondMusicPlayer;
//
//        System.out.println(comparison);
//
//        System.out.println(firstMusicPlayer);
//        System.out.println(secondMusicPlayer);
//
//        firstMusicPlayer.setVolume(10);
//
//        System.out.println(firstMusicPlayer.getVolume());
//        System.out.println(secondMusicPlayer.getVolume());
//
//        musicPlayer.playMusic();
//
//        System.out.println(musicPlayer.getName());
//        System.out.println(musicPlayer.getVolume());

//        MusicPlayer musicPlayer = context.getBean("musicPlayer",MusicPlayer.class);
//        musicPlayer.playMusic();

//        Computer computer = context.getBean("computer",Computer.class);
//        System.out.println(computer);

        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        musicPlayer.playMusic();

//        ClassicalMusic classicalMusic1 = context.getBean("classicalMusic", ClassicalMusic.class);
//        ClassicalMusic classicalMusic2 = context.getBean("classicalMusic", ClassicalMusic.class);

//        System.out.println(classicalMusic1 == classicalMusic2);

        context.close();
    }
}
