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
import com.sky.system.domain.PointsCategory;
import com.sky.system.service.IPointsCategoryService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 积分商品分类Controller
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
@RestController
@RequestMapping("/point_category/point_category")
public class PointsCategoryController extends BaseController
{
    @Autowired
    private IPointsCategoryService pointsCategoryService;

    /**
     * 查询积分商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:list')")
    @GetMapping("/list")
    public TableDataInfo list(PointsCategory pointsCategory)
    {
        startPage();
        List<PointsCategory> list = pointsCategoryService.selectPointsCategoryList(pointsCategory);
        for (PointsCategory title : list) {
            title.setPointsCategoryName(title.getPointsCategoryId() == null ? "" :
                    pointsCategoryService.selectPointsCategoryByPointsCategoryId(title.getPointsCategoryId()).getPointsCategoryName());
        }

        return getDataTable(list);
    }

    /**
     * 导出积分商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:export')")
    @Log(title = "积分商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointsCategory pointsCategory)
    {
        List<PointsCategory> list = pointsCategoryService.selectPointsCategoryList(pointsCategory);
        ExcelUtil<PointsCategory> util = new ExcelUtil<PointsCategory>(PointsCategory.class);
        util.exportExcel(response, list, "积分商品分类数据");
    }

    /**
     * 获取积分商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:query')")
    @GetMapping(value = "/{pointsCategoryId}")
    public AjaxResult getInfo(@PathVariable("pointsCategoryId") Long pointsCategoryId)
    {
        return success(pointsCategoryService.selectPointsCategoryByPointsCategoryId(pointsCategoryId));
    }

    /**
     * 新增积分商品分类
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:add')")
    @Log(title = "积分商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsCategory pointsCategory)
    {
        return toAjax(pointsCategoryService.insertPointsCategory(pointsCategory));
    }

    /**
     * 修改积分商品分类
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:edit')")
    @Log(title = "积分商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsCategory pointsCategory)
    {
        return toAjax(pointsCategoryService.updatePointsCategory(pointsCategory));
    }

    /**
     * 删除积分商品分类
     */
    @PreAuthorize("@ss.hasPermi('point_category:point_category:remove')")
    @Log(title = "积分商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pointsCategoryIds}")
    public AjaxResult remove(@PathVariable Long[] pointsCategoryIds)
    {
        return toAjax(pointsCategoryService.deletePointsCategoryByPointsCategoryIds(pointsCategoryIds));
    }
}
