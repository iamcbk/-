package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.system.mapper.PointsCategoryMapper;
import com.sky.system.domain.PointsCategory;
import com.sky.system.service.IPointsCategoryService;

/**
 * 积分商品分类Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
@Service
public class PointsCategoryServiceImpl implements IPointsCategoryService 
{
    @Autowired
    private PointsCategoryMapper pointsCategoryMapper;

    /**
     * 查询积分商品分类
     * 
     * @param pointsCategoryId 积分商品分类主键
     * @return 积分商品分类
     */
    @Override
    public PointsCategory selectPointsCategoryByPointsCategoryId(Long pointsCategoryId)
    {
        return pointsCategoryMapper.selectPointsCategoryByPointsCategoryId(pointsCategoryId);
    }

    /**
     * 查询积分商品分类列表
     * 
     * @param pointsCategory 积分商品分类
     * @return 积分商品分类
     */
    @Override
    public List<PointsCategory> selectPointsCategoryList(PointsCategory pointsCategory)
    {
        return pointsCategoryMapper.selectPointsCategoryList(pointsCategory);
    }

    /**
     * 新增积分商品分类
     * 
     * @param pointsCategory 积分商品分类
     * @return 结果
     */
    @Override
    public int insertPointsCategory(PointsCategory pointsCategory)
    {
        return pointsCategoryMapper.insertPointsCategory(pointsCategory);
    }

    /**
     * 修改积分商品分类
     * 
     * @param pointsCategory 积分商品分类
     * @return 结果
     */
    @Override
    public int updatePointsCategory(PointsCategory pointsCategory)
    {
        return pointsCategoryMapper.updatePointsCategory(pointsCategory);
    }

    /**
     * 批量删除积分商品分类
     * 
     * @param pointsCategoryIds 需要删除的积分商品分类主键
     * @return 结果
     */
    @Override
    public int deletePointsCategoryByPointsCategoryIds(Long[] pointsCategoryIds)
    {
        return pointsCategoryMapper.deletePointsCategoryByPointsCategoryIds(pointsCategoryIds);
    }

    /**
     * 删除积分商品分类信息
     * 
     * @param pointsCategoryId 积分商品分类主键
     * @return 结果
     */
    @Override
    public int deletePointsCategoryByPointsCategoryId(Long pointsCategoryId)
    {
        return pointsCategoryMapper.deletePointsCategoryByPointsCategoryId(pointsCategoryId);
    }
}
