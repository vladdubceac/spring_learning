package md.vlad.springcourse;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {
    private String song;

    private ClassicalMusic() {
    }

    public static ClassicalMusic getClassicalMusic() {
        return new ClassicalMusic();
    }

    public void doMyInit() {
        System.out.println("Doing my initialization ...");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction ...");
    }

    @Override
    public String getSong() {
        return song != null ? song : "Hungarian Rhapsody";
    }

    public void setSong(String song) {
        this.song = song;
    }
}
