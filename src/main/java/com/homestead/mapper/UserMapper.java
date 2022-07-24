package com.homestead.mapper;

import com.homestead.entity.Blog;
import com.homestead.entity.Comment;
import com.homestead.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@CacheNamespace
public interface UserMapper {

    @Select({" select * from users where id=#{1}"})
    //@Options(flushCache = Options.FlushCachePolicy.TRUE)
    User selectById(Integer id);

    @Select({" select * from users where id=#{id} and name=#{name}"})
    User selectByIdAndName(@Param("id") Integer id, @Param("name") String name);

    @Select({" select * from users where id=#{1}"})
    User selectByid3(Integer id);

    @Select({" select * from users where name='${name}'"})
    @Options(statementType = StatementType.PREPARED)
    List<User> selectByName(User user);

    List<User> selectByUser(User user);

    @Insert("INSERT INTO `users`( `name`, `age`, `sex`, `email`, `phone_number`) VALUES ( #{name}, #{age}, #{sex}, #{email}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addUser(User user);

    int editUser(User user);

    @Update("update  users set name=#{arg1} where id=#{arg0}")
    int setName(Integer id, String name);

    @Delete("delete from users where id=#{id}")
    int deleteUser(Integer id);

    Blog selectBlogByIdCollection(Integer id);

    Blog selectBlogByIdAssociation(Integer id);

    User selectUserByUserId(Integer id);
    Comment selectCommentByBlogId(Integer id);

    Blog selectBlogByIdLazy(Integer id);
    Comment selectCommentsByBlog(Integer id);

    Blog selectByBlogIdCollection(Integer id);

    Blog selectByBlogIdCollectionCircu(Integer id);
}
