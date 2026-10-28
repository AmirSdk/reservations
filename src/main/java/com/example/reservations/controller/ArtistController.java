
package com.example.reservations.controller;

import com.example.reservations.model.Artist;
import com.example.reservations.repository.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @GetMapping("/artists/new")
    public String showCreateForm(Model model) {
        model.addAttribute("artist", new Artist());
        return "artists/create";
    }


    @PostMapping("/artists")
    public String createArtist(@ModelAttribute Artist artist) {
        artistRepository.save(artist);
        return "redirect:/artists";
    }

}

