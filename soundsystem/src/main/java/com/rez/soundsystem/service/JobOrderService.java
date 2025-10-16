package com.rez.soundsystem.service;

import com.rez.soundsystem.dao.JobOrderDao;
import com.rez.soundsystem.dto.JobOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOrderService {
    @Autowired private JobOrderDao dao;
    public List<JobOrderDto> all(){ return dao.findAll(); }
    public JobOrderDto byId(int id){ return dao.findById(id); }
    public int create(JobOrderDto j){ return dao.insert(j); }
    public int update(JobOrderDto j){ return dao.update(j); }
    public int delete(int id){ return dao.delete(id); }
}
