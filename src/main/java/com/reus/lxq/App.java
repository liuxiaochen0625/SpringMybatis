package com.reus.lxq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/**
 * Hello world!
 *
 */

@Controller
@SpringBootApplication
public class App
{
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
