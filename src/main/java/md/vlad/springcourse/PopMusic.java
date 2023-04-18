package md.vlad.springcourse;

import org.springframework.stereotype.Component;

@Component
public class PopMusic implements Music{
    private String song;

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String getSong() {
        return song!=null ? song : "Billie Jean";
    }
}
