package pe.gob.serfor.osutd.sgd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import pe.gob.serfor.osutd.sgd.filter.CsrfCookieGeneratorFilter;
import pe.gob.serfor.osutd.sgd.jwt.JwtAuthenticationEntryPoint;
import pe.gob.serfor.osutd.sgd.jwt.JwtRequestFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
	public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
	public static final String AUTHENTICATE_ENTRY_POINT = "/auth";
	public static final String AUTHORIZATION_USER_ENTRY = "/user/authorization";
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//            .antMatchers(HttpMethod.OPTIONS, "/**")
//            .antMatchers("/app/**/*.{js,html,jsp,png,jpg}")
//            .antMatchers("/i18n/**")
//            .antMatchers("/content/**")
//            .antMatchers("/test/**")
//            .antMatchers("/h2-console/**");
//    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .headers()
        .frameOptions()
        .disable()
        .and()
        	.addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
        .exceptionHandling()
        	.authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            	.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            	.antMatchers(AUTHENTICATE_ENTRY_POINT).permitAll()
                .antMatchers(AUTHORIZATION_USER_ENTRY).permitAll() ;// Sirve para que el usuario active su cuenta de registro
         // .and()
           //     .authorizeRequests()
              //      .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated(); // Protected API End-points
        
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		// We don't need CSRF for this example
//		http.csrf().disable()
//						// dont authenticate this particular request
//						.authorizeRequests().antMatchers("/authenticate").permitAll().
//						// all other requests need to be authenticated
//						anyRequest().authenticated().and().
//						// make sure we use stateless session; session won't be used to
//						// store user's state.
//						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		// Add a filter to validate the tokens with every request
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
