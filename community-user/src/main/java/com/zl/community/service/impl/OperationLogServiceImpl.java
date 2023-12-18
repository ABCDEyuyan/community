package com.zl.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zl.community.mapper.OperationLogMapper;
import com.zl.community.model.entity.OperationLogEntity;
import com.zl.community.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
* @author ABCD
* @description 针对表【operation_log(操作日志表)】的数据库操作Service实现
* @createDate 2023-12-13 11:00:46
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogEntity>
    implements OperationLogService {

}




