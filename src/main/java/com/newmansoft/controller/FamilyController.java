package com.newmansoft.controller;

import com.newmansoft.model.Family;
import com.newmansoft.model.RSVP;
import com.newmansoft.services.FamilyRepository;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/family/{wedding}")
@CrossOrigin()

public class FamilyController {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private RSVPRepository rsvpRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Family save(@PathVariable String wedding, @RequestBody Family family) {
        family.setWedding(wedding);
        return  familyRepository.save(family);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public List<Family> saveAll(@PathVariable String wedding, @RequestBody List<Family> families) {
        for (Family family : families) {
           family.setWedding(wedding);
            List<RSVP> members = family.getMembers();
            for (RSVP member : members) {
           member.setFamilyName(family.getName());
                member.setWedding(wedding);
                RSVP save = rsvpRepository.save(member);

                member.setId(save.getId());
            }
        }

          return families;
    }


     @RequestMapping(path="/")
    @ResponseBody
    public Iterable<Family> getAllFamilies(@PathVariable String wedding) {
         return familyRepository.findByWedding(wedding);
    }

    @RequestMapping(path="/{id}")
    @ResponseBody()
    public Family getGuest(@PathVariable String wedding, @PathVariable int id) {
        return familyRepository.findOne(id);
    }
}
