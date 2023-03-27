package com.shapran.service;

import com.shapran.model.Detail;
import com.shapran.model.StatsDTO;
import com.shapran.repository.DetailRepository;
import com.shapran.util.ThreadRobots;

import java.util.List;
import java.util.Optional;

public class DetailService {
    private final DetailRepository repository = DetailRepository.getInstance();

    public Detail createDetail() throws InterruptedException {
        ThreadRobots threadRobots = new ThreadRobots();
        Detail detail = threadRobots.create();
        repository.save(detail);
        return detail;
    }

    public Optional<Detail> getById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Error id");
        } else {
            return repository.getById(id);
        }
    }

    public StatsDTO getStats(){
        return repository.getStats();
    }

    public List<String> getAllId() {
        return repository.getAllId();
    }


}
