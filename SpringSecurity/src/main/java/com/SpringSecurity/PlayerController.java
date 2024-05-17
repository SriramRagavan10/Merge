package com.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

//	@Autowired
//	private AuthenticationManager authenticationManager;

//	@PostMapping(value = "/login")
//	public String insert(List<Player> players) {
//		playerRepository.saveAll(players);
//		return "All Details Save Successfully";
//	}

//	@PostMapping(value = "/loginIn")
//	public ResponseEntity<Void> login(@RequestBody Player player) {
//		Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(player.getName(),
//				player.getPassword());
//		Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
//		SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
//		return ResponseEntity.ok().build();
//	}

	@GetMapping(value = "/getBatter")
	@PreAuthorize("hasAuthority('ROLE_Admin')")
	public String getBats() {
		return "He is the Batter";
	}

	@GetMapping(value = "/getBowler")
	@PreAuthorize("hasAuthority('ROLE_Staff')")
	public String getBowl() {
		return "He is the Bowler";
	}

	//@PreAuthorize("hasAuthority('ROLE_Admin')")
	@GetMapping(value = "/getAll")
	public String getAll() {
		return "He is the All Rounder";
	}

}
