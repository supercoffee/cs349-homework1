import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ben on 4/2/15.
 */
public class Homework1 {

    public static void main(String[] args) {
        // Gotta love them varargs methods
        List<GameCharacter> characters = Arrays.asList(
                new AngusYoung(),
                new Slash(),
                new JimiHendrix()
        );

        for(GameCharacter gc : characters){
            gc.sayName();
            gc.playGuitar();
            gc.performSolo();
        }


    }
}
