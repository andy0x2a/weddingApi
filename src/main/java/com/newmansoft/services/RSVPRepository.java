package com.newmansoft.services;

import com.newmansoft.model.RSVP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RSVPRepository extends CrudRepository<RSVP, Integer> {


//    Page<RSVP> findAll(Pageable pageable);
          List<RSVP> findByWedding(String wedding);


}
