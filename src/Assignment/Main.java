package Assignment;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ExactMatch e = new ExactMatch();
        SkillMatch s = new SkillMatch();

        e.matchingStrategy();
        s.matchingStrategy();
    }
}
