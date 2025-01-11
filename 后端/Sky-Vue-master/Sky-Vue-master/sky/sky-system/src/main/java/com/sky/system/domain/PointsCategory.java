package com.sky.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 积分商品分类对象 points_category
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
public class PointsCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 积分商品分类ID */
    private Long pointsCategoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String pointsCategoryName;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    public Long getPointsCategoryId() {
        return pointsCategoryId;
    }

    public void setPointsCategoryId(Long pointsCategoryId) {
        this.pointsCategoryId = pointsCategoryId;
    }

    public String getPointsCategoryName() {
        return pointsCategoryName;
    }

    public void setPointsCategoryName(String pointsCategoryName) {
        this.pointsCategoryName = pointsCategoryName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pointsCategoryId", getPointsCategoryId())
            .append("pointsCategoryName", getPointsCategoryName())
            .append("status", getStatus())
            .toString();
    }
}
