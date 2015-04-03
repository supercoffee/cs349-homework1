## Homework 1
Hello world! My name is Dr Read Me, MD. I am here today to present to you my latest work of `code formatting artwork!!`

#### Q & A
Q: Why didn't you create a constructor for any of the character classes?  As I began to implement the pattern, I realized that some of the limitations of Java required me to duplicate a lot of code. Exhibit A, the contstuctor dinosour.  This ancient creature was conceived long before the notion of inherited explicit valued constructors (EVCs). Contrast this with PHP where subclasses can inherit EVCs from the parent class for free.  Why should a subclass be forced to pass on all the arguments to it's superclass when those arguments will be assigned to private variables inside the superclass anyways? The previous notion is especially frustrating considering that the superconstructor call must be the first function called inside an EVC?  

Q: Why do all your setters return `this`? 
Since I didn't provide any EVCs to use on the GameCharacter classes, it seemed sensible to create a fluent interface (FI).  Fluent interfaces allow implementing programmers to create custom objects on the fly in a self documenting manner.  Why should a setter return nothing when it can return something useful to the caller?

Q: 

Changes my strategy as I implement the pattern.  See what I did there? Strategy pattern. 