package com.sky.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 积分商品对象 points_goods
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
public class PointsGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 积分商品ID */
    private Long pointsGoodId;

    /** 图片集 */
    @Excel(name = "图片集")
    private String photo;

    /** 商品名 */
    @Excel(name = "商品名")
    private String title;

    /** 积分套餐类别 */
    @Excel(name = "积分套餐类别")
    private Long pointsCategoryId;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String pointsDescription;

    /** 已售件数 */
    @Excel(name = "已售件数")
    private Long soldNumber;

    public String getPointsCategoryName() {
        return pointsCategoryName;
    }

    public void setPointsCategoryName(String pointsCategoryName) {
        this.pointsCategoryName = pointsCategoryName;
    }


    private String pointsCategoryName;

    @Override
    public String toString() {
        return "PointsGoods{" +
                "pointsCategoryName='" + pointsCategoryName + '\'' +
                '}';
    }

    /** 积分值 */
    @Excel(name = "积分值")
    private Long points;


    public void setPointsGoodId(Long pointsGoodId) 
    {
        this.pointsGoodId = pointsGoodId;
    }

    public Long getPointsGoodId() 
    {
        return pointsGoodId;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPointsCategoryId(Long pointsCategoryId) 
    {
        this.pointsCategoryId = pointsCategoryId;
    }

    public Long getPointsCategoryId() 
    {
        return pointsCategoryId;
    }
    public void setPointsDescription(String pointsDescription) 
    {
        this.pointsDescription = pointsDescription;
    }

    public String getPointsDescription() 
    {
        return pointsDescription;
    }
    public void setSoldNumber(Long soldNumber) 
    {
        this.soldNumber = soldNumber;
    }

    public Long getSoldNumber() 
    {
        return soldNumber;
    }
    public void setPoints(Long points) 
    {
        this.points = points;
    }

    public Long getPoints() 
    {
        return points;
    }

}
