package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.system.mapper.PointsGoodsMapper;
import com.sky.system.domain.PointsGoods;
import com.sky.system.service.IPointsGoodsService;

/**
 * 积分商品Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
@Service
public class PointsGoodsServiceImpl implements IPointsGoodsService 
{
    @Autowired
    private PointsGoodsMapper pointsGoodsMapper;

    /**
     * 查询积分商品
     * 
     * @param pointsGoodId 积分商品主键
     * @return 积分商品
     */
    @Override
    public PointsGoods selectPointsGoodsByPointsGoodId(Long pointsGoodId)
    {
        return pointsGoodsMapper.selectPointsGoodsByPointsGoodId(pointsGoodId);
    }

    /**
     * 查询积分商品列表
     * 
     * @param pointsGoods 积分商品
     * @return 积分商品
     */
    @Override
    public List<PointsGoods> selectPointsGoodsList(PointsGoods pointsGoods)
    {
        return pointsGoodsMapper.selectPointsGoodsList(pointsGoods);
    }

    /**
     * 新增积分商品
     * 
     * @param pointsGoods 积分商品
     * @return 结果
     */
    @Override
    public int insertPointsGoods(PointsGoods pointsGoods)
    {
        return pointsGoodsMapper.insertPointsGoods(pointsGoods);
    }

    /**
     * 修改积分商品
     * 
     * @param pointsGoods 积分商品
     * @return 结果
     */
    @Override
    public int updatePointsGoods(PointsGoods pointsGoods)
    {
        return pointsGoodsMapper.updatePointsGoods(pointsGoods);
    }

    /**
     * 批量删除积分商品
     * 
     * @param pointsGoodIds 需要删除的积分商品主键
     * @return 结果
     */
    @Override
    public int deletePointsGoodsByPointsGoodIds(Long[] pointsGoodIds)
    {
        return pointsGoodsMapper.deletePointsGoodsByPointsGoodIds(pointsGoodIds);
    }

    /**
     * 删除积分商品信息
     * 
     * @param pointsGoodId 积分商品主键
     * @return 结果
     */
    @Override
    public int deletePointsGoodsByPointsGoodId(Long pointsGoodId)
    {
        return pointsGoodsMapper.deletePointsGoodsByPointsGoodId(pointsGoodId);
    }
}
