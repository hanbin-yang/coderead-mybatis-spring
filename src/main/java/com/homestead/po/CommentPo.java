package com.homestead.po;

/**
 * @author HanBin_Yang
 * @since 2022/7/24 10:28
 */
public class CommentPo {
    private Integer id;

    private Integer blogId;

    private String body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
