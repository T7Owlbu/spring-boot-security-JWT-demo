package com.example.demospringsecurity.service;

import com.example.demospringsecurity.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
public interface IAuthorityService extends IService<Authority> {

    List<Authority> getAuthorityListByUsername(String username);
}
