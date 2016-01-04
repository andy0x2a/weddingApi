package com.newmansoft.services;

import com.newmansoft.model.AuthDao;
import com.newmansoft.model.Authorization;
import com.newmansoft.model.Family;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorizationRepository extends CrudRepository<AuthDao, Integer> {


                  List<AuthDao> findByWedding(String wedding);


}
