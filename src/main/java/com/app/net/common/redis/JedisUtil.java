package com.app.net.common.redis;

import java.util.Set;

import com.app.net.common.utils.SpringContextHolder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {
	
	private static JedisPool jedisPool = SpringContextHolder.getBean("jedisPool");
	
	public static Jedis getResource() {
        return jedisPool.getResource();
    }
    
    public static byte[] set(byte[] key,byte[] value){
        Jedis jedis = getResource();
        try{
        	jedis.set(key, value);
        	return value;
        }finally{
        	jedis.close();
        }
    }
    
    public static void expire(byte[] key,int i){
    	Jedis jedis = getResource();
        try{
        	jedis.expire(key, i);
        }finally{
        	jedis.close();
        }
    }
    
    public static byte[] get(byte[] key){
        Jedis jedis = getResource();
        try{
        	return jedis.get(key);
        }finally{
        	jedis.close();
        }
    }
    
    public static void replace(byte[] key, byte[] obj){
        set(key,obj);
    }
    
    public static void del(byte[] key){
    	Jedis jedis = getResource();
        try{
        	jedis.del(key);
        }finally{
        	jedis.close();
        }
    }
    
    public static Set<byte[]> keys(String pattern){
    	Jedis jedis = getResource();
        try{
        	return jedis.keys(pattern.getBytes());
        }finally{
        	jedis.close();
        }
    }

}
