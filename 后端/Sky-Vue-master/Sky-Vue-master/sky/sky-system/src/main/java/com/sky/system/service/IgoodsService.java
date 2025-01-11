package com.sky.system.service;

import java.util.List;
import com.sky.system.domain.goods;

/**
 * 商品Service接口
 * 
 * @author yeboyu
 * @date 2024-11-23
 */
public interface IgoodsService 
{
    /**
     * 查询商品
     * 
     * @param goodsId 商品主键
     * @return 商品
     */
    public goods selectgoodsByGoodsId(Long goodsId);

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
     */
    public List<goods> selectgoodsList(goods goods);

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int insertgoods(goods goods);

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int updategoods(goods goods);

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的商品主键集合
     * @return 结果
     */
    public int deletegoodsByGoodsIds(Long[] goodsIds);

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品主键
     * @return 结果
     */
    public int deletegoodsByGoodsId(Long goodsId);
}
