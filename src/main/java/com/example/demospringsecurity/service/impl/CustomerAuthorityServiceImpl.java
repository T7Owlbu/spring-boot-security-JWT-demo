package com.example.demospringsecurity.service.impl;

import com.example.demospringsecurity.entity.CustomerAuthority;
import com.example.demospringsecurity.mapper.CustomerAuthorityMapper;
import com.example.demospringsecurity.service.ICustomerAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
@Service
public class CustomerAuthorityServiceImpl extends ServiceImpl<CustomerAuthorityMapper, CustomerAuthority> implements ICustomerAuthorityService {

}
