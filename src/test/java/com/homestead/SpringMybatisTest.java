package com.homestead;

import com.homestead.entity.User;
import com.homestead.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author HanBin_Yang
 * @since 2022/4/5 21:54
 */
public class SpringMybatisTest {

    @Test
    public void testBySpring() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        //  动态代理          SqlSession                动态代理                  mybatis
        //    |                  |                       |                        |
        //   \|/                \|/                     \|/                      \|/
        // mapper -> SqlSessionTemplate  ->  SqlSessionInterceptor   ->   SqlSessionFactory

        DataSourceTransactionManager txManager = context.getBean("transactionManager", DataSourceTransactionManager.class);
        // 手动开启事务
        TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionDefinition());

        User u1 = mapper.selectUserByUserId(111);
        User u3 = mapper.selectUserByUserId(111);
        User u2 = mapper.selectById(111);
        System.out.println("u1 == u2 " + (u1 == u2));
    }
}
