package com.allen.pattern.command;

/**
 * @program: pattern
 * @description: CommandTest Broker 类来接受并执行命令  客户类。
 *
 * 顺序：调用者→接受者→命令
 *
 * @author: allen小哥
 * @Date: 2019-03-30 21:44
 **/
public class CommandTest {

    public static void main(String[] args){
        // 定义接收者
        Stock stock = new Stock();
        // 定义命令
        BuyOrder buyOrder = new BuyOrder(stock);
        SellOrder sellOrder = new SellOrder(stock);
        // 定义调用者
        Broker broker = new Broker();
        broker.takeOrder(buyOrder);
        broker.takeOrder(sellOrder);
        // 执行调用者方法去执行命令
        broker.placeOrders();
    }

}
