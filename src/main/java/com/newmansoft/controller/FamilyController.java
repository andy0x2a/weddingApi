package com.newmansoft.controller;

import com.newmansoft.model.Family;
import com.newmansoft.model.RSVP;
import com.newmansoft.services.FamilyRepository;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/family")
@CrossOrigin()

public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private RSVPRepository rsvpRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Family save(@RequestBody Family family) {
        return  familyRepository.save(family);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public List<Family> saveAll(@RequestBody List<Family> families) {
        for (Family family : families) {

            List<RSVP> members = family.getMembers();
            for (RSVP member : members) {
           member.setFamilyName(family.getName());
                RSVP save = rsvpRepository.save(member);

                member.setId(save.getId());
            }
        }

          return families;
    }


     @RequestMapping(path="/")
    @ResponseBody
    public Iterable<Family> getAllFamilies() {
         return familyRepository.findAll();
    }

    @RequestMapping(path="/{id}")
    @ResponseBody()
    public Family getGuest(@PathVariable int id) {
        return familyRepository.findOne(id);
    }
}
