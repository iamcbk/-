package com.sky.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sky.common.annotation.Log;
import com.sky.common.core.controller.BaseController;
import com.sky.common.core.domain.AjaxResult;
import com.sky.common.enums.BusinessType;
import com.sky.system.domain.Images;
import com.sky.system.service.IImagesService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 图片Controller
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/images/images")
public class ImagesController extends BaseController
{
    @Autowired
    private IImagesService imagesService;

    /**
     * 查询图片列表
     */
    @PreAuthorize("@ss.hasPermi('images:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(Images images)
    {
        startPage();
        List<Images> list = imagesService.selectImagesList(images);
        return getDataTable(list);
    }

    /**
     * 导出图片列表
     */
    @PreAuthorize("@ss.hasPermi('images:images:export')")
    @Log(title = "图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Images images)
    {
        List<Images> list = imagesService.selectImagesList(images);
        ExcelUtil<Images> util = new ExcelUtil<Images>(Images.class);
        util.exportExcel(response, list, "图片数据");
    }

    /**
     * 获取图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('images:images:query')")
    @GetMapping(value = "/{otherId}")
    public AjaxResult getInfo(@PathVariable("otherId") Long otherId)
    {
        return success(imagesService.selectImagesByOtherId(otherId));
    }

    /**
     * 新增图片
     */
    @PreAuthorize("@ss.hasPermi('images:images:add')")
    @Log(title = "图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Images images)
    {
        return toAjax(imagesService.insertImages(images));
    }

    /**
     * 修改图片
     */
    @PreAuthorize("@ss.hasPermi('images:images:edit')")
    @Log(title = "图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Images images)
    {
        return toAjax(imagesService.updateImages(images));
    }

    /**
     * 删除图片
     */
    @PreAuthorize("@ss.hasPermi('images:images:remove')")
    @Log(title = "图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{otherIds}")
    public AjaxResult remove(@PathVariable Long[] otherIds)
    {
        return toAjax(imagesService.deleteImagesByOtherIds(otherIds));
    }
}
