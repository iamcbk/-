package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.system.mapper.ImagesMapper;
import com.sky.system.domain.Images;
import com.sky.system.service.IImagesService;

/**
 * 图片Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
@Service
public class ImagesServiceImpl implements IImagesService 
{
    @Autowired
    private ImagesMapper imagesMapper;

    /**
     * 查询图片
     * 
     * @param otherId 图片主键
     * @return 图片
     */
    @Override
    public Images selectImagesByOtherId(Long otherId)
    {
        return imagesMapper.selectImagesByOtherId(otherId);
    }

    /**
     * 查询图片列表
     * 
     * @param images 图片
     * @return 图片
     */
    @Override
    public List<Images> selectImagesList(Images images)
    {
        return imagesMapper.selectImagesList(images);
    }

    /**
     * 新增图片
     * 
     * @param images 图片
     * @return 结果
     */
    @Override
    public int insertImages(Images images)
    {
        return imagesMapper.insertImages(images);
    }

    /**
     * 修改图片
     * 
     * @param images 图片
     * @return 结果
     */
    @Override
    public int updateImages(Images images)
    {
        return imagesMapper.updateImages(images);
    }

    /**
     * 批量删除图片
     * 
     * @param otherIds 需要删除的图片主键
     * @return 结果
     */
    @Override
    public int deleteImagesByOtherIds(Long[] otherIds)
    {
        return imagesMapper.deleteImagesByOtherIds(otherIds);
    }

    /**
     * 删除图片信息
     * 
     * @param otherId 图片主键
     * @return 结果
     */
    @Override
    public int deleteImagesByOtherId(Long otherId)
    {
        return imagesMapper.deleteImagesByOtherId(otherId);
    }
}
