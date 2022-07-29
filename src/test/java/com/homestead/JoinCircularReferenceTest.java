package com.homestead;

import com.homestead.entity.Blog;
import com.homestead.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 联合查询映射 -- 循环依赖
 * @author HanBin_Yang
 * @since 2022/7/24 22:26
 */
public class JoinCircularReferenceTest {
    private SqlSessionFactory factory;

    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(FirstCacheTest.class.getResourceAsStream("/mybatis-config.xml"));
    }


    @Test
    public void leftJoinCircularTest() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Blog blog = mapper.selectByBlogIdCollectionCircu(10);
        System.out.println("end");
    }
}
