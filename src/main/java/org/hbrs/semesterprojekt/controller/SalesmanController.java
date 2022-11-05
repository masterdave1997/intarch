package org.hbrs.semesterprojekt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hbrs.semesterprojekt.entities.PerformanceRecord;
import org.hbrs.semesterprojekt.entities.Salesman;
import org.hbrs.semesterprojekt.repository.PerformanceRecordRepository;
import org.hbrs.semesterprojekt.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SalesmanController {

    private final SalesmanRepository salesmanRepository;
    private final PerformanceRecordRepository recordRepository;

    @Autowired
    public SalesmanController(SalesmanRepository salesmanRepository, PerformanceRecordRepository recordRepository) {
        this.salesmanRepository = salesmanRepository;
        this.recordRepository = recordRepository;
    }

    @GetMapping("/salesmen/{id}")
    public String read(@PathVariable String id) throws JsonProcessingException {
        Salesman s = this.salesmanRepository.findById(id).orElse(null);

        if(s != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", s.getId());
            map.put("firstname", s.getFirstname());
            map.put("lastname", s.getLastname());
            map.put("performanceRecords", this.recordRepository.findAllBySid(s.getId()));

            return new ObjectMapper().writeValueAsString(map);
        }

        return null;
    }

    @GetMapping("/salesmen")
    public List<String> readAll() {
        List<Salesman> salesmen = this.salesmanRepository.findAll();

        List<String> res = new ArrayList<>();
        salesmen.forEach(s -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", s.getId());
            map.put("firstname", s.getFirstname());
            map.put("lastname", s.getLastname());
            map.put("performanceRecords", this.recordRepository.findAllBySid(s.getId()));

            try {
                res.add(new ObjectMapper().writeValueAsString(map));
            } catch (JsonProcessingException ignored) {}
        });

        return res;
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

    @PostMapping("/records")
    public String create(@RequestBody PerformanceRecord performanceRecord, HttpServletResponse response) {
        PerformanceRecord p = this.recordRepository.save(performanceRecord);

        response.setStatus(HttpServletResponse.SC_CREATED);

        return "/salesmen/" + p.sid();
    }

    @PutMapping("records")
    public String update(@RequestBody String sid, @RequestBody PerformanceRecord performanceRecord) {
        PerformanceRecord p = this.recordRepository.save(performanceRecord);

        return "/salesmen/" + p.sid();
    }
}
