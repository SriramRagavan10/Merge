package com.phone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phone.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
