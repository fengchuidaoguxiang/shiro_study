package cn.wjx.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

//自定义数据源
public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //限制数据源只支持UsernamePasswordToken类型，判断下是否支持UsernamePasswordToken类型
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        //模拟从数据库中查看该用户账号名和密码是否正确
        if(!"kitty".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"333".equals(password)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
