package com.homestead;

import com.homestead.entity.User;
import com.homestead.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author HanBin_Yang
 * @since 2022/7/24 10:08
 */
public class FirstCacheTest {

    private SqlSessionFactory factory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(FirstCacheTest.class.getResourceAsStream("/mybatis-config.xml"));
    }

    /**
     * 一级缓存命中规则：
     * 1 sq和参数相同
     * 2 必须是相同的·statementid
     * 3 sqlSession 必须一样 （会话级别缓存）
     * RowBounds 返回行范围相同
     * 未手动清空
     * 未调用 sqlSession.clearCache() @Options(flushCache = Options.FlushCachePolicy.TRUE)
     * 未执行update
     * 缓存作用域不是statement-> 嵌套查询
     */
    @Test
    public void test1() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByid(10);
        User user1 = mapper.selectByid(10);
        List<Object> list = sqlSession.selectList("org.coderead.mybatis.UserMapper.selectByid", 10);
        System.out.println("end");
    }

    @Test
    public void test2() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.selectByid(10);
        sqlSession.clearCache();
        mapper.selectByid(10);
    }
}
