package com.kaito.game.Security;

import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Utils.Constants;
import com.kaito.game.Utils.StatusEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
import java.util.Optional;

/**
 * 自动登录实现：就是直接在浏览器上存储用户名和帐号token，然后每次执行操作的时候发送请求进行过滤，验证token即可。
 * 没有必要每次跳转页面等都要通过token来获取用户名
 */
@Slf4j
public class SecurityUserFilter extends OncePerRequestFilter {

    @Qualifier("tokenUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityTokenUtil securityTokenUtil;

    @Value(Constants.TOKENHEADER)
    private String TOKENHEADER;
    @Value(Constants.USERNAME)
    private String USERNAME;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKENHEADER);
        logger.info("正在执行权限检查");
        if (token !=null){
            Optional<String> userName = securityTokenUtil.getUserNameByToken(token);
            logger.info("the userName is "+userName.get());
            if (userName.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null){
                // 如果有这个帐号的话，就赋予相应的权限
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName.get());

                if (securityTokenUtil.validateToken(userName.get(),token)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + userName + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
