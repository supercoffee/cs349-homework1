/**
 * Created by ben on 4/2/15.
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

    public void playGuitar(){
        mGuitar.play();
    }

    public void performSolo(){
        mSolo.perform();
    }

}
