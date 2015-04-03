import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Benjamin Daschel on 4/2/15.
 */
public class Homework1 {

    public static void main(String[] args) {

        System.out.println("Creating characters with no guitars or solo.");

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

        Guitar[] guitars = new Guitar[] {new GibsonSG(), new FenderTelecaster(), new GibsonFlyingV()};
        Solo[] solos = new Solo[] {new GuitarOnFire(), new GuitarSmash(), new StageJump()};

        System.out.println("Okay, now we're going to swap in some guitars and solo behaviors on the fly.");

        for(int i = 0; i < characters.size(); i++){
            GameCharacter gc = characters.get(i);
            Guitar guitar = guitars[i];
            Solo solo = solos[i];
            /*
                And now boys and girls, that's what a fluent interface looks like.
                In the case of some more complex classes where many options can be specified,
                a Builder class is used instead. The Builder class will usually contain all the setters
                with the validation logic and state machine management and simply output a ready to use object.
             */
            gc.setGuitar(guitar)
                    .setSolo(solo);

            /*
                Normally you don't chain together "action" methods with a Fluent interface.
                Typically, the use of a fluent interface is used for configuring an object
                or building a query.  Usually the very last call in the Fluent "chain" is the
                "action" call.
             */
            System.out.println("#############################");
            gc.sayName();
            gc.playGuitar();
            gc.performSolo();
        }

    }

}
