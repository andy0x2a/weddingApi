package com.newmansoft.controller;

import com.newmansoft.model.Family;
import com.newmansoft.model.RSVP;
import com.newmansoft.services.FamilyRepository;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/rsvp/{wedding}")
@CrossOrigin()

public class RsvpController {

    @Autowired
    private RSVPRepository rsvpRepository;

    @Autowired
    FamilyRepository familyRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RSVP save(@PathVariable String wedding, @RequestBody RSVP rsvp) {
        rsvp.setWedding(wedding);
        return  rsvpRepository.save(rsvp);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RSVP update(@PathVariable String wedding, @RequestBody RSVP rsvp) {
        rsvp.setWedding(wedding);
        return  rsvpRepository.save(rsvp);
    }


    private Family findFamily(String wedding, Map<Integer, Family> familyCache, Integer familyId) {
        Family result = familyCache.get(familyId);

        if (result == null) {
            result = familyRepository.findOne(familyId);
            familyCache.put(familyId, result);
        }

        result.setWedding(wedding);
        return result;
    }
    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    @ResponseBody
    public List<RSVP> update(@PathVariable String wedding, @RequestBody List<RSVP> rsvps) {
        List<RSVP> response = new ArrayList<>();

        Map<Integer, Family> familiesToSave = new HashMap<Integer, Family>();

        for (RSVP rsvp : rsvps) {
            rsvp.setWedding(wedding);

            Integer familyId = rsvp.getFamilyId();
            Integer id = rsvp.getId();


            if (familyId != null && (id == 0 || id == null)) {
                Family family = findFamily(wedding, familiesToSave, familyId);
                List<RSVP> members = family.getMembers();
                members.add(rsvp);
                family.setMembers(members);


            }

            response.add(rsvpRepository.save(rsvp));

        }

        familyRepository.save(familiesToSave.values());

        return response;
    }

    @RequestMapping(path="/{id}")
    @ResponseBody()
    public RSVP getGuest(@PathVariable String wedding, @PathVariable int id) {
        return rsvpRepository.findOne(id);
    }
}
