package org.hbrs.semesterprojekt.controller;

import org.hbrs.semesterprojekt.entities.Salesman;
import org.hbrs.semesterprojekt.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class SalesmanController {

    private final SalesmanRepository salesmanRepository;

    @Autowired
    public SalesmanController(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @GetMapping("/salesmen/{id}")
    public String read(@PathVariable String id) {
        Salesman s = salesmanRepository.findById(id).orElse(null);

        return s != null ? s.toString() : "404 - Not Found";
    }

    @GetMapping("/salesmen")
    public List<Salesman> readAll() {
        return salesmanRepository.findAll();
    }

    @PostMapping("/salesmen")
    public String create(@RequestBody Salesman salesman, HttpServletResponse response) {
        Salesman s = this.salesmanRepository.save(salesman);

        response.setStatus(HttpServletResponse.SC_CREATED);

        return "/salesmen/" + s.getId();
    }

    @PutMapping("/salesmen")
    public String update(@RequestBody Salesman salesman) {
        Salesman s = this.salesmanRepository.save(salesman);

        return "/salesmen/" + s.getId();
    }

    @DeleteMapping("/salesmen/{id}")
    public void delete(@PathVariable String id) {
        this.salesmanRepository.deleteById(id);
    }
}
