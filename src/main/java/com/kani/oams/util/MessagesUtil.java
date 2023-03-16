package com.kani.oams.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MessagesUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String key, String[] params, Locale locale) {
		return messageSource.getMessage(key, params, locale);
	}

}
