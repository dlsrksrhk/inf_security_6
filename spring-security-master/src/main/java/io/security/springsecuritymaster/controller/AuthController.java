package io.security.springsecuritymaster.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping(value="/anonymous/a")
	public String anonymousA() {
		return "/anonymousA";
	}

	@GetMapping(value="/anonymous/b")
	public String anonymousB() {
		return "/anonymousB";
	}

	@GetMapping(value="/anonymousContext")
	public String anonymousContext(@CurrentSecurityContext SecurityContext securityContext) {
		return securityContext.getAuthentication().getName();
	}

	@GetMapping(value="/authenticationCheck")
	public String authenticationCheck(Authentication authentication) {
		if (authentication == null) {
			return "anonymous";
		}

		return "some user";
	}
}