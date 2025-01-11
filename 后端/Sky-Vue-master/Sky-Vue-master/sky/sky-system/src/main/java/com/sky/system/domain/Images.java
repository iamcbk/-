package com.sky.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 图片对象 images
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
public class Images extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 其他ID */
    private Long otherId;

    /** 首页图片集 */
    @Excel(name = "首页图片集")
    private String images;

    public void setOtherId(Long otherId) 
    {
        this.otherId = otherId;
    }

    public Long getOtherId() 
    {
        return otherId;
    }
    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("otherId", getOtherId())
            .append("images", getImages())
            .toString();
    }
}
