package com.SpringSecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

//	@Query(value = "select * from players where type = ?", nativeQuery = true)
//	public String getBats(String bat);
//	
//	@Query(value = "select * from players where type = ?", nativeQuery = true)
//	public String getBats(String bowl);
//	
//	@Query(value = "select * from players where type = ?", nativeQuery = true)
//	public String getBats(String all);

}
