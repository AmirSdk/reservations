
package com.example.reservations.controller;

import com.example.reservations.model.Artist;
import com.example.reservations.repository.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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


    @GetMapping("/artists/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Artiste introuvable : " + id
                ));

        model.addAttribute("artist", artist);

        return "artists/edit";
    }


    @PostMapping("/artists/{id}")
    public String updateArtist(
            @PathVariable Long id,
            @ModelAttribute Artist artist) {

        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Artiste introuvable : " + id
                ));

        existingArtist.setFirstname(artist.getFirstname());
        existingArtist.setLastname(artist.getLastname());

        artistRepository.save(existingArtist);

        return "redirect:/artists";
    }


    @PostMapping("/artists/{id}/delete")
    public String deleteArtist(@PathVariable Long id) {

        if (!artistRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Artiste introuvable : " + id
            );
        }

        artistRepository.deleteById(id);

        return "redirect:/artists";
    }


}

