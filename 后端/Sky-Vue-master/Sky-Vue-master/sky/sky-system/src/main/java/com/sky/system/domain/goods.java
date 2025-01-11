package com.sky.system.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 商品对象 goods
 * 
 * @author yeboyu
 * @date 2024-11-23
 */
public class goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long goodsId;

    /** 图片集 */
    @Excel(name = "图片集")
    private String photo;

    /** 主标题 */
    @Excel(name = "主标题")
    private String mainTitle;

    /** 小标题 */
    @Excel(name = "小标题")
    private String secondaryTitle;

    /** 套餐类别 */
    @Excel(name = "套餐类别")
    private Long categoryId;
    /**
     * 套餐名字
     */
    private String categoryName;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String description;

    /** 已售件数 */
    @Excel(name = "已售件数")
    private Long soldNumber;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 积分值 */
    @Excel(name = "积分值")
    private Long points;

    /** 商品产地 */
    @Excel(name = "商品产地")
    private String originPlace;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String specification;

    /** 保质期 */
    @Excel(name = "保质期")
    private String shelfLife;

    /** 发货地 */
    @Excel(name = "发货地")
    private String deliver;

    /** 广告图片集 */
    @Excel(name = "广告图片集")
    private String advertisement;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private String goodsStatus;



    /** 商品分类信息 */
    private List<Category> categoryList;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setMainTitle(String mainTitle) 
    {
        this.mainTitle = mainTitle;
    }

    public String getMainTitle() 
    {
        return mainTitle;
    }
    public void setSecondaryTitle(String secondaryTitle) 
    {
        this.secondaryTitle = secondaryTitle;
    }

    public String getSecondaryTitle() 
    {
        return secondaryTitle;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setSoldNumber(Long soldNumber) 
    {
        this.soldNumber = soldNumber;
    }

    public Long getSoldNumber() 
    {
        return soldNumber;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setPoints(Long points) 
    {
        this.points = points;
    }

    public Long getPoints() 
    {
        return points;
    }
    public void setOriginPlace(String originPlace) 
    {
        this.originPlace = originPlace;
    }

    public String getOriginPlace() 
    {
        return originPlace;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setShelfLife(String shelfLife) 
    {
        this.shelfLife = shelfLife;
    }

    public String getShelfLife() 
    {
        return shelfLife;
    }
    public void setDeliver(String deliver) 
    {
        this.deliver = deliver;
    }

    public String getDeliver() 
    {
        return deliver;
    }
    public void setAdvertisement(String advertisement) 
    {
        this.advertisement = advertisement;
    }

    public String getAdvertisement() 
    {
        return advertisement;
    }
    public void setGoodsStatus(String goodsStatus) 
    {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsStatus() 
    {
        return goodsStatus;
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("photo", getPhoto())
            .append("mainTitle", getMainTitle())
            .append("secondaryTitle", getSecondaryTitle())
            .append("categoryId", getCategoryId())
            .append("description", getDescription())
            .append("soldNumber", getSoldNumber())
            .append("price", getPrice())
            .append("points", getPoints())
            .append("remark", getRemark())
            .append("originPlace", getOriginPlace())
            .append("specification", getSpecification())
            .append("shelfLife", getShelfLife())
            .append("deliver", getDeliver())
            .append("advertisement", getAdvertisement())
            .append("goodsStatus", getGoodsStatus())
            .append("categoryList", getCategoryList())
            .toString();
    }
}
