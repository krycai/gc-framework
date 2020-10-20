package com.allen.test;

/**
 * @author xuguocai 2020/8/4 16:05
 */

import com.allen.redis.RedisApp;
import com.allen.redis.jedis.JedisTool;
import com.allen.redis.redis.RedisLock;
import com.allen.redis.redisson.RedissLockHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApp.class)
public class RedistTest {

	@Autowired
	RedisLock redisLock;

	@Test
	public void test(){
		List<Integer> list = new ArrayList<>();
		for (int i =0;i<500;i++){
			list.add(i);
		}

		list.parallelStream().forEach(item -> {
			String token = null;
			try {
				token = redisLock.lock("lock_name", 10000, 11000);
				if (token != null) {
					System.out.println("我拿到了锁哦");
					// 执行业务代码
				} else {
					System.out.println("我没有拿到锁唉");
				}
			} finally {
				if (token != null) {
					redisLock.unlock("lock_name", token);
				}
			}
		}

		);


	}

	@Test
	public void test2(){
		List<Integer> list = new ArrayList<>();
		for (int i =0;i<500;i++){
			list.add(i);
		}

		list.parallelStream().forEach(item -> {
			String value = UUID.randomUUID().toString();
			try {
				boolean lock_name = redisLock.getLock("lock2_name", value, 11000);
				if (lock_name) {
					System.out.println("我拿到了锁哦");
					// 执行业务代码
				} else {
					System.out.println("我没有拿到锁唉");
				}
			} finally {
				redisLock.releaseLock("lock2_name", value);
			}
		}
		);


	}

	@Test
	public void test3(){
		List<Integer> list = new ArrayList<>();
		for (int i =0;i<50;i++){
			list.add(i);
		}

		list.parallelStream().forEach(item -> {
			RLock lock2_name = null;
			try {
				 lock2_name = RedissLockHelper.lock("lock5_name");
				if (lock2_name.isLocked()) {
					System.out.println("===拿到万能锁====");
					// 执行业务代码
				} else {
					System.out.println("====没有拿到万能锁====");
				}
			} finally {
//				RedissLockHelper.unlock("lock5_name");
				if (lock2_name != null){
					RedissLockHelper.unlock(lock2_name);
				}
			}
		}
		);


	}
}
