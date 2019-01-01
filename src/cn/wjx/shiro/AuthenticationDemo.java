package cn.wjx.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;


/**
 * 完成用户认证功能
 */
public class AuthenticationDemo {
    public static void main(String[] args) {
        //1.创建SecurityManager工厂
        Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.通过SecurityManager工厂获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        //3.将securityManager对象设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.通过SecurityUtils获取主体(Subject)
        Subject subject = SecurityUtils.getSubject();
        //5.假设登录的用户名zhangsan 密码：1111。这个地方的zhangsan和1111表示用户使用浏览器登录时输入的信息
        //而shiro.ini文件中用户信息相当于数据库中存放的用户信息
        UsernamePasswordToken token = new UsernamePasswordToken("tom","202");
        try {
            //6.进行用户身份验证
            subject.login(token);
            //7.通过subject来判断用户是否通过验证
            if(subject.isAuthenticated()) {
                System.out.println("用户登录成功");
                //查看是否有角色
                if(subject.hasRole("admin")){
                    System.out.println("有admin角色");
                }else{
                    System.out.println("没有admin角色");
                }
                //查看是否有权限
                if(subject.isPermitted("del")){
                    System.out.println("有删除权限");
                }else{
                    System.out.println("没有删除权限");
                }
                if(subject.isPermitted("update")){
                    System.out.println("有update权限");
                }else{
                    System.out.println("没有update权限");
                }
                if(subject.isPermittedAll("del","add")){
                    System.out.println("同时有删除和添加的权限");
                }else{
                    System.out.println("同时没有删除和添加的权限");
                }
                if(subject.isPermitted("searc")){
                    System.out.println("有查询权限");
                }else{
                    System.out.println("没有查询权限");
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("用户登录失败");
        }

    }
}
