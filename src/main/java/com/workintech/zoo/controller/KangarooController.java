package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Long, Kangaroo> kangaroos = new HashMap<>();


    @PostMapping
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getName() == null  || kangaroo.getWeight() <= 0 || kangaroo.getHeight() <= 0) {
            throw new ZooException("Fields cannot negative, null, or 0", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }
    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable long id) {
        if (id <= 0) {
            throw new ZooException("Id cannot be negative", HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable long id, @RequestBody Kangaroo kangaroo) {
        if (id <= 0) {
            throw new ZooException("Id cannot be 0 or negative", HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable long id) {
        if (id <= 0) {
            throw new ZooException("Id cannot be 0 or negative", HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.BAD_REQUEST);
        }

        return kangaroos.remove(id);
    }

}
