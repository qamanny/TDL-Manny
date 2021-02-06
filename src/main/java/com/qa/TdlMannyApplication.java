package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TdlMannyApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(TdlMannyApplication.class, args);
		System.out.println(beanBag.getBean("HelloWorld", String.class));
	}

}
