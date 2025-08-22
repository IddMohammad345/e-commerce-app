package com.idd.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	private final GatewayApplication gatewayApplication;

	SecurityConfig(GatewayApplication gatewayApplication) {
		this.gatewayApplication = gatewayApplication;
	}

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
		serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(e -> e.pathMatchers("(/eureka/**").permitAll().anyExchange().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
		return serverHttpSecurity.build();

	}

}
