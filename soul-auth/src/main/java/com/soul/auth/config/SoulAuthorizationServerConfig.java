package com.soul.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import sun.jvmstat.perfdata.monitor.PerfStringConstantMonitor;

/**
 * 授权/认证服务器配置
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/21 22:58
 **/
@Slf4j
@Configuration
@EnableAuthorizationServer
public class SoulAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * redis工厂，默认使用lettue
     */
    @Autowired
    public RedisConnectionFactory redisConnectionFactory;

    /**
     * 用户认证管理器
     */
    @Autowired
    public AuthenticationManager authenticationManager;

    /**
     * 用户服务
     */
    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * 密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置认证管理器
     * @author wangdong
     * @date 2020/7/22 12:36 下午
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //配置认证管理器
        endpoints.authenticationManager(authenticationManager)
                //配置用户服务
                .userDetailsService(userDetailsService)
                //配置token存储的服务与位置
                .tokenServices(tokenService())
                .tokenStore(tokenStore());
    }

    /**
     * 客户端信息配置，可配置多个客户端，这里可以使用配置文件进行代替
     * @author wangdong
     * @date 2020/7/22 12:36 下午
     * @return void
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("admin"))
                .authorizedGrantTypes("password", "refresh_token","authorization_code")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(864000)
                .scopes("all")
                .and()
                .withClient("soul")
                .secret(passwordEncoder.encode("soul"))
                .authorizedGrantTypes("password", "refresh_token","authorization_code")
                .scopes("all")
                .redirectUris("http://localhost:18090/oauth2/getCode");
    }

    /**
     * 授权服务安全配置，主要用于放行客户端访问授权服务接口
     * @author wangdong
     * @date 2020/7/22 12:39 下午
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 设置 redis 存储 token
     * @author wangdong
     * @date 2020/7/22 12:33 下午
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     **/
    @Bean
    public TokenStore tokenStore() {
        // 使用redis存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 设置redis token存储中的前缀
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }

    /**
     * 扩展 token 规则
     * @author wangdong
     * @date 2020/7/22 12:33 下午
     * @return org.springframework.security.oauth2.provider.token.DefaultTokenServices
     **/
    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 配置token存储
        tokenServices.setTokenStore(tokenStore());
        // 开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        // 复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        // token 有效期，设置12小时
        tokenServices.setAccessTokenValiditySeconds(12 * 60 * 60);
        // refresh_token有效期，设置一周
        tokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return tokenServices;
    }

}
