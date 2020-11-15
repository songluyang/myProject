package taco.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dateSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Bean
//	public PasswordEncoder encoder{
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public PasswordEncoder encoder() {
//		return new StandardPasswordEncoder("53cr3t");
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userDetailsService).passwordEncoder(encoder());//数据库验证
		

//		.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("buzz")
//		.password("$2a$10$YuqPbIALlT6P65GMLz.L2OJvI5BoywtVL4yGt/aft5BOxLe3HCL1m").authorities("ROLE_USER").and()
//		.withUser("woody").password("$2a$10$uGo4ERBHYmaCjIPGISW6W.fE5BowC1fx1fqaDufF/0GMoI8RoaM7m")
//		.authorities("ROLE_USER");
//		// buzz  : infinity $2a$10$YuqPbIALlT6P65GMLz.L2OJvI5BoywtVL4yGt/aft5BOxLe3HCL1m
// 		// woody : bullseye $2a$10$uGo4ERBHYmaCjIPGISW6W.fE5BowC1fx1fqaDufF/0GMoI8RoaM7m
		
		
	}
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()                     
//                .antMatchers("/test").authenticated()
//                //.antMatchers("/test","/tests").authenticated()
//                .antMatchers(HttpMethod.POST,"/tests").authenticated()
//                .anyRequest().permitAll();
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		                        .antMatchers("/design","/orders").hasRole("USER")
//		                        .antMatchers(HttpMethod.POST,"/design").permitAll()
		                        .antMatchers("/","/**").permitAll()
		                        .and().formLogin().loginPage("/login")//自定义登录页面
		                        .defaultSuccessUrl("/design",true)//登陆成功默认页面
		                        .and().logout().logoutSuccessUrl("/")//退出页面
//		                        .and().csrf().disable()//禁用csrf验证
		                        ;
		
//		http.authorizeRequests().antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
//		.antMatchers("/", "/**").access("permitAll")
//		.and().formLogin().loginPage("/login")//自定义登录页面
//        .defaultSuccessUrl("/design",true)//登陆成功默认页面
//        .and().logout().logoutSuccessUrl("/")//退出页面
//        ;
	}
	

	// 基于内存的用户存储
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("buzz")
//				.password("$2a$10$YuqPbIALlT6P65GMLz.L2OJvI5BoywtVL4yGt/aft5BOxLe3HCL1m").authorities("ROLE_USER").and()
//				.withUser("woody").password("$2a$10$uGo4ERBHYmaCjIPGISW6W.fE5BowC1fx1fqaDufF/0GMoI8RoaM7m")
//				.authorities("ROLE_USER");
//		// buzz : infinity $2a$10$YuqPbIALlT6P65GMLz.L2OJvI5BoywtVL4yGt/aft5BOxLe3HCL1m
//		// woody : bullseye $2a$10$uGo4ERBHYmaCjIPGISW6W.fE5BowC1fx1fqaDufF/0GMoI8RoaM7m
//	}
	

	// 基于JDBC的用户存储
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dateSource);
//	}

	// 重写默认的用户查询功能
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dateSource)
//				.usersByUsernameQuery("select username,password,enabled from Users where username=?")
//				.authoritiesByUsernameQuery("select username,authority from UserAuthorities where username=?")
//				.passwordEncoder(new BCryptPasswordEncoder());
//	}
	
	//LDAP
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})").
//		groupSearchBase("ou=groups").groupSearchFilter("member={0}")
//		.passwordCompare()//密码比对
//		.passwordEncoder(new BCryptPasswordEncoder())
//		.passwordAttribute("passcode")
//		;
//	}
	
	//引用远程的LDAP服务器
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})")
//		.groupSearchBase("ou=groups").groupSearchFilter("member={0}")
////		.passwordCompare()
////		.passwordEncoder(new BCryptPasswordEncoder()).
////		.passwordAttribute("passcode")
//		.contextSource().url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
//	}
	
	
	//引用远程的LDAP服务器
//	    @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.ldapAuthentication()
//	                .userSearchBase("ou=people")
//	                .userSearchFilter("(uid={0})")
//	                .groupSearchBase("ou=groups")
//	                .groupSearchFilter("member={0}")
//	                // 返回一个ContextSourceBuilder 对象
//	                .contextSource()
//	                // 指定远程 LDAP 服务器 的 地址
//	                .url("ldap://xxx.com:389/dc=xxx,dc=com");
//	                
//	    }
	

	//配置嵌入式的LDAP服务器
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userSearchBase("ou= people").userSearchFilter("(uid={0})")
//				.groupSearchBase("ou=groups").groupSearchFilter("member={0}")
////				.passwordCompare().passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("passcode")
//				.contextSource().root("dc=tacocloud,dc=com");
//	}	
	
	//LDIF
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})")
//				.groupSearchBase("ou= groups").groupSearchFilter("member={0}")
////				.passwordCompare().passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("passcode")
//				.contextSource().root("dc=tacocloud,dc=com")
//				.ldif("classpath:users.ldif");
//	}	
	
}
