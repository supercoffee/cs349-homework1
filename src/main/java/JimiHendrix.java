/**
 * Created by ben on 4/2/15.
 */
public class JimiHendrix extends GameCharacter {

    /*
        In the interest of flexibility, you implemented
        both constructors from the superclass.
     */
    public JimiHendrix(Solo solo) {
        super(solo);
    }

    public JimiHendrix(Guitar guitar) {
        super(guitar);
    }

    /*
        Note that this constructor is an ambiguous reference.
        Which parent constructor does it match I wonder?
        I know, let's type cast that null value to shut the compiler up.
     */
    public JimiHendrix(){
        super(null);
        // super((Solo)null);  This works just fine though
    }

    @Override
    protected String getName() {
        return "Jimi Hendrix";
    }
}
