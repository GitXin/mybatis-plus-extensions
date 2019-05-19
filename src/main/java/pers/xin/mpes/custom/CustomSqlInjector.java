package pers.xin.mpes.custom;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> abstractMethods = super.getMethodList();
        abstractMethods.add(new PessimisticLockById());
        return abstractMethods;
    }

}
