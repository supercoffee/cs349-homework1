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

        /*
            Demonstrates the behavior of each character when no Solos or Guitars
            have been set on the instances.  See method documentation for more details.
         */
        for(GameCharacter gc : characters){
            gc.sayName();
            gc.playGuitar();
            gc.performSolo();
        }

        for(GameCharacter gc : characters){

            /*
                And now boys and girls, that's what a fluent interface looks like.
                In the case of some more complex classes where many options can be specified,
                a Builder class is used instead. The Builder class will usually contain all the setters
                with the validation logic and state machine management and simply output a ready to use object.
             */
            gc.setGuitar(new FenderTelecaster())
                    .setSolo(new GuitarOnFire());
        }

    }

}
