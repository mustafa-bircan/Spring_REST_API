package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Long, Koala> koalas = new HashMap<>();

    @PostMapping
    public Koala addKoala(@RequestBody Koala koala) {
        if (koala.getName() == null  || koala.getWeight() <= 0) {
            throw new ZooException("Fields cannot empty, negative, or 0", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @GetMapping
    public List<Koala> getAllKoalas() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable long id) {
        if (id <= 0) {
            throw new ZooException("Id cannot be 0 or negative", HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable long id, @RequestBody Koala koala) {
        if (id <= 0) {
            throw new ZooException("Id cannot be 0 or negative", HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.BAD_REQUEST);
        }
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable long id) {
        if (id <= 0) {
            throw new ZooException("Id cannot be 0 or negative", HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("Id does not exist", HttpStatus.BAD_REQUEST);
        }
        return koalas.remove(id);
    }
}
