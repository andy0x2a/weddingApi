package com.newmansoft.services;

import com.newmansoft.model.Family;
import com.newmansoft.model.RSVP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FamilyRepository extends CrudRepository<Family, Integer> {

    List<Family> findByWedding(String wedding);



}
