package com.sustly.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * @Author: liyue
 * @Date: 19-9-12 下午4:27
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {
    private static ArrayList<HttpHost> hostList;

    @Value(value = "${elasticsearch.port}")
    private Integer port;
    @Value(value = "${elasticsearch.ip}")
    private String hosts;
    /** 连接超时时间 */
    @Value("${elasticsearch.connectTimeOut}")
    private static int connectTimeOut;
    /** 连接超时时间 */
    @Value("${elasticsearch.socketTimeOut}")
    private static int socketTimeOut;
    /** 获取连接的超时时间 */
    @Value("${elasticsearch.connectionRequestTimeOut}")
    private int connectionRequestTimeOut;
    /** 最大连接数 */
    @Value("${elasticsearch.maxConnectNum}")
    private int maxConnectNum;
    /** 最大路由连接数 */
    @Value("${elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;

    @PostConstruct
    public void init() {
        hostList = new ArrayList<>();
        String[] hostStrs = hosts.split(",");
        for (String host : hostStrs) {
            // 使用的协议
            String schema = "http";
            hostList.add(new HttpHost(host, port, schema));
        }
    }

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }


}
