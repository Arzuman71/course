package com.nerzon.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nerzon.course.dto.CatDTO;
import com.nerzon.course.repository.CatRepository;
import com.nerzon.course.entity.Cat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequestMapping("/api")
public class CatController {

    @Autowired
    private CatRepository catRepository;


    @Operation(
            summary = "it do something",
            description = "todo",
            deprecated = false
    )
    @PostMapping
    public void addCat(@RequestBody CatDTO catDTO) {
        Cat cat = new Cat(catDTO.getName(), catDTO.getAge(), catDTO.getWeight());
        catRepository.save(cat);

    }

    @GetMapping("/all")
    public List<Cat> getAll() throws JsonProcessingException {
        List<Cat> cats = catRepository.findAll();

        return cats;
    }

    @GetMapping()
    public Cat getCat(@RequestParam int id) {
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping()
    public void deleteCat(@RequestParam int id) {
        catRepository.deleteById(id);
    }

    @PutMapping()
    public String cheng(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "no such row";
        }
        return catRepository.save(cat).toString();
    }


}
