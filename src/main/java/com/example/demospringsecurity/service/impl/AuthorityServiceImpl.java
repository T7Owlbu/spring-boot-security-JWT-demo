package com.example.demospringsecurity.service.impl;

import com.example.demospringsecurity.entity.Authority;
import com.example.demospringsecurity.mapper.AuthorityMapper;
import com.example.demospringsecurity.service.IAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements IAuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Authority> getAuthorityListByUsername(String username) {
        List<Authority> authorityList = null;
        Object o = redisTemplate.opsForValue().get("authorities_" + username);
        if(o != null){
            authorityList = (List<Authority>) o;
        }else {
            authorityList = authorityMapper.getAuthorityListByUsername(username);
            if(authorityList == null || authorityList.size() == 0){
                throw new RuntimeException("该用户无任何权限");
            }
            redisTemplate.opsForValue().set("authorities_" + username,authorityList);
        }
        return authorityList;
    }
}
