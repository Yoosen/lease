package com.uu.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.uu.lease.web.*.mapper")    // p87
public class MybatisPlusConfiguration {

}