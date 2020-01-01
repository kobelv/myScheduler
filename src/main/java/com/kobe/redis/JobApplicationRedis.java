package com.kobe.redis;

import java.util.Calendar;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

public class JobApplicationRedis {
	private static final String REDIS_SERVER = "192.168.1.12";
	private static final int PORT = 6379;
	private static JedisPool jedisPool = new JedisPool(REDIS_SERVER, PORT);
	private static final int TOTAL_JOB = 100000;
	
	private static Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	public static void main(String[] args) {
		JobApplicationRedis app = new JobApplicationRedis();
		System.out.println("-----------start to produce msg:");
		app.produceDelayMessage();
		System.out.println("-----------start to consume msg:");
		app.consumeDelayMessage();
	}

	public void consumeDelayMessage() {
		Jedis jedis = JobApplicationRedis.getJedis();
		Calendar calendar = null;
		
		while(true){
			Set<Tuple> set = jedis.zrangeWithScores("MessageID", 0, 1);
			if(set == null || set.isEmpty()){
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			calendar = Calendar.getInstance();
			int score = (int)((Tuple)set.toArray()[0]).getScore();
			if((int)(calendar.getTimeInMillis()/1000) > score){
				String messageId = ((Tuple)set.toArray()[0]).getElement();
				Long result = jedis.zrem("MessageID", messageId);
				if(result != null && result>0){
					System.out.println(System.currentTimeMillis()+
							"ms:redis consumed a MessageID: "+messageId);
				}
				
			}
				
		}
		
	}

	public void produceDelayMessage() {
		Jedis jedis = JobApplicationRedis.getJedis();
		for(int i=0; i<TOTAL_JOB; i++){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 1);
			jedis.zadd("MessageID", (int)(calendar.getTimeInMillis()/1000), "msg"+i);
			System.out.println(System.currentTimeMillis()+
					"ms:redis produced a MessageID: "+"msg"+i);
		}
		
	}

}
