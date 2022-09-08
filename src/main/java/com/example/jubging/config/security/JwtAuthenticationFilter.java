package com.example.jubging.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


// Jwt가 유효한 토큰인지 인증을 위한 filter
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    // Jwt provider 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 Jwt Token의 유효성을 검증 (jwtToken provider, validateToken)하는
    // filter를 filterChain에 등록한다.
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        // request에서 token을 취한다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 검증
        log.info("[Verifing token]");
        log.info(((HttpServletRequest) request).getRequestURL().toString());

        if (token != null && jwtTokenProvider.validateToken(token) == true) {
            log.info("[토큰 유효성 확인]");

            // validateToken : Jwt 토큰의 유효성 + 만료일자 확인
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            log.info("[토큰 유효성 + 만료일자 확인]");

            // getAuthentication : Jwt 토큰으로 인증 정보 조회
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("[토큰으로 인증 정보 조회]");
        }

        filterChain.doFilter(request, response);
        log.info("[Do filter]");
    }
}
