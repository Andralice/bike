package com.start.bike.mapper;
import com.start.bike.entity.Examine;

import java.util.List;

public interface ExamineMapper {
    List<Examine> selectAllExamine(int page, int size);
    Examine selectExamineById(Integer examineId);
    List<Examine> selectListExamine(Examine examine);
    void CreateExamine(Examine examine);
    void UpdateExamine(Examine examine);
    int DeleteExamineById(Integer examineId);
}
