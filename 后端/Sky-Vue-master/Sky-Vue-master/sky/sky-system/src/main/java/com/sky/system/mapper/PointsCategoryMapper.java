package com.sky.system.mapper;

import java.util.List;
import com.sky.system.domain.PointsCategory;

/**
 * 积分商品分类Mapper接口
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
public interface PointsCategoryMapper 
{
    /**
     * 查询积分商品分类
     * 
     * @param pointsCategoryId 积分商品分类主键
     * @return 积分商品分类
     */
    public PointsCategory selectPointsCategoryByPointsCategoryId(Long pointsCategoryId);

    /**
     * 查询积分商品分类列表
     * 
     * @param pointsCategory 积分商品分类
     * @return 积分商品分类集合
     */
    public List<PointsCategory> selectPointsCategoryList(PointsCategory pointsCategory);

    /**
     * 新增积分商品分类
     * 
     * @param pointsCategory 积分商品分类
     * @return 结果
     */
    public int insertPointsCategory(PointsCategory pointsCategory);

    /**
     * 修改积分商品分类
     * 
     * @param pointsCategory 积分商品分类
     * @return 结果
     */
    public int updatePointsCategory(PointsCategory pointsCategory);

    /**
     * 删除积分商品分类
     * 
     * @param pointsCategoryId 积分商品分类主键
     * @return 结果
     */
    public int deletePointsCategoryByPointsCategoryId(Long pointsCategoryId);

    /**
     * 批量删除积分商品分类
     * 
     * @param pointsCategoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointsCategoryByPointsCategoryIds(Long[] pointsCategoryIds);
}
