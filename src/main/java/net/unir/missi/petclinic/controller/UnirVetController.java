package net.unir.missi.petclinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.unir.missi.petclinic.model.Vet;
import net.unir.missi.petclinic.repository.VetRepository;
import net.unir.missi.petclinic.service.UnirNumberService;
import net.unir.missi.petclinic.service.UnirVetService;

@RestController
public class UnirVetController {

	private final static Logger logger = LoggerFactory.getLogger(UnirVetController.class);

	@Autowired
	VetRepository vetRepository;

	@Autowired
	UnirVetService vetService;

	@Autowired
	UnirNumberService primeService;

	public UnirVetController() {
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@PostMapping("/unir/vets/new")
	public String addNewVetDirect(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		try {
			logger.info("addNewVetDirect firstName " + firstName + " lastName " + lastName);
			Vet myVet = new Vet();
			myVet.setFirstName(firstName);
			myVet.setLastName(lastName);
			vetRepository.save(myVet);
			return myVet.getId().toString();
		}
		catch (Exception ex) {
			logger.error("addNewVet ex " + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR", ex);
		}
	}

	@PostMapping("/unir/vets/new-service")
	public Vet addNewVetService(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		try {
			logger.info("addNewVetService firstName " + firstName + " lastName " + lastName);
			return vetService.addNewVet(firstName, lastName);
		}
		catch (Exception ex) {
			logger.error("addNewVet ex " + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR", ex);
		}
	}

	@PostMapping("/unir/vets/new-primeEmulator")
	public boolean addNewVetPrimeEmulator(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		try {
			logger.info("addNewVetPrimeEmulator firstName " + firstName + " lastName " + lastName);
			String fName = firstName.replaceAll("\\D", "");
			String fName2 = fName;
			if (fName.length() > 5)
				fName2 = fName.substring(0, 5);
			vetService.addNewVet(firstName, lastName);
			return primeService.isPrime(Long.parseLong(fName2));
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error("addNewVet ex " + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR", ex);
		}
	}

	@PostMapping("/unir/vets/new-factorialEmulator")
	public long addNewVetFactorialEmulator(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		try {
			logger.info("addNewVetFactorialEmulator firstName " + firstName + " lastName " + lastName);
			String fName = firstName.replaceAll("\\D", "");
			String fName2 = fName;
			if (fName.length() > 5)
				fName2 = fName.substring(0, 5);
			vetService.addNewVet(firstName, lastName);
			return primeService.factorialUsingRecursion(Long.parseLong(fName2));
		}
		catch (Exception ex) {
			ex.printStackTrace();
			logger.error("addNewVet ex " + ex.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR", ex);
		}
	}

}
