package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class CustomServiceImpl<M extends CustomBaseMapper<T>, T > extends ServiceImpl<M, T> implements CustomIService<T> {

    @Override
    public T pessimisticLockById(Long id) {
        return this.baseMapper.pessimisticLockById(id);
    }

}
