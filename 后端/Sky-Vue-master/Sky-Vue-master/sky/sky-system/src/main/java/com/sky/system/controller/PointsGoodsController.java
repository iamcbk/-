package com.sky.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.sky.system.service.ICategoryService;
import com.sky.system.service.IPointsCategoryService;
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
import com.sky.system.domain.PointsGoods;
import com.sky.system.service.IPointsGoodsService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 积分商品Controller
 * 
 * @author yeboyu
 * @date 2024-11-26
 */
@RestController
@RequestMapping("/piont_goods/piont_goods")
public class PointsGoodsController extends BaseController
{
    @Autowired
    private IPointsGoodsService pointsGoodsService;
    @Autowired
    private IPointsCategoryService PointsCategoryService;

    /**
     * 查询积分商品列表
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(PointsGoods pointsGoods)
    {
        startPage();
        List<PointsGoods> list = pointsGoodsService.selectPointsGoodsList(pointsGoods);


        return getDataTable(list);
    }

    /**
     * 导出积分商品列表
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:export')")
    @Log(title = "积分商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointsGoods pointsGoods)
    {
        List<PointsGoods> list = pointsGoodsService.selectPointsGoodsList(pointsGoods);
        ExcelUtil<PointsGoods> util = new ExcelUtil<PointsGoods>(PointsGoods.class);
        util.exportExcel(response, list, "积分商品数据");
    }

    /**
     * 获取积分商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:query')")
    @GetMapping(value = "/{pointsGoodId}")
    public AjaxResult getInfo(@PathVariable("pointsGoodId") Long pointsGoodId)
    {
        return success(pointsGoodsService.selectPointsGoodsByPointsGoodId(pointsGoodId));
    }

    /**
     * 新增积分商品
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:add')")
    @Log(title = "积分商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsGoods pointsGoods)
    {
        return toAjax(pointsGoodsService.insertPointsGoods(pointsGoods));
    }

    /**
     * 修改积分商品
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:edit')")
    @Log(title = "积分商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsGoods pointsGoods)
    {
        return toAjax(pointsGoodsService.updatePointsGoods(pointsGoods));
    }

    /**
     * 删除积分商品
     */
    @PreAuthorize("@ss.hasPermi('piont_goods:piont_goods:remove')")
    @Log(title = "积分商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pointsGoodIds}")
    public AjaxResult remove(@PathVariable Long[] pointsGoodIds)
    {
        return toAjax(pointsGoodsService.deletePointsGoodsByPointsGoodIds(pointsGoodIds));
    }
}
