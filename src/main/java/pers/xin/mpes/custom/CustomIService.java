package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomIService<T> extends IService<T> {

    T pessimisticLockById(Long id);

}
