package com.start.bike.service;

import com.start.bike.entity.Examine;

import java.util.List;

public interface ExamineService {
    List<Examine> selectAllExamine(Examine examine);
    List<Examine> selectAllExamine();
    Examine selectExamineById(Integer examineId);
    List<Examine> selectListExamine(Examine examine);
    void CreateExamine(Examine examine);
    void UpdateExamine(Examine examine);
    Boolean DeleteExamineById(Integer examineId);
}
