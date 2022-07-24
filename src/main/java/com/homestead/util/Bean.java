package com.homestead.util;

import com.homestead.entity.User;

import java.io.Serializable;

/**
 * @author HanBin_Yang
 * @since 2022/7/24 11:39
 */
public class Bean implements Serializable {

    public int id;

    public String name;

    protected final Object writeReplace() {
        /*User user = new User();
        user.setId(1112);
        user.setAge("dfs");
        return user;*/
        Bean bean = new Bean();
        bean.name = "yyy";
        bean.id = 12333;
        return bean;
    }

    protected final Object readResolve() {
        System.out.println(this);
        this.name = "read hahah";
        return this;
    }
}
