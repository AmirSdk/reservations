
package com.example.reservations.controller;

import com.example.reservations.model.Artist;
import com.example.reservations.repository.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artists")
    public String listArtists(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "artists/list";
    }
}
