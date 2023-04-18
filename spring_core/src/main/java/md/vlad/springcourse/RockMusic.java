package md.vlad.springcourse;

import java.util.ArrayList;
import java.util.List;

//@Component
public class RockMusic implements Music {

    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
