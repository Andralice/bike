package com.start.bike.service.impl;

import com.start.bike.entity.Examine;
import com.start.bike.mapper.ExamineMapper;
import com.start.bike.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamineServiceImpl implements ExamineService {
    @Autowired
    private ExamineMapper examineMapper ;


    @Override
    public List<Examine> selectAllExamine(int page, int size) {
        return examineMapper.selectAllExamine();
    }

    @Override
    public Examine selectExamineById(Integer examineId) {
        return examineMapper.selectExamineById(examineId);
    }

    @Override
    public List<Examine> selectListExamine(Examine examine) {
        return examineMapper.selectListExamine(examine);
    }

    @Override
    public void CreateExamine(Examine examine) {
        examineMapper.CreateExamine(examine);
    }

    @Override
    public void UpdateExamine(Examine examine) {
        examineMapper.UpdateExamine(examine);
    }

    @Override
    public Boolean DeleteExamineById(Integer examineId) {
        int result = examineMapper.DeleteExamineById(examineId);
        return result > 0;
    }
}
