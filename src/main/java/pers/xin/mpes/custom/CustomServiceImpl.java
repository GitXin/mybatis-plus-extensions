package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class CustomServiceImpl<M extends CustomBaseMapper<T>, T > extends ServiceImpl<M, T> implements CustomIService<T> {

    @Override
    public T pessimisticLockById(Long id) {
        return this.baseMapper.pessimisticLockById(id);
    }

    @Override
    public T findOne(QueryWrapper<T> queryWrapper) {
        return this.getOne(queryWrapper.last("limit 1"));
    }

    @Override
    public boolean exists(QueryWrapper<T> queryWrapper) {
        int count = this.count(queryWrapper);
        return count > 0;
    }

}
