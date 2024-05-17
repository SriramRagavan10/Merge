package com.kavi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Config {

	@GetMapping(value = "/get")
	public String get() {
		return "Kavitha";
	}

}
