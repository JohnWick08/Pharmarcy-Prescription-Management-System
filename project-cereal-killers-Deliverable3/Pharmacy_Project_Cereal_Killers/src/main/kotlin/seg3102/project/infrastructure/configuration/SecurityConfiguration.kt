//package seg3102.project.infrastructure.configuration
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.builders.WebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//class SecurityConfiguration {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.csrf()
//            .disable()
//            .authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers("/auth/**").hasRole("USER")
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/auth/account")
//            .permitAll()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/")
//            .permitAll()
//        return http.build()
//    }
//
//    @Bean
//    fun webSecurityCustomizer(): WebSecurityCustomizer {
//        return WebSecurityCustomizer { web: WebSecurity ->
//            web.ignoring()
//                .antMatchers("/resources/**") }
//    }
//
//    @Bean
//    fun passwordEncoder(): BCryptPasswordEncoder? {
//        return BCryptPasswordEncoder()
//    }
//}