package com.allen.algorithm.red;

import java.util.LinkedList;
import java.util.List;

/**
 * 红包算法
 *   1)、设定最小 最大的抢红包值
 *   2）、限定最大值 * count 与 总金额的关系
 *   3）、随机取值（次数递减方式随机）
 *
 * Created by xuguocai on 2020/12/28 10:04
 */
public class RedPacketUtil {

    /**
     * 生成红包最小值 1 分 (默认为1, 0.01元)
     */
    private static final int MIN_MONEY = 1;

    /**
     * 生成红包最大值 200 人民币 (默认为20000， 200元)
     */
    private static final int MAX_MONEY = 200 * 100;

    /**
     * 小于最小值
     */
    private static final int LESS = -1;

    /**
     * 大于最大值
     */
    private static final int MORE = -2;

    /**
     * 正常值
     */
    private static final int OK =1;

    /**
     * 最大的红包是平均值的 TIMES 倍，防止某一次分配红包较大
     */
    private static final double TIMES = 2.1F;

    /**
     * 递归次数
     */
    private int recursiveCount = 0;

    /**
     * 拆分红包
     * @param money  金额
     * @param count  人数
     * @return
     */
    public List<Integer> splitRedPacket(int money, int count) {
        List<Integer> moneys = new LinkedList<>();

        //金额检查，如果最大红包 * 个数 < 总金额；则需要调大最小红包 MAX_MONEY
        if (MAX_MONEY * count <= money){
            System.err.println("请调大最小红包金额 MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys ;
        }
        //计算出最大红包
        int max = (int) ((money / count) * TIMES);
        // 平均数的两倍 与 最大红包数比较
        max = Math.min(max, MAX_MONEY);

        for (int i = 0; i < count; i++) {
            //随机获取红包
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            //总金额每次减少
            money -= redPacket;
        }

        return moneys;
    }

    /**
     * 随机获取红包
     * @param totalMoney  金额
     * @param minMoney  最小金额
     * @param maxMoney  最大金额
     * @param count   次数/人数
     * @return
     */
    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {
        System.out.println();
        //只有一个红包直接返回
        if (count == 1) {
            System.out.println("随机红包方法 count == 1 的返回值:"+totalMoney);
            return totalMoney;
        }
        // 最小值 = 最大值，返回最小值
        if (minMoney == maxMoney) {
            System.out.println("随机红包方法 minMoney == maxMoney 的返回值:"+minMoney);
            return minMoney;
        }

        //如果最大金额大于了剩余金额 则用剩余金额 因为这个 money 每分配一次都会减小。既是取小值
        maxMoney = Math.min(maxMoney, totalMoney);

        //在 minMoney到maxMoney 生成一个随机红包
        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);
        System.out.println("随机红包redPacket:"+redPacket);

        int lastMoney = totalMoney - redPacket;
        System.out.println("随机红包lastMoney  :"+lastMoney);
        // 校验剩余值
        int status = checkMoney(lastMoney, count - 1);

        //正常金额
        if (OK == status) {
            return redPacket;
        }

        //如果生成的金额不合法 则递归重新生成
        if (LESS == status) {
            recursiveCount++;
            System.out.println("LESS的 recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("MORE recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    /**
     * 校验剩余的金额的平均值是否在 最小值和最大值这个范围内
     *
     * @param lastMoney
     * @param count
     * @return
     */
    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        // 小于最小值，则返回 -1
        if (avg < MIN_MONEY) {
            return LESS;
        }

        // 大于最大值，则返回 -2
        if (avg > MAX_MONEY) {
            return MORE;
        }
        // 返回正常值
        return OK;
    }


    public static void main(String[] args) {
        RedPacketUtil redPacket = new RedPacketUtil();
        List<Integer> redPackets = redPacket.splitRedPacket(10000, 5);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }

}
