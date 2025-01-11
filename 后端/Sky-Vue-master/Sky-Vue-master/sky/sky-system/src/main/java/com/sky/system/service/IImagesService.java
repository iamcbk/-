package com.sky.system.service;

import java.util.List;
import com.sky.system.domain.Images;

/**
 * 图片Service接口
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
public interface IImagesService 
{
    /**
     * 查询图片
     * 
     * @param otherId 图片主键
     * @return 图片
     */
    public Images selectImagesByOtherId(Long otherId);

    /**
     * 查询图片列表
     * 
     * @param images 图片
     * @return 图片集合
     */
    public List<Images> selectImagesList(Images images);

    /**
     * 新增图片
     * 
     * @param images 图片
     * @return 结果
     */
    public int insertImages(Images images);

    /**
     * 修改图片
     * 
     * @param images 图片
     * @return 结果
     */
    public int updateImages(Images images);

    /**
     * 批量删除图片
     * 
     * @param otherIds 需要删除的图片主键集合
     * @return 结果
     */
    public int deleteImagesByOtherIds(Long[] otherIds);

    /**
     * 删除图片信息
     * 
     * @param otherId 图片主键
     * @return 结果
     */
    public int deleteImagesByOtherId(Long otherId);
}
