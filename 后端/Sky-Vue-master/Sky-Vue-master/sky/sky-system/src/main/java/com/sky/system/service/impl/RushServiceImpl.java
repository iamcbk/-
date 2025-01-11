package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.system.mapper.RushMapper;
import com.sky.system.domain.Rush;
import com.sky.system.service.IRushService;

/**
 * 今日疯抢Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
@Service
public class RushServiceImpl implements IRushService 
{
    @Autowired
    private RushMapper rushMapper;

    /**
     * 查询今日疯抢
     * 
     * @param id 今日疯抢主键
     * @return 今日疯抢
     */
    @Override
    public Rush selectRushById(Long id)
    {
        return rushMapper.selectRushById(id);
    }

    /**
     * 查询今日疯抢列表
     * 
     * @param rush 今日疯抢
     * @return 今日疯抢
     */
    @Override
    public List<Rush> selectRushList(Rush rush)
    {
        return rushMapper.selectRushList(rush);
    }

    /**
     * 新增今日疯抢
     * 
     * @param rush 今日疯抢
     * @return 结果
     */
    @Override
    public int insertRush(Rush rush)
    {
        return rushMapper.insertRush(rush);
    }

    /**
     * 修改今日疯抢
     * 
     * @param rush 今日疯抢
     * @return 结果
     */
    @Override
    public int updateRush(Rush rush)
    {
        return rushMapper.updateRush(rush);
    }

    /**
     * 批量删除今日疯抢
     * 
     * @param ids 需要删除的今日疯抢主键
     * @return 结果
     */
    @Override
    public int deleteRushByIds(Long[] ids)
    {
        return rushMapper.deleteRushByIds(ids);
    }

    /**
     * 删除今日疯抢信息
     * 
     * @param id 今日疯抢主键
     * @return 结果
     */
    @Override
    public int deleteRushById(Long id)
    {
        return rushMapper.deleteRushById(id);
    }
}
