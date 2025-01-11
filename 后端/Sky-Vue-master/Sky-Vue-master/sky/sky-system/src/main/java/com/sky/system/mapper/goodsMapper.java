package com.sky.system.mapper;

import java.util.List;
import com.sky.system.domain.goods;
import com.sky.system.domain.Category;

/**
 * 商品Mapper接口
 * 
 * @author yeboyu
 * @date 2024-11-23
 */
public interface goodsMapper 
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
     * 删除商品
     * 
     * @param goodsId 商品主键
     * @return 结果
     */
    public int deletegoodsByGoodsId(Long goodsId);

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletegoodsByGoodsIds(Long[] goodsIds);

    /**
     * 批量删除商品分类
     * 
     * @param goodsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(Long[] goodsIds);
    
    /**
     * 批量新增商品分类
     * 
     * @param categoryList 商品分类列表
     * @return 结果
     */
    public int batchCategory(List<Category> categoryList);
    

    /**
     * 通过商品主键删除商品分类信息
     * 
     * @param goodsId 商品ID
     * @return 结果
     */
    public int deleteCategoryByCategoryId(Long goodsId);
}
