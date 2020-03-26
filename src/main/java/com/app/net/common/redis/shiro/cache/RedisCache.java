package com.app.net.common.redis.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.app.net.common.redis.JedisUtil;

@Component
public class RedisCache<K, V> implements Cache<K, V> {
	
	private final static String CACHE_PREFIX = "app-shiro-cache:";
	
	private byte[] getKey(K k){
		if(k instanceof String){
			return (CACHE_PREFIX+k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}

	@Override
	public V get(K key) throws CacheException {
		System.out.println("===============RedisCache=======get form cache.");
		byte[] value = JedisUtil.get(getKey(key));
		if(value != null){
			return (V)SerializationUtils.deserialize(value);
		}
		return null;
	}
	
	@Override
	public V put(K k, V v) throws CacheException {
		System.out.println("===============RedisCache=======put.");
		byte[] key = getKey(k);
		byte[] value = SerializationUtils.serialize(v);
		JedisUtil.set(key, value);
		JedisUtil.expire(key, 600);
		return v;
	}
	
	@Override
	public V remove(K k) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = JedisUtil.get(key);
		JedisUtil.del(key);
		if(value != null){
			return (V)SerializationUtils.deserialize(value);
		}
		return null;
	}
	
	@Override
	public void clear() throws CacheException {
		//不仅仅是shiro的数据
	}

	@Override
	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
