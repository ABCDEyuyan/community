package com.zl.community.config;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : ZL
 */
@Configuration
public class SnowflakeConfig {
    /**
     * 雪花生成器
     */
    @Bean
    public SnowflakeGenerator snowflakeGenerator() {
        return new SnowflakeGenerator(1, 0);
    }
}
