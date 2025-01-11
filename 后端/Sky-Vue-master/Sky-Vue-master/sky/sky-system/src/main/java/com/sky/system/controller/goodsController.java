package com.sky.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.sky.system.service.ICategoryService;
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
import com.sky.system.domain.goods;
import com.sky.system.service.IgoodsService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author yeboyu
 * @date 2024-11-23
 */
@RestController
@RequestMapping("/goods/goods")
public class goodsController extends BaseController
{
    @Autowired
    private IgoodsService goodsService;
    @Autowired
    private ICategoryService categoryService;
    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(goods goods)
    {
        startPage();
        List<goods> list = goodsService.selectgoodsList(goods);
        for (com.sky.system.domain.goods good : list) {
            good.setCategoryName(good.getCategoryId() == null ? "" :
                    categoryService.selectCategoryByCategoryId(good.getCategoryId()).getCategoryName());
        }
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, goods goods)
    {
        List<goods> list = goodsService.selectgoodsList(goods);
        ExcelUtil<goods> util = new ExcelUtil<goods>(goods.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return success(goodsService.selectgoodsByGoodsId(goodsId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody goods goods)
    {
        return toAjax(goodsService.insertgoods(goods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody goods goods)
    {
        return toAjax(goodsService.updategoods(goods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(goodsService.deletegoodsByGoodsIds(goodsIds));
    }
}
