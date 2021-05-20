package com.example.demospringsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_customer_authority")
public class CustomerAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer customerId;

    private Integer authorityId;


}
