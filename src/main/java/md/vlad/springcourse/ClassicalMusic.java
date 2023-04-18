package md.vlad.springcourse;

public class ClassicalMusic implements Music {
    private String song;

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String getSong() {
        return song != null ? song : "Hungarian Rhapsody";
    }
}
