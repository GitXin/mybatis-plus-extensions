package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CustomBaseMapper<T> extends BaseMapper<T> {

    T pessimisticLockById(Long id);

}
