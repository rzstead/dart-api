package dart;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class DartSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication().dataSource(datasource)
				.usersByUsernameQuery("select username, password, true from user where username=?")
				.authoritiesByUsernameQuery("select user_username, roles from user_roles where user_username=?")
				.passwordEncoder(new PasswordEncoder() {

					@Override
					public boolean matches(CharSequence rawPassword, String encodedPassword) {
						return encodedPassword.equals(rawPassword);
					}

					@Override
					public String encode(CharSequence rawPassword) {
						return rawPassword.toString();
					}
				});
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
            .antMatchers("/register", "/login").anonymous()
            .anyRequest().authenticated()
            .and()
            .httpBasic();

	}
}
