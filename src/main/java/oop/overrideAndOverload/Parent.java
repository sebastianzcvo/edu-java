package oop.overrideAndOverload;

//overriding requires exactly same function:
//  - same name
//  - same parameter types and quantity
//  - same return type

//overloading requires same name and different parameters:
//  - same name
//  - different parameters types or quantity
//  - return type can vary
public class Parent {
    int overriding(int a) {
        return 0;
    }

    String overloading() {
        return "0";
    }

    int overloading(int a) {
        return a;
    }
}

class Child extends Parent {
    @Override
    int overriding(int b) {
        return 1;
    }
}
