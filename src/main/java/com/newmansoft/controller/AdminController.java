package com.newmansoft.controller;

import com.newmansoft.model.*;
import com.newmansoft.services.AuthorizationRepository;
import com.newmansoft.services.FamilyRepository;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by v-annew on 24-Nov-15.
 */

@RestController
@RequestMapping("/admin/{wedding}")
@CrossOrigin()

public class AdminController {

    @Autowired
    private RSVPRepository rsvpRepository;

    @Autowired
    private FamilyRepository familyRepository;


    @Autowired
    private AuthorizationRepository authorizationRepository;

    private String getAdminPassword(String wedding) {
              Iterable<AuthDao> daos = authorizationRepository.findByWedding(wedding);

        if (daos == null ) {
            return null;
        }

        for (AuthDao dao : daos) {
            return dao.getPassword();
        }
        return null;
    }

    @RequestMapping(value="/family/", method = RequestMethod.POST )
    public ResponseEntity<?> addFamily(
            @PathVariable String wedding,
            @RequestBody Family family,
            @RequestHeader(value = "adminPass") String adminPassword) {
        if (!getAdminPassword(wedding).equals(adminPassword)) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }

        family.setWedding(wedding);
        Family save = familyRepository.save(family);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @RequestMapping(value="/guests/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<?> deleteGuest(
            @PathVariable String wedding,
            @PathVariable int id,
            @RequestHeader(value = "adminPass") String adminPassword) {
        if (!getAdminPassword(wedding).equals(adminPassword)) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }
        RSVP guestToDelete = rsvpRepository.findOne(id);
        if (guestToDelete == null) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.NOT_FOUND);
        }
        if (!guestToDelete.getWedding().equals(wedding)) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }

        List<Family> allFamilies =familyRepository.findByWedding(wedding);
        if (allFamilies != null) {
            allFamilies.stream().filter(family -> family.getMembers().contains(guestToDelete)).forEach(family -> {
                family.getMembers().remove(guestToDelete);
                familyRepository.save(family);
            });
        }

        rsvpRepository.delete(id) ;
        return new ResponseEntity<>(Authorization.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping("/guests/")
    @ResponseBody()
    public ResponseEntity<?> getAllGuests(
            @PathVariable String wedding,
            @RequestHeader(value = "adminPass") String adminPassword) {
        if (!getAdminPassword(wedding).equals(adminPassword)) {
           return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rsvpRepository.findByWedding(wedding), HttpStatus.OK);
    }
    @RequestMapping("/login/")
    @ResponseBody()
    public ResponseEntity<?> doLogin(
            @PathVariable String wedding,
            @RequestHeader(value = "adminPass") String adminPassword) {

        if (!getAdminPassword(wedding).equals(adminPassword)) {
            return new ResponseEntity<>(Authorization.FAIL, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Authorization.SUCCESS, HttpStatus.OK);
    }


}
