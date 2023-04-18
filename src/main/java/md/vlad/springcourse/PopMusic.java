package md.vlad.springcourse;

import java.util.ArrayList;
import java.util.List;

public class PopMusic implements Music{
    private List<String> songs = new ArrayList<>();

    {
        songs.add("Billie Jean");
        songs.add("Beat it");
        songs.add("Dragostea din tei");
    }

    @Override
    public List<String> getSongs() {
        return songs;
    }
}
