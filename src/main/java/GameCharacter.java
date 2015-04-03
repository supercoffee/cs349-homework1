/**
 * Created by Benjamin Daschel on 4/2/15.
 */
public abstract class GameCharacter {

    protected Guitar mGuitar;
    protected Solo mSolo;

    /**
     *
     * @param solo the guitar this game character will play
     * @return the current object
     */
    public GameCharacter setSolo(Solo solo){
        mSolo = solo;
        return this;
    }

    /**
     *
     * @param guitar the type of solo this character will perform
     * @return the current object
     */
    public GameCharacter setGuitar(Guitar guitar){
        mGuitar = guitar;
        return this;
    }

    /**
     * Display a message on the screen about the guitar being played.
     * Sorry, it doesn't really play audio.
     *
     * If the behavior hasn't been set, the method simply returns.
     */
    public void playGuitar(){
        //too bad I can't just check if(!mGuitar) like C or PHP
        if (mGuitar != null) {
            mGuitar.play();
        }
    }

    /**
     * Display a message about the character performing a ridiculous solo.
     * Just use your imagination.
     *
     * If the behavior hasn't been set, the method simply returns.
     */
    public void performSolo(){
        if (mSolo != null) {
            mSolo.performSolo();
        }
    }

    public void sayName(){
        System.out.println(this.getName());
    }

    /**
     * Why not just implement this method right here you ask?  Well,
     * that would require me to use a member variable to hold the name. That's
     * not a big deal, except that then I would have to call a setter from within the
     * subclass's constructor or implement a super constructor with arguments.  Please
     * see the README.md included with this project for more details.
     *
     * Also notice the fact that there is no setName method. This is intended;
     * there need not be an interface to swap the character's name at runtime.
     *
     * @return The player's name
     */
    protected abstract String getName();

}
