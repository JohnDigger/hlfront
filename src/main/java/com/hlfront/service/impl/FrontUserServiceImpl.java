package com.hlfront.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hlfront.entity.TFrontUser;
import com.hlfront.service.FrontUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 贾佳
 * @date 2023/3/7 16:19
 */
@Service
public class FrontUserServiceImpl implements FrontUserService {
    @Override
    public boolean saveBatch(Collection<TFrontUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<TFrontUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<TFrontUser> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(TFrontUser entity) {
        return false;
    }

    @Override
    public TFrontUser getOne(Wrapper<TFrontUser> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<TFrontUser> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<TFrontUser> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<TFrontUser> getBaseMapper() {
        return null;
    }
}
