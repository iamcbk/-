package com.sky.system.mapper;

import java.util.List;
import com.sky.system.domain.Rush;

/**
 * 今日疯抢Mapper接口
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
public interface RushMapper 
{
    /**
     * 查询今日疯抢
     * 
     * @param id 今日疯抢主键
     * @return 今日疯抢
     */
    public Rush selectRushById(Long id);

    /**
     * 查询今日疯抢列表
     * 
     * @param rush 今日疯抢
     * @return 今日疯抢集合
     */
    public List<Rush> selectRushList(Rush rush);

    /**
     * 新增今日疯抢
     * 
     * @param rush 今日疯抢
     * @return 结果
     */
    public int insertRush(Rush rush);

    /**
     * 修改今日疯抢
     * 
     * @param rush 今日疯抢
     * @return 结果
     */
    public int updateRush(Rush rush);

    /**
     * 删除今日疯抢
     * 
     * @param id 今日疯抢主键
     * @return 结果
     */
    public int deleteRushById(Long id);

    /**
     * 批量删除今日疯抢
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRushByIds(Long[] ids);
}
