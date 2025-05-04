package com.pyip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {
    private int port;//netty监听的端口
    private String path;//websocket访问路径
}
