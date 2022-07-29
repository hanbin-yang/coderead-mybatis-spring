package com.homestead;

import com.homestead.entity.User;
import com.homestead.mapper.UserMapper;
import com.homestead.po.UserPo;
import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HanBin_Yang
 * @since 2022/7/29 20:52
 */
public class OgnlTest {
    private SqlSessionFactory factory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(NestMapCircularTest.class.getResourceAsStream("/mybatis-config.xml"));
    }

    @Test
    public void ognlTest() {
        UserPo userPo = new UserPo();
        userPo.setId(12);
        userPo.setName("xiaoming");
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        boolean result = evaluator.evaluateBoolean("id != null", userPo);
        System.out.println("result = " + result);
    }

    @Test
    public void forEachTest() {
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> ids = new ArrayList<>(Arrays.asList(111, 112));
        List<User> res =  userMapper.selectByIds(ids);
        System.out.println("end");
    }
}
