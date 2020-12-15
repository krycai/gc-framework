package com.allen.pattern.command;

/**
 * @program: pattern
 * @description: CommandTest Broker 类来接受并执行命令
 *
 * 顺序：调用者→接受者→命令
 *
 * @author: allen小哥
 * @Date: 2019-03-30 21:44
 **/
public class CommandTest {

    public static void main(String[] args){
        Stock stock = new Stock();

        BuyOrder buyOrder = new BuyOrder(stock);
        SellOrder sellOrder = new SellOrder(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyOrder);
        broker.takeOrder(sellOrder);

        broker.placeOrders();
    }

}
