package com.start.bike.mapper;
import com.start.bike.entity.Examine;

import java.util.List;

public interface ExamineMapper {
    List<Examine> selectAllExamine(Examine examine);
    List<Examine> selectAllExamine();
    Examine selectExamineById(Integer examineId);
    List<Examine> selectListExamine(Examine examine);
    void CreateExamine(Examine examine);
    void UpdateExamine(Examine examine);

    int DeleteExamineById(Integer examineId);
//    int DeleteExamineById(Integer examineId);
}
