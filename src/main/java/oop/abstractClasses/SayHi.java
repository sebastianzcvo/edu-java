package oop.abstractClasses;

// just like a normal class, but can't be used to create instances from: abstract classes are meant to be extended
// can contain abstract methods: instance methods that have to be overridden
public abstract class SayHi {
    abstract void method(); // 'private, final, static, synchronized' don't make sense on abstracts methods
}
