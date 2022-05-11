package com.soccerfantasy.app.util;

import org.springframework.stereotype.Component;

@Component
public final class AppConstants {

	public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
	public static final String HEADER_NAME = "Authorization";
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String LOG_IN_URL = "/users/login";
	public static final String H2_CONSOLE = "/h2-console/**";

}
