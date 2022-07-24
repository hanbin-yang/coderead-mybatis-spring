package com.homestead;

import com.homestead.entity.Blog;
import com.homestead.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * @author HanBin_Yang
 * @since 2022/7/24 10:08
 */
public class NestMapTest {

    private SqlSessionFactory factory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(NestMapTest.class.getResourceAsStream("/mybatis-config.xml"));
    }


    @Test
    public void testAssociation() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Blog blog = mapper.selectBlogByIdAssociation(10);
        System.out.println("blog = " + blog);
    }

    @Test
    public void testCollection() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Blog blog = mapper.selectBlogByIdCollection(10);
        System.out.println("blog = " + blog);
    }
}
