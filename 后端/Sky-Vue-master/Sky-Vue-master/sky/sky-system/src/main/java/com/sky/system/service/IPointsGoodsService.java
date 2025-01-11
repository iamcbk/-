package com.sky.system.service;

import java.util.List;
import com.sky.system.domain.PointsGoods;

/**
 * 积分商品Service接口
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
public interface IPointsGoodsService 
{
    /**
     * 查询积分商品
     * 
     * @param pointsGoodId 积分商品主键
     * @return 积分商品
     */
    public PointsGoods selectPointsGoodsByPointsGoodId(Long pointsGoodId);

    /**
     * 查询积分商品列表
     * 
     * @param pointsGoods 积分商品
     * @return 积分商品集合
     */
    public List<PointsGoods> selectPointsGoodsList(PointsGoods pointsGoods);

    /**
     * 新增积分商品
     * 
     * @param pointsGoods 积分商品
     * @return 结果
     */
    public int insertPointsGoods(PointsGoods pointsGoods);

    /**
     * 修改积分商品
     * 
     * @param pointsGoods 积分商品
     * @return 结果
     */
    public int updatePointsGoods(PointsGoods pointsGoods);

    /**
     * 批量删除积分商品
     * 
     * @param pointsGoodIds 需要删除的积分商品主键集合
     * @return 结果
     */
    public int deletePointsGoodsByPointsGoodIds(Long[] pointsGoodIds);

    /**
     * 删除积分商品信息
     * 
     * @param pointsGoodId 积分商品主键
     * @return 结果
     */
    public int deletePointsGoodsByPointsGoodId(Long pointsGoodId);
}
