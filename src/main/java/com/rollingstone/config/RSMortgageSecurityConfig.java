package com.rollingstone.config;

import java.security.SecureRandom;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.rollingstone.persistence.dao.UserRepository;
import com.rollingstone.persistence.model.User;
import com.rollingstone.security.CustomAuthenticationProvider;
import com.rollingstone.security.FacebookConnectionSignup;
import com.rollingstone.security.FacebookSignInAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.rollingstone.security" })
public class RSMortgageSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	} // @formatter:on

	
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		customAuthenticationProvider.setUserDetailsService(userDetailsService);
		customAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        auth.authenticationProvider(customAuthenticationProvider);
	}

	@PostConstruct
	private void saveTestUser() {
		final User user = new User();
		user.setEmail("test2@email.com");
		user.setEnabled(true);
		user.setCreated(Calendar.getInstance());
		user.setPassword(passwordEncoder().encode("pass"));
		userRepository.save(user);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http.authorizeRequests()
				.antMatchers("/signup", "/login*", "/signin/**", "/scripts/**", "/rsmortgage-edge-service/**", "/rsmortgage-degreetype-service/**", "/views/**", "/signup/**", "/user/register", "/signin/register",
						"/registrationConfirm*", "/badUser*", "/forgotPassword*", "/user/resetPassword*",
						"/user/changePassword*", "/user/savePassword*", "/js/**")
				.permitAll().anyRequest().authenticated()

				.and().formLogin().loginPage("/login").permitAll()
				// loginProcessingUrl("/login")

				.and().rememberMe().key("lssAppKey").tokenValiditySeconds(604800) // One week is 604800 

				.and().logout()

				.and().csrf().disable();
	} // @formatter:on

	@Bean
	// @Primary
	public ProviderSignInController providerSignInController() {
		((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository,
				new FacebookSignInAdapter());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
}