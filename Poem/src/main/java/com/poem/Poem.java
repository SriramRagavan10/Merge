package com.poem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Poem {

	@GetMapping(value = "/mypoem")
	public String get() {
		return "poem.html";
	}

}
