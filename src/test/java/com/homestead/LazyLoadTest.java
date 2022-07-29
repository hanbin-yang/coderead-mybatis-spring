package com.homestead;

import com.homestead.entity.Blog;
import com.homestead.entity.Comment;
import com.homestead.mapper.UserMapper;
import com.homestead.util.Bean;
import com.homestead.util.SerializeUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * @author HanBin_Yang
 * @since 2022/7/24 11:42
 */
public class LazyLoadTest {
    private SqlSessionFactory factory;
    private static Configuration configuration;
    @Before
    public void init() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(NestMapCircularTest.class.getResourceAsStream("/mybatis-config.xml"));
        configuration = factory.getConfiguration();
        configuration.setLazyLoadTriggerMethods(new HashSet<>());
    }


    @Test
    public void testSerialize() throws IOException, ClassNotFoundException {
        Bean bean = new Bean();
        byte[] bytes = SerializeUtil.writeObject(bean);
        Object o = SerializeUtil.readObject(bytes);
        System.out.println("end");
    }

    @Test
    public void testLazyLoad() throws IOException, ClassNotFoundException {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Blog blog = mapper.selectBlogByIdLazy(10);
        byte[] bytes = SerializeUtil.writeObject(blog);
        Blog o = (Blog)SerializeUtil.readObject(bytes);
        List<Comment> comments = o.getComments();
        System.out.println("end");
    }

    public static class ConfigurationFactory {
        public static Configuration getConfiguration() {
            return configuration;
        }
    }
}
