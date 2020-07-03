package design;

public class Principle {
    // Open Closed Principle(开闭原则):
    //      Software entities should be open for extension，but closed for modification
    //      对拓展开放，对修改封闭

    // Liskov Substitution Principle(里氏替换原则):
    //      Inheritance should ensure that any property proved about supertype objects also holds for subtype objects
    //      继承关系中，尽量不要重写父类方法

    // Dependence Inversion Principle(依赖倒置原则):
    //      High level modules should not depend upon low level modules.
    //      Both should depend upon abstractions.
    //      Abstractions should not depend upon details.
    //      Details should depend upon abstractions
    //      高层模块不应该依赖底层模块
    //      两者都应该以来抽象
    //      抽象不该依赖细节
    //      细节应该依赖抽象

    // Single Responsibility Principle(单一职责原则):
    //      There should never be more than one reason for a class to change
    //      不要有超过一个的引起类变化的原因

    // Interface Segregation Principle(接口隔离原则):
    //      Clients should not be forced to depend on methods they do not use
    //      The dependency of one class to another one should depend on the smallest possible interface
    //      用户不应该依赖他们用不到的方法
    //      一个类对另一个类的依赖应该建立在最小的接口上

    // Law of Demeter(迪米特法则):
    //      Talk only to your immediate friends and not to strangers
    //      只和你最接近的朋友聊天
    //      如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，可以通过第三方转发该调用
    //      例子：粉丝-经纪人-明星

    // Composite Reuse Principle(合成复用原则):
    //      它要求在软件复用时，要尽量先使用组合或者聚合等关联关系来实现，其次才考虑使用继承关系来实现


}
