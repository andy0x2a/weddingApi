package com.newmansoft.controller;

import com.newmansoft.model.RSVP;
import com.newmansoft.services.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping("/guests/")
    @ResponseBody()
    public Iterable<RSVP> getAllGuests() {
        return rsvpRepository.findAll();
    }
}
