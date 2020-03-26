package com.app.net.common.redis.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.SerializationUtils;

import com.app.net.common.redis.JedisUtil;

public class RedisSessionDao extends AbstractSessionDAO {

	private static final String SHIRO_SESSION_PREFIX = "app-shiro-session:";

	public byte[] getKey(String key) {
		return (SHIRO_SESSION_PREFIX + key).getBytes();
	}
	
	public void saveSession(Session session){
		if(session != null && session.getId() != null){
			byte[] key = getKey(session.getId().toString());
			byte[] value = SerializationUtils.serialize(session);
			JedisUtil.set(key, value);
			JedisUtil.expire(key, 6000);
		}
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.generateSessionId(session);
		assignSessionId(session, sessionId);
		System.out.println("doCreate,id= " + session.getId().toString());
		System.out.println("equals? " + sessionId.toString() + "----" + session.getId().toString());
		saveSession(session);
		return sessionId;
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			return null;
		}
		System.out.println("doReadSession,id=" + sessionId.toString());
		byte[] key = getKey(sessionId.toString());
		byte[] value = JedisUtil.get(key);
		return (Session)SerializationUtils.deserialize(value);
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		System.out.println("update,id=[{" + session.getId().toString() + "}]");
		saveSession(session);

	}

	@Override
	public void delete(Session session) {
		System.out.println("删除session,id=[{" + session.getId().toString() + "}]");
		if(session != null && session.getId() != null){
			byte[] key = getKey(session.getId().toString());
			JedisUtil.del(key);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		//
		Set<byte[]> keys = JedisUtil.keys(SHIRO_SESSION_PREFIX);
		Set<Session> set = new HashSet<>();
		if(CollectionUtils.isEmpty(keys)){
			return Collections.emptySet();
		}
		for(byte[] key : keys){
			Session session = (Session)SerializationUtils.deserialize(JedisUtil.get(key));
			set.add(session);
		}
		return set;
	}

	

}
