package com.allen.pattern.bridge;

/**
 * @ClassName BridgeTest
 * @Description
 * 将类的功能层次结构和实现层次结构相分离，使二者能够独立地变化，并在两者之间搭建桥梁，实现桥接
 *
 * 桥接模式中，具体的实现类放在作为“桥梁”的接口中实现，而“桥梁”接口中只有实现功能的抽象方法；具体实现类是继承“桥梁”，
 * 而不直接继承实现类的抽象类，抽象类与具体的实现类在结构上是相互独立的，两者的相互变化并不会影响到彼此，
 * 只要“桥梁”没变，两者的变化并不会影响到彼此
 *
 * *桥接模式使用对象见的组合关系解耦了抽象和实现之间固有的绑定关系，使得抽象和实现可以沿着各自的维度来变化。
 * 所谓抽象和实现沿着各自维度的变化，即“子类化”它们，得到各个子类之后，便可以任意它们，从而获得不同路上的不同其次。
 * 桥接模式有时候类似于多继承方案，但是多继承方案往往违背了SRP原则，复用性较差。桥接模式是比继承方案更好的解决方法。
 * 桥接模式的应用一般在“两个非常强的变化维度”，有时候即使有两个变化的维度，但是某个方向的变化维度并不剧烈——换而言之两个变化不会导致纵横交错的结果，并不一定要使用桥接模式。
 *
 *
 *
 * @Author Xu
 * @Date 2019/3/26 17:05
 **/
public class BridgeTest {

    public static void main(String[] args){
        // 将类与接口实现类连接在一起， 即桥接
        AbstractShape shape = new RefinedAbstractionCircle(100,new ConcreteImplementorRedCircle());
        AbstractShape white = new RefinedAbstractionCircle(300,new ConcreteImplementorWhiteCircle());

        shape.draw();

        white.draw();
    }

}
