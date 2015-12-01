package com.newmansoft.controller;

import com.newmansoft.model.RSVP;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rsvp")
@CrossOrigin()

public class RsvpController {

    @Autowired
    private RSVPRepository rsvpRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public RSVP save(@RequestBody RSVP rsvp) {
        return  rsvpRepository.save(rsvp);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public RSVP update(@RequestBody RSVP rsvp) {
        return  rsvpRepository.save(rsvp);
    }

    @RequestMapping(value = "/updateList", method = RequestMethod.POST)
    @ResponseBody


    public List<RSVP> update(@RequestBody List<RSVP> rsvps) {
        List<RSVP> response = new ArrayList<>();
        for (RSVP rsvp : rsvps) {
            response.add(rsvpRepository.save(rsvp));
        }

        return response;
    }

    @RequestMapping(path="/{id}")
    @ResponseBody()
    public RSVP getGuest(@PathVariable int id) {
        return rsvpRepository.findOne(id);
    }
}
