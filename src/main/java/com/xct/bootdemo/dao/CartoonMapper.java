package com.xct.bootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xct.bootdemo.bean.Cartoon;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoonMapper extends BaseMapper<Cartoon> {
}
