## Homework 1
Hello world! My name is Dr Read Me, MD. I am here today to present to you my latest work of `code formatting artwork!!`

#### Q & A
**Question:** Why didn't you create a constructor for any of the character classes? <br>
**Answer:** As I began to implement the pattern, I realized that some of the limitations of Java required me to duplicate a lot of code. Exhibit A, the constructor dinosour.  This ancient creature was conceived long before the notion of inherited explicit valued constructors (EVCs). Contrast this with PHP where subclasses can inherit EVCs from the parent class for free. Consider the following from the perspective of a programmer implementing a concrete class from someone else's superclass. <br><br> 
Why should a subclass be forced to pass on all the arguments to it's superclass when those arguments will be assigned to private variables inside the superclass anyways? The previous notion is especially frustrating considering that the superconstructor call must be the first function called inside an EVC.  This can make maintaining constructors between multiple levels of classes into a mess. And once you've implemented an EVC in a subclass, you lose the free *sensible defaults contructor* (SDC) provided by Java.  And if you try to implement an SDC in the subclass, the compiler complains that the superclass does not have an SDC.  You probably have the source code, but chances are that it's part of a library.  Do you really want to risk breaking compatibility with the upstream library? No.

**Question:** Why do all your setters return `this`? <br>
**Answer:** Since I chose not use EVCs on the GameCharacter classes, it seemed reasonable to create a fluent interface (FI).  Fluent interfaces allow implementing programmers to create custom objects on the fly in a self documenting manner.  Why should a setter return nothing when it can return something useful to the caller?

Q: 

Changes my strategy as I implement the pattern.  See what I did there? Strategy pattern. 