package hello;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ShiroTest {
    @Test
    public void test1(){
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName();
        JdbcRealm jdbcRealm = new JdbcRealm();
//        jdbcRealm.setDataSource();
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("");
        SecurityManager securityManager = factory.getInstance();
    }
}
