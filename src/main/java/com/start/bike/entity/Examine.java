package com.start.bike.entity;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Examine {
    private Integer examineId; // 审核ID
    private String examineName; // 审核名称
    private String examineData; // 执行数据
    private String examineStatus; // 审核状态  // 0-待审核 1-审核通过 2-审核不通过
    private String examineType; // 审核类型
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

}
