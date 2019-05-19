package pers.xin.mpes.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xin.mpes.dao.OperationLogDao;
import pers.xin.mpes.dao.mapper.OperationLogMapper;
import pers.xin.mpes.entity.OperationLog;

@Service("operationLogDao")
public class OperationLogDaoImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogDao {
}
