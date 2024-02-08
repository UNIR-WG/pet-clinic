package net.unir.missi.petclinic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication(proxyBeanMethods = false)
public class PetClinicApplication {

	private final static Logger logger = LoggerFactory.getLogger(PetClinicApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

}
