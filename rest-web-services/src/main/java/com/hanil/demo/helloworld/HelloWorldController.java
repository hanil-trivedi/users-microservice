package com.hanil.demo.helloworld;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(value="/hello-world" , method = RequestMethod.GET  )
	public String helloWord() {
		return "Hello World !";
	}
	
	@RequestMapping(value="/hello-world-bean" , method=RequestMethod.GET )
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@RequestMapping(value="/hello-world/path-variable/{name}" , method=RequestMethod.GET )
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello ,%s !!!", name));
	}
}
