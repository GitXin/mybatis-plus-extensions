package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomIService<T> extends IService<T> {

    T pessimisticLockById(Long id);

    T findOne(QueryWrapper<T> queryWrapper);

    boolean exists(QueryWrapper<T> queryWrapper);

}
