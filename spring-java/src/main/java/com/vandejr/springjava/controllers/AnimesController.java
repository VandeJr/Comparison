package com.vandejr.springjava.controllers;

import com.vandejr.springjava.entities.Anime;
import com.vandejr.springjava.repositories.AnimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/animes")
public class AnimesController {

    @Autowired
    private AnimesRepository repository;

    @GetMapping("/")
    public List<Anime> getAll() {
        return repository.getAll().stream().map(Anime::new).toList();
    }

    @GetMapping("/{id}")
    public Optional<Anime> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody Anime anime) {
        try {
            repository.save(anime);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Anime anime) {
        try {
            repository.save(anime);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
