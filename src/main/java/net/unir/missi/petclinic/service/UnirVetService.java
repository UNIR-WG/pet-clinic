package net.unir.missi.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unir.missi.petclinic.model.Vet;
import net.unir.missi.petclinic.repository.VetRepository;

@Service
public class UnirVetService {

	@Autowired
	VetRepository vetRepository;

	public Vet addNewVet(String firstName, String lastName) {
		try {
			Vet myVet = new Vet();
			myVet.setFirstName(firstName);
			myVet.setLastName(lastName);
			vetRepository.save(myVet);
			return myVet;
		}
		catch (Exception ex) {
			return null;
		}
	}

}
