package com.example.demospringsecurity.mapper;

import com.example.demospringsecurity.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
public interface AuthorityMapper extends BaseMapper<Authority> {

    List<Authority> getAuthorityListByUsername(String username);

}
