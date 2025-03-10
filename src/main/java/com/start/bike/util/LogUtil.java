package com.start.bike.util;

import com.start.bike.entity.Log;
import com.start.bike.service.LogService;
import com.start.bike.context.ThreadLocalContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {

    @Autowired
    private LogService logService;

    /**
     * 记录操作日志
     *
     * @param hisData   历史数据
     * @param carrySQL  执行的 SQL 语句
     * @param updateData 更新后的数据
     * @param actorName 操作人名称
     */
    public void logOperation(String type,Object hisData, String carrySQL, Object updateData, String actorName) {
        // 创建日志对象并插入日志表
        Log log = new Log();
        log.setType(type);
        log.setHisData(String.valueOf(hisData)); // 根据实际情况填充数据
        log.setCarrySQL(carrySQL); // 设置执行的 SQL 语句
        log.setUpdateData(String.valueOf(updateData)); // 根据实际情况填充数据
        log.setActorName(actorName); // 根据实际情况填充操作人

        logService.insertLog(log);

        // 清除线程本地变量
        ThreadLocalContext.clearLastExecutedSql();
    }
}