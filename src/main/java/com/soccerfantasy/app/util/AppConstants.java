package com.soccerfantasy.app.util;

import org.springframework.stereotype.Component;

@Component
public final class AppConstants {

	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String LOG_IN_URL = "/users/login";
	public static final String H2_CONSOLE = "/h2-console/**";

	public static final int NUM_ATTACKER = 5;
	public static final int NUM_MIDFIELDER = 6;
	public static final int NUM_DEFENDER = 6;
	public static final int NUM_GOALKEEPER = 3;

}
