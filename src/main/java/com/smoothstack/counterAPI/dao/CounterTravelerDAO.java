package com.smoothstack.counterAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smoothstack.counterAPI.entity.Traveler;

public interface CounterTravelerDAO extends JpaRepository<Traveler, Integer>{

}
