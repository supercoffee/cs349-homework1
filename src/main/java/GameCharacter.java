/**
 * Created by ben on 4/2/15.
 */
public abstract class GameCharacter {

    private Guitar mGuitar;
    private Solo mSolo;

    public GameCharacter(Solo solo){

    }

    public GameCharacter(Guitar guitar){

    }

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
     */
    public void performSolo(){
        if (mSolo != null) {
            mSolo.perform();
        }
    }

    public void sayName(){
        System.out.println(this.getName());
    }

    protected abstract String getName();

}
