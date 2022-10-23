package com.xct.bootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xct.bootdemo.bean.CartoonNewChapter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewChapterMapper extends BaseMapper<CartoonNewChapter> {
    @Select("select * from test.cartoon_new_chapter where cid = #{cid}")
    List<CartoonNewChapter> selectByCid(@Param("cid") Integer cid);
}