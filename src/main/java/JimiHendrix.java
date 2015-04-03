/**
 * Created by ben on 4/2/15.
 */
public class JimiHendrix extends GameCharacter {

    public JimiHendrix(Guitar guitar, Solo solo) {
        super(guitar, solo, "Jimi Hendrix");
    }

    public JimiHendrix(){
        /*
            You had better hope that the superclass is okay with
            you passing in null references. Never know when you might step on
            someone else's bug.
         */
        super(null, null, "Jimi Hendrix");
    }

    @Override
    protected String getName() {
        return "Jimi Hendrix";
    }
}
