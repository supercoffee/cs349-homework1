**Benjamin Daschel**
**CS 349 Spring 2015**
**Homework 1**

## About
***
This project covers the use of the strategy pattern in Java.  There is no CLI for this project, only an automated test runner main program. This project demonstrates the use of the strategy pattern to dynamically change the behavior of objects during runtime. 
## How to build and run this project
***

**Building**
Mac/Linux: `./gradlew build`
Windows: `gradlew.bat build`

**Running**
Mac/Linux: `./gradlew run`
Windows: `gradlew.bat run`

## Q & A
***
**Question:** Why didn't you create a constructor for any of the character classes?
**Answer:** As I began to implement the pattern, I realized that some of the limitations of Java required me to duplicate a lot of code. Exhibit A, the constructor dinosaur.  This ancient creature was conceived long before the notion of inherited explicit valued constructors (EVCs). Contrast this with PHP where subclasses can inherit EVCs from the parent class for free. Consider the following from the _perspective of a programmer implementing a concrete class from someone else's superclass_. 
Why should a subclass be forced to pass on all the arguments to it's superclass when those arguments will be assigned to private variables inside the superclass anyways? The previous notion is especially frustrating considering that the super-constructor call must be the first function called inside an EVC.  This can make maintaining constructors between multiple levels of classes into a mess. And once you've implemented an EVC in a subclass, you lose the free *sensible defaults constructor* (SDC) provided by Java.  And if you try to implement an SDC in the subclass, the compiler complains that the superclass does not have an SDC.  You probably have the source code, but chances are that it's part of a library.  Do you really want to risk breaking compatibility with the upstream library? No.


Let's say the library you are using has the following abstract class and you are making a concrete implementation of it.  

```java
public abstract class GameCharacter {

    private Guitar mGuitar;
    private Solo mSolo;
    private String nName;

    public GameCharacter(Guitar guitar, Solo solo, String name){
        mGuitar = guitar;
        //...
    }

	...

```
And lets also say that you wanted to have the following constructors available for your class: 

```java
public class JimiHendrix extends GameCharacter {

    public JimiHendrix(Guitar guitar, Solo solo) {
        super(guitar, solo, "Jimi Hendrix");
    }

    public JimiHendrix(){
        /*
			You had better hope that the superclass is okay with null pointers.
			Never know when you might step on someone else's bug.
         */
        super(null, null, "Jimi Hendrix");
    }

	...

```

You've got a few options. You can do as showing in the code snippet above, but that's not always guaranteed to be safe. You could leave out the SDC. You would need to carefully ensure that all of your usages of JimiHendrix have a Guitar and a Solo object ready to go for the constructor. Or worse yet, you might be tempted to pass a `null` pointer. 

From the library maintainer's point of view, it might make sense for she/he to include a couple of constructors for you to use.  Most classes in the default Java library do this.  But lets assume for the moment that the superclass no longer has two or three arguments; instead it has ten.  This could be a GUI element with many different non-nullable parameter.  Do you really want to make constructors with sensible defaults for all the parameters that cannot be `null`? More than likely, you'll just make one big ugly constructor that looks like this:

```java
	public FooClass(int width, int height, int flags, String title, boolean closeOnTouch, boolean 
		someOtherOption) {...}
``` 
And that kind of thing looks even worse when you instantiate one. It's not really obvious what all the arguments to this constructor call even mean. **Example:**``` new FooClass(32, 64, 1, "Click Me!", false, true);``` 

If you happen to be one of the kind library creators and include several constructors with your class, programmers using your library might run into problems with ambiguous constructor references if you force they are allowed to pass `null` into the constructor. Suppose you create the following constructors:

```java	
	// Note: It's okay for the requestBody, listener, and errorListener arguments to be null. 
	public JsonRequest(int method, String url, String requestBody, Listener<T> listener,
		ErrorListener errorListener) { ... }

	// Being the nice programmer that you are, you allow a URL object as an alternative. 
	public JsonRequest(int method, URL url, String requestBody, Listener<T> listener,
		ErrorListener errorListener) { ... }

```

This scenario will evoke an ambiguous constructor error during compile if the caller of your constructor decides to pass a `null` argument for any of the nullable arguments. **Example:** ` new JsonRequest(0, "http://example.com", successListener, null);` Don't believe me? Check out the `demo/passing_nulls_from_default_constructor` branch of my repository and try to compile it with Java 7.  Of course, none of this ambiguous `null` stuff would be a problem if you _never_ allow nullable values. But then, you would have to implement a constructor for every configuration scenario you want to accommodate, which results in the creation of boiler plate code.   

###tl;dr
 Explicit value constructors in Java...
* are not very maintainable.
* are not expressive.
* are mostly boiler plate.
* can make your relationship with the compiler really awkward.
* shouldn't be used for any class you intend to create subclasses of. 

***

    
**Question:** Why do all your setters return `this`?

**Answer:** Since I chose not use EVCs on the GameCharacter classes, it seemed reasonable to create a [fluent interface](http://en.wikipedia.org/wiki/Fluent_interface).  Fluent interfaces allow implementing programmers to create custom objects on the fly in a readable self-documenting manner.  Very basic fluid interfaces can simply  be retrofitted to existing classes by returning values from setters. 

Advanced use cases for the Fluent interface allow the implementer to control which order the methods are called in.  Consider an object that must be initialized in a certain sequence.  Normally, using setters, you would have to internally track the state of the object and check the state in each setter to ensure proper order is followed.  If the order is violated, the only reasonable action is to throw an `InvalidStateException`.  Consider a fragile state machine whose various states must be transitioned to in a specific order before something cool can happen. If you'd like to run this code, please have a look at my [java-fluent-demo](https://github.com/supercoffee/java-fluent-demo) repo on Github. 

```java
/**
 * This state machine object must be initialized in the following order:
 * doFirstThing()
 * doSecondThing()
 * doOptionalThirdThing()
 * doForthThing()
 *
 * If the order is not followed, an exception will be thrown.  Only once this sequence
 * has been followed will it be able to do something cool. Hence why it is a fragile 
 * state machine.
 *
 * For the purpose of real-world ness, imagine that our FragileStateMachine is a
 * MediaPlayer that takes a file name,  opens the file, decodes the audio from 
 * the file, lets you specify a few flags,  and finally lets you play back
 * an audio track.  However, you have to call all those methods in the right order 
 * otherwise  the whole thing blows up.
 *
 * Notice that the FragileStateMachine has no references to it's builder.  That 
 * means that  we have a one  way relationship here.  The FragileStateMachine has
 * no influence  over the builder.  Also notice how the only way to get a 
 * ready-to-use FragileStateMachine is to follow the method chain required
 * by the builder.  No more complex state transition checks needed.
 *
 */
public class FragileStateMachine {

    private void doFirstThing(String file){
        System.out.println("Opening file : " + file);
    }

    private void doSecondThing(String args){
        System.out.println("Setting arguments: "+ args);
    }

    private void doOptionalThirdThing(int flags){
        System.out.println("Setting flags: "+ flags);
    }

    private void doFourthThing(){
        System.out.println("Running magical decoding algorithm");
    }

    public void doSomeThingCool(){
        System.out.println("Something cooooooool!!!");
    }

    public static class Builder{

        private FragileStateMachine mStateMachine;

        public Builder(){
            mStateMachine = new FragileStateMachine();
        }

        /**
         * Do the first thing
         */
        public BuilderStep2 step1(String filename){
            return new BuilderStep1().step1(filename);
        }

        /**
         * The actual logic of the first step is encapsulated by the 
		  * BuilderStep1 class. Any data validation logic could go in here too.
         */
        public class BuilderStep1{
            public BuilderStep2 step1(String filename){
                mStateMachine.doFirstThing(filename);
                return new BuilderStep2();
            }
        }

        /**
         * Just do step2 and return a BuilderStep3. 
         */
        public class BuilderStep2{
            public BuilderStep3 step2(String args){
                mStateMachine.doSecondThing(args);
                return new BuilderStep3();
            }
        }

        /**
         * Because this step 3 on the FragileStateMachine is optional,
         * we implement the optional step here, and delegate the next step's 
		  * implementation to the BuilderStep4 class.
         */
        public class BuilderStep3{
            public BuilderStep4 optionalStep3(int flags){
                if(flags > 0){ // some mock data validation
                    mStateMachine.doOptionalThirdThing(flags);
                }
                return new BuilderStep4();
            }

            /**
             * A shortcut method to skip over the optional step which
             * delegates it's implementation to BuilderStep4.
             * @return a ready to go FragileStateMachine
             */
            public FragileStateMachine doFourthThing(){
              return new BuilderStep4().step4();
            }
        }

        public class BuilderStep4{

            /**
             * @return A ready to go FragileStateMachine.
             */
            public FragileStateMachine step4(){
                mStateMachine.doFourthThing();
                return mStateMachine; //now we are finally done with the builder.
            }
        }
    }
}

```

