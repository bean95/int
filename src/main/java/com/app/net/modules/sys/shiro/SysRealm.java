package com.app.net.modules.sys.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.app.net.modules.sys.user.User;

public class SysRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		String username = (String)principalCollection.getPrimaryPrincipal();
		Set<String> roles = getRoles();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		return simpleAuthorizationInfo;
	}

	private Set<String> getRoles(){
		Set<String> set = new HashSet<>();
		set.add("reader");
		set.add("writer");
		System.out.println("======================Realm====get roles from database...");
		return set;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
        User user = findByName(username);
        if (user == null) {
            throw new UnknownAccountException(); //没有找到账号
        }
        //交给AuthenticationRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //密码
                user.getPassword(),
                getName() //realm name
        );
        
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("liang"));
        return authenticationInfo;
	}

	private User findByName(String username) {
		if("jason".equals(username)){
			return new User("jason","ca6b48f157ebb0f3e9c8b04fc52e0494");
		}else{
			return new User("alex","f2d3e8343262b624d6f691b565837543");
		}
	}
	
	public static void main(String[] args){
		System.out.println(new Md5Hash("456","liang")); //salt随机数
	}

}
