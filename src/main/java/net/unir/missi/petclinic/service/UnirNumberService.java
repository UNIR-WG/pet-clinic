package net.unir.missi.petclinic.service;

import org.springframework.stereotype.Service;

@Service
public class UnirNumberService {

	public boolean isPrime(long numero) {
		if (numero == 0 || numero == 1 || numero == 4) {
			return false;
		}
		for (int x = 2; x < numero / 2; x++) {
			if (numero % x == 0)
				return false;
		}
		return true;
	}

	public long factorialUsingRecursion(long n) {
		if (n <= 2) {
			return n;
		}
		return n * factorialUsingRecursion(n - 1);
	}

}
