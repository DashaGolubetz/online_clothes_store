package com.github.DashaGolubetz.online_clothes_store.configs;

import com.github.DashaGolubetz.online_clothes_store.services.UserDetailsService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс конфигурации Spring Security, который необходим для аутентификации и авторизации пользователей в приложении.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Поле, содержащее объект класса {@link UserDetailsService}.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     * @param userDetailsService
     */
    @Contract(pure = true)
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Бин, используемый для определения цепочки фильтров безопасности, которые обрабатывают запросы веб-приложения.
     *
     * @param httpSecurity позволяет настраивать веб-безопасность для определенных http-запросов; по умолчанию он будет применен ко всем запросам, но может быть ограничен с помощью #requestMatcher(RequestMatcher) или других подобных методов.
     * @return {@link SecurityFilterChain}, который представляет собой цепочку фильтров, определенных в конфигурации безопасности приложения; каждый фильтр выполняет определенную операцию безопасности, и они вызываются последовательно в порядке, определенном этой цепочкой.
     * @throws Exception проблемы при настройке безопасности, например, неправильное использование методов, некорректная конфигурация или другие проблемы, связанные с безопасностью приложения.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers("/login", "/register").anonymous();
            authorizationManagerRequestMatcherRegistry.requestMatchers("/products/add").hasRole("ADMIN");
            authorizationManagerRequestMatcherRegistry.requestMatchers("/cart").authenticated();
            authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
        }).formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login");
            httpSecurityFormLoginConfigurer.loginProcessingUrl("/login");
            httpSecurityFormLoginConfigurer.failureUrl("/login");
            httpSecurityFormLoginConfigurer.defaultSuccessUrl("/", true);
        }).logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer.logoutUrl("/logout");
            httpSecurityLogoutConfigurer.logoutSuccessUrl("/");
        }).userDetailsService(userDetailsService).httpBasic(Customizer.withDefaults()).build();
    }

    /**
     * Бин, используемый для подгрузки кодировщика паролей фабричным методом.
     *
     * @return {@link BCryptPasswordEncoder} (конфигурация для кодирования паролей).
     */
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
