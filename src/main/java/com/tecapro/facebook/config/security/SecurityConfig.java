package com.tecapro.facebook.config.security;

import com.tecapro.facebook.config.CustomAccessDeniedHandler;
import com.tecapro.facebook.config.JwtAuthenticationFilter;
import com.tecapro.facebook.config.RestAuthenticationEntryPoint;
import com.tecapro.facebook.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private JwtService jwtService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {  //Cấu hình lại lỗi không có quyền truy cập
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //bean mã hóa pass người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**"); // vô hiệu hóa csrf cho 1 số đường dẫn nhất định
        http.httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint);//Tùy chỉnh lại thông báo 401 thông qua class restEntryPoint
        http.authorizeRequests()
                .antMatchers("/login",
                        "/register", "/**").permitAll() // tất cả truy cập được
                .anyRequest().authenticated()  //các request còn lại cần xác thực
                .and().csrf().disable(); // vô hiệu hóa bảo vệ của csrf (kiểm soát quyền truy cập)
        http.addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class) // lớp filter kiểm tra chuỗi jwt
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()); //xử lý ngoaoj lệ khi không có quyền truy cập
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.cors();// ngăn chăn truy cập từ miền khác
    }
}
