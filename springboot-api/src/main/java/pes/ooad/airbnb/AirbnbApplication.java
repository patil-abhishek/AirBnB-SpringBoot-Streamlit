package pes.ooad.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirbnbApplication implements WebMvcConfigurer{

	public void addViewController(ViewControllerRegistry registry)
	{
		registry.addViewController("/index").setViewName("index");
	}

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}

}
