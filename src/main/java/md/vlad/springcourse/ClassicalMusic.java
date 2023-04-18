package md.vlad.springcourse;

public class ClassicalMusic implements Music {
    private ClassicalMusic(){}

    public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }

    public void doMyInit(){
        System.out.println("Doing my initialization ...");
    }

    public void doMyDestroy(){
        System.out.println("Doing my destruction ...");
    }

    private String song;

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String getSong() {
        return song != null ? song : "Hungarian Rhapsody";
    }
}
