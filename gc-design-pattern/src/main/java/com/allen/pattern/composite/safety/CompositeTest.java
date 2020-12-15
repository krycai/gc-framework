package com.allen.pattern.composite.safety;

/**
 * @ClassName CompositeTest
 * @Description TODO
 * 组合模式组合多个对象形成树形结构以表示“整体-部分”的结构层次。
 *
 * 叶子对象和组合对象实现相同的接口。这就是组合模式能够将叶子节点和对象节点进行一致处理的原因。
 *
 * 优点：
 *   1、可以清楚地定义分层次的复杂对象，表示对象的全部或部分层次，使得增加新构件也更容易。
 *   2、客户端调用简单，客户端可以一致的使用组合结构或其中单个对象。
 *   3、定义了包含叶子对象和容器对象的类层次结构，叶子对象可以被组合成更复杂的容器对象，而这个容器对象又可以被组合，这样不断递归下去，可以形成复杂的树形结构。
 *   4、更容易在组合体内加入对象构件，客户端不必因为加入了新的对象构件而更改原有代码。
 *
 * 缺点：
 *    1、使设计变得更加抽象，对象的业务规则如果很复杂，则实现组合模式具有很大挑战性，而且不是所有的方法都与叶子对象子类都有关联
 *
 * 场景：
 *   1、需要表示一个对象整体或部分层次，在具有整体和部分的层次结构中，希望通过一种方式忽略整体与部分的差异，可以一致地对待它们。
 *   2、让客户能够忽略不同对象层次的变化，客户端可以针对抽象构件编程，无须关心对象层次结构的细节。
 *   https://www.cnblogs.com/chenssy/p/3299719.html
 *
 *   主要解决：它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，客户程序可以向处理简单元素一样来处理复杂元素，从而使得客户程序与复杂元素的内部结构解耦。
 *   何时使用： 1、您想表示对象的部分-整体层次结构（树形结构）。 2、您希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
 *
 * @Author Xu
 * @Date 2019/3/26 19:39
 **/
public class CompositeTest {

    public static void main(String[] args){
        Folder folder = new Folder("总文件夹");

        ImageFile imageFile = new ImageFile("图片文件夹");
        VedioFile vedioFile = new VedioFile("视频文件夹");

        Folder tempFolder = new Folder("临时文件夹");

        folder.add(imageFile);
        folder.add(vedioFile);

        ImageFile imageFile2 = new ImageFile("图片文件夹C");
        VedioFile vedioFile2 = new VedioFile("视频文件夹C");

        tempFolder.add(imageFile2);
        tempFolder.add(vedioFile2);

        tempFolder.display();

        System.out.println("=======================");

        folder.display();
    }

}
