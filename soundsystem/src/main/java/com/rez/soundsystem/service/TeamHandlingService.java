package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.TeamHandlingDao;
import com.rez.soundsystem.dto.TeamHandlingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamHandlingService {
    @Autowired
    private TeamHandlingDao dao;

    public List<TeamHandlingDto> all() {
        return dao.findAll();
    }

    public TeamHandlingDto byId(int id) {
        return dao.findById(id);
    }

    public int create(TeamHandlingDto t) {
        return dao.insert(t);
    }

    public int update(TeamHandlingDto t) {
        return dao.update(t);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
