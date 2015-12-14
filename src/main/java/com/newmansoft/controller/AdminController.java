package com.newmansoft.controller;

import com.newmansoft.model.AuthDao;
import com.newmansoft.model.Authorization;
import com.newmansoft.model.RSVP;
import com.newmansoft.services.AuthorizationRepository;
import com.newmansoft.services.RSVPRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by v-annew on 24-Nov-15.
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin()

public class AdminController {

    @Autowired
    private RSVPRepository rsvpRepository;


    @Autowired
    private AuthorizationRepository authorizationRepository;

    private String getAdminPassword() {
              Iterable<AuthDao> daos = authorizationRepository.findAll();

        if (daos == null ) {
            return null;
        }

        for (AuthDao dao : daos) {
            return dao.getPassword();
        }
        return null;
    }


    @RequestMapping("/guests/")
    @ResponseBody()
    public ResponseEntity<?> getAllGuests(
            @RequestHeader(value = "adminPass") String adminPassword) {
        if (!getAdminPassword().equals(adminPassword)) {
           return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rsvpRepository.findAll(), HttpStatus.OK);
    }
    @RequestMapping("/login/")
    @ResponseBody()
    public ResponseEntity<?> doLogin(
            @RequestHeader(value = "adminPass") String adminPassword) {

        if (!getAdminPassword().equals(adminPassword)) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Authorization.SUCCESS, HttpStatus.OK);
    }


}
