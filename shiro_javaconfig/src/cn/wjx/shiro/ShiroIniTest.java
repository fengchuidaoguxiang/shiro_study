package cn.wjx.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 代替ini文件，用代码创建数据源
 */
public class ShiroIniTest {

    public static void main(String[] args) {
        /**
         *         //1.创建SecurityManager工厂
         *         Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro.ini");
         *         //2.通过SecurityManager工厂获取SecurityManager实例
         *         SecurityManager securityManager = factory.getInstance();
         *         上面2步 等价于 下面 1-8步
         *
         */
        //  1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //  2.创建身份验证器
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        // 3.注入验证身份策略到身份验证器中
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        // 4.注入身份验证器到安全管理器中
        securityManager.setAuthenticator(authenticator);
        // 5.创建权限验证器
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        // 6.注入权限解析器到权限验证器
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        // 7.注入权限验证器到安全管理器中
        securityManager.setAuthorizer(authorizer);
        // 8.注入数据源到安全管理器中
        securityManager.setRealm(new MyRealm());
        // 9.将安全管理器注入到上下文中
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("kitty","333");
        try {
            currentUser.login(usernamePasswordToken);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
    }
}
