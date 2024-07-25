package com.nearby.location;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NearByLocationApplication {

	public static void main(String[] args) {

		PropertyConfigurator
				.configure(NearByLocationApplication.class.getClassLoader().getResource("log4j.properties"));

		SpringApplication.run(NearByLocationApplication.class, args);
		System.out.println("Near by Location");
	}

}
