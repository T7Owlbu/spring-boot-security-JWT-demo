package com.example.demospringsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.example.demospringsecurity.entity.Customer;
import com.example.demospringsecurity.mapper.CustomerMapper;
import com.example.demospringsecurity.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        Object o = redisTemplate.opsForValue().get("customer_" + username);
        if(o != null){
            customer = (Customer) o;
        }else{
            customer = customerMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getUsername,username));
            if(customer == null){
                throw new RuntimeException("用户名不存在");
            }
//            customer = customerMapper.getByUsername(username);
            redisTemplate.opsForValue().set("customer_"+username,customer);
        }
        return customer;
    }
}
