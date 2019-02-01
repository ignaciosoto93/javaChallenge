package com.marb.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class ResourcesServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
				.authorizeRequests()
				.antMatchers("/auth/**").authenticated()
				.and()
				.authorizeRequests()
				//.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('morfeo-api/write')")
				//.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('morfeo-api/write')")
				//.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('morfeo-api/write')")
				//.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('morfeo-api/write')")
				.anyRequest().permitAll();
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
			OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}
}
