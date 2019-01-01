package hello;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;



public class ShiroJdbcIniConfigTest {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("root@shiro.com","root123");
        try {
            currentUser.login(token);
            System.out.println("----登录成功");
            //角色判断
            if(currentUser.hasRole("admin")){
                System.out.println("拥有admin角色");
            }else{
                System.out.println("没有admin角色");
            }
            //权限判断
            if(currentUser.isPermitted("/add.html")){
                System.out.println("拥有add权限");
            }else {
                System.out.println("没有add权限");
            }
            if (currentUser.isPermitted("/get.html")) {
                System.out.println("拥有query权限");
            } else {
                System.out.println("没有query权限");

            }

        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("----登录失败");
        }

    }
}
