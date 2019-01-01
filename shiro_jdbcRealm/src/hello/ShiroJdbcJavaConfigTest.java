package hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ShiroJdbcJavaConfigTest {
    public static void main(String[] args) {
        //安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //身份验证器
        ModularRealmAuthenticator auhthenticator = new ModularRealmAuthenticator();
        auhthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(auhthenticator);
        //权限验证器
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);
        //数据源
        MyRealm2 myRealm2 = new MyRealm2();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myshiro?serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        myRealm2.setJdbcTemplate(jdbcTemplate);
        securityManager.setRealm(myRealm2);
        //开始验证
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("root@shiro.com","root123");
        try {
            currentUser.login(token);
            System.out.println("身份验证成功");
           if(currentUser.hasRole("admin")){
               System.out.println("角色存在");
           }else {
               System.out.println("角色不存在");
           }
//            currentUser.isPermitted("xxx");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("身份验证失败");
        }


    }
}
