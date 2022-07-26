package com.moxa.dream.boot.mapper;

import com.moxa.dream.boot.table.Blog;
import com.moxa.dream.system.annotation.Mapper;
import com.moxa.dream.system.annotation.Sql;

import java.util.List;

@Mapper
public interface BlogMapper{
    @Sql("select @all() from blog where user_id=@$(userId)")
    List<Blog> selectBlogByUserId(Integer userId);
}
