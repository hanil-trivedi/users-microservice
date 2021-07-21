package com.hanil.demo.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/hello-world", method = RequestMethod.GET)
	public String helloWord() {
		return "Hello World !";
	}

	@RequestMapping(value = "/hello-world-bean", method = RequestMethod.GET)
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@RequestMapping(value = "/hello-world/path-variable/{name}", method = RequestMethod.GET)
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello ,%s !!!", name));
	}

	@RequestMapping(value = "/hello-world-internationalized", method = RequestMethod.GET)
	public String helloWordInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, "Default Message"
				// locale); instead of this , use LocaleContextHolder
				,LocaleContextHolder.getLocale());
				
	}
}
