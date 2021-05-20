package com.example.demospringsecurity.service;

import com.example.demospringsecurity.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
public interface ICustomerService extends IService<Customer> {

    Customer getCustomerByUsername(String username);

}
