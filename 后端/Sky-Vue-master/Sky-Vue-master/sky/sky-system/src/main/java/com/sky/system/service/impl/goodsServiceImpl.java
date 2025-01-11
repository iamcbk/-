package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.sky.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.sky.system.domain.Category;
import com.sky.system.mapper.goodsMapper;
import com.sky.system.domain.goods;
import com.sky.system.service.IgoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-23
 */
@Service
public class goodsServiceImpl implements IgoodsService 
{
    @Autowired
    private goodsMapper goodsMapper;

    /**
     * 查询商品
     * 
     * @param goodsId 商品主键
     * @return 商品
     */
    @Override
    public goods selectgoodsByGoodsId(Long goodsId)
    {
        return goodsMapper.selectgoodsByGoodsId(goodsId);
    }

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品
     */
    @Override
    public List<goods> selectgoodsList(goods goods)
    {
        return goodsMapper.selectgoodsList(goods);
    }

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Transactional
    @Override
    public int insertgoods(goods goods)
    {
        int rows = goodsMapper.insertgoods(goods);
        insertCategory(goods);
        return rows;
    }

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Transactional
    @Override
    public int updategoods(goods goods)
    {
        goodsMapper.deleteCategoryByCategoryId(goods.getGoodsId());
        insertCategory(goods);
        return goodsMapper.updategoods(goods);
    }

    /**
     * 批量删除商品
     * 
     * @param goodsIds 需要删除的商品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletegoodsByGoodsIds(Long[] goodsIds)
    {
        goodsMapper.deleteCategoryByCategoryIds(goodsIds);
        return goodsMapper.deletegoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除商品信息
     * 
     * @param goodsId 商品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletegoodsByGoodsId(Long goodsId)
    {
        goodsMapper.deleteCategoryByCategoryId(goodsId);
        return goodsMapper.deletegoodsByGoodsId(goodsId);
    }

    /**
     * 新增商品分类信息
     * 
     * @param goods 商品对象
     */
    public void insertCategory(goods goods)
    {
        List<Category> categoryList = goods.getCategoryList();
        Long goodsId = goods.getGoodsId();
        if (StringUtils.isNotNull(categoryList))
        {
            List<Category> list = new ArrayList<Category>();
            for (Category category : categoryList)
            {
                category.setCategoryId(goodsId);
                list.add(category);
            }
            if (list.size() > 0)
            {
                goodsMapper.batchCategory(list);
            }
        }
    }
}
