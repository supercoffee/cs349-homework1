## Homework 1
Hello world! My name is Dr Read Me, MD. I am here today to present to you my latest work of `code formatting artwork!!`

#### Q & A
***
**Question:** Why didn't you create a constructor for any of the character classes?
**Answer:** As I began to implement the pattern, I realized that some of the limitations of Java required me to duplicate a lot of code. Exhibit A, the constructor dinosour.  This ancient creature was conceived long before the notion of inherited explicit valued constructors (EVCs). Contrast this with PHP where subclasses can inherit EVCs from the parent class for free. Consider the following from the _perspective of a programmer implementing a concrete class from someone else's superclass_. 
Why should a subclass be forced to pass on all the arguments to it's superclass when those arguments will be assigned to private variables inside the superclass anyways? The previous notion is especially frustrating considering that the superconstructor call must be the first function called inside an EVC.  This can make maintaining constructors between multiple levels of classes into a mess. And once you've implemented an EVC in a subclass, you lose the free *sensible defaults contructor* (SDC) provided by Java.  And if you try to implement an SDC in the subclass, the compiler complains that the superclass does not have an SDC.  You probably have the source code, but chances are that it's part of a library.  Do you really want to risk breaking compatibility with the upstream library? No.


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

This kind of this will create an ambiguous constructor error during compile if the caller of your constructor decides to pass a `null` argument for any of the nullable arguments. **Example:** ` new JsonRequest(0, "http://example.com", successListener, null);` Don't believe me? Check out the `demo/passing_nulls_from_default_constructor` branch of my repository and try to compile it with Java 7.  Of course, none of this ambiguous `null` stuff would be a problem if you _never_ allow nullables. But then, you would have to implement a constructor for every conceivable scenario. But that's an obscene amount of boilerplate...  

###tl;dr
 Explicit value constructors in Java...
* are not very maintainable.
* are not expressive.
* are mostly boiler plate.
* can make your relationship with the compiler really awkward.
* shouldn't be used for any class you intend to create subclasses of. 

***

    
**Question:** Why do all your setters return `this`?
**Answer:** Since I chose not use EVCs on the GameCharacter classes, it seemed reasonable to create a fluent interface (FI).  Fluent interfaces allow implementing programmers to create custom objects on the fly in a self documenting manner.  Why should a setter return nothing when it can return something useful to the caller?


Changes my strategy as I implement the pattern.  See what I did there? Strategy pattern. 