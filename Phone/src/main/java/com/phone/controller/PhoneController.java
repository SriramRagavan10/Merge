package com.phone.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.security.auth.x500.X500Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.phone.entity.Phone;
import com.phone.exception.BrandException;
import com.phone.exception.RatingException;
import com.phone.repository.PhoneRepository;

//@CrossOrigin(origins = "*")
@RestController
public class PhoneController {

	@Autowired
	PhoneRepository phoneRepository;

	@PostMapping(value = "/insert")
	public String insert(@RequestBody Phone phone) {
		phoneRepository.save(phone);
		return "Successfully Saved";
	}

	@PostMapping(value = "/insertAll")
	public String insertAll(@RequestBody List<Phone> phone) {
		phoneRepository.saveAll(phone);
		return "Successfully Saved";
	}

	@PostMapping(value = "/price")
	public String insertByPrice(@RequestBody Phone phone) {
		if (phone.getPrice() > 70000) {
			phoneRepository.save(phone);
		} else {
			return "The Price is Not 70000";
		}
		return "Successfully Saved";
	}

	@PostMapping(value = "/rating")
	public String insertByRating(@RequestBody Phone phone) throws RatingException {
		if (phone.getRating() < 8) {
			throw new RatingException("The Rating is Below 8");
		} else {
			phoneRepository.save(phone);
		}
		return "Successfully Saved";
	}

	@PostMapping(value = "/brand")
	public String insertByBrand(@RequestBody Phone phone) throws BrandException {
		if (phone.getBrand().startsWith("S")) {
			phoneRepository.save(phone);
		} else {
			throw new BrandException("The Brand Name is not Starts With S");
		}
		return "Successfully Saved";
	}

	@PostMapping(value = "/priceList")
	public String insertByPriceList(@RequestBody List<Phone> phone) {
		phoneRepository.saveAll(phone.stream().filter(x -> x.getPrice() > 70000).toList());
		return "Successfully Saved";
	}

	@PostMapping(value = "/ratingList")
	public String rating(@RequestBody List<Phone> phone) throws RatingException {
		for (Phone x : phone) {
			if (x.getRating() < 8) {
				throw new RatingException("The Rating is Below 8");
			} else {
				phoneRepository.save(x);
			}
		}
		return "Successfully Saved";
	}

	@PostMapping(value = "/brandList")
	public String insertByBrandList(@RequestBody List<Phone> phone) throws BrandException {
		for (Phone x : phone) {
			if (x.getBrand().startsWith("S")) {
				phoneRepository.save(x);
			} else {
				throw new BrandException("The Brand Name is not Starts With S");
			}
		}
		return "Successfully Saved";
	}

	@PostMapping(value = "/palind")
	public String colorPal(@RequestBody Phone phone) {
		Phone p = phone;
		String a = "";
		for (int i = 0; i < p.getColor().length(); i++) {
			a = p.getColor().charAt(i) + a;
		}
		if (a.equalsIgnoreCase(p.getColor())) {
			return "This is Palindrome";
		} else {
			phoneRepository.save(p);
			return "Saved Successfully";
		}
	}

	@GetMapping(value = "/getAll")
	public List<Phone> getAll() {
		return phoneRepository.findAll();
	}

	@GetMapping(value = "/middle")
	public List<String> midLetter() {
		List<String> c = new ArrayList<>();
		phoneRepository.findAll().forEach(x -> {
			if (x.getBrand().length() % 2 == 0) {
				c.add(x.getBrand().substring(x.getBrand().length() / 2 - 1, x.getBrand().length() / 2 + 1));
			} else {
				c.add(x.getBrand().charAt(x.getBrand().length() / 2) + "");
			}
		});
		return c;
	}

	@GetMapping(value = "/color")
	public List<Phone> color() {
		List<Phone> ph = phoneRepository.findAll().stream().filter(x -> x.getColor().equalsIgnoreCase("black"))
				.map(x -> x).collect(Collectors.toList());
		return ph;
	}

	@GetMapping(value = "/lasttwo")
	public List<Phone> two() {
		return phoneRepository.findAll().stream().sorted(Comparator.comparing(Phone::getId).reversed()).limit(2)
				.toList();
	}

	@GetMapping(value = "/desc")
	public List<Phone> des() {
		return phoneRepository.findAll().stream().sorted(Comparator.comparing(Phone::getPrice).reversed()).toList();
	}

	@GetMapping(value = "/duplicate")
	public List<Phone> dup() {
		List<Float> ph = phoneRepository.findAll().stream().map(x -> x.getRating()).toList();
		return phoneRepository.findAll().stream().filter(x -> Collections.frequency(ph, x.getRating()) == 1).toList();
	}

	@PutMapping(value = "/update")
	public String update(@RequestBody Phone phone) {
		phoneRepository.save(phone);
		return "Successfully Updated";
	}

	@PatchMapping(value = "/patch")
	public String patch(@RequestBody Phone phone) {
		List<Integer> p = phoneRepository.findAll().stream().filter(x -> x.getBrand().endsWith("g")).map(x -> x.getId())
				.toList();
		p.forEach(x -> {
			Phone ph = phoneRepository.findById(x).orElse(null);
			if (ph != null) {
				ph.setColor(phone.getColor());
				phoneRepository.save(ph);
			}
		});
		return "UPDATED SUCCESSFULLY";
	}

	@PatchMapping(value = "/patchPrice")
	public String patchPrice() {
		List<Phone> ph = phoneRepository.findAll().stream().filter(x -> x.getRating() > 7.0f).map(x -> {
			x.setPrice(x.getPrice() + (x.getPrice() * (10 / 100)));
			return x;
		}).toList();
		phoneRepository.saveAll(ph);
		return "Update Successfully";
	}

	@DeleteMapping(value = "/delete")
	public String del() {
		List<Integer> de = phoneRepository.findAll().stream().filter(x -> x.getRating() < 5.0f).map(x -> x.getId())
				.toList();
		de.forEach(x -> phoneRepository.deleteById(x));
		return "DELETED SUCCESSFULLY";
	}
}
