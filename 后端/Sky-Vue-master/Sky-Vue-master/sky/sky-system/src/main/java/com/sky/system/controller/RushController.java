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
import com.sky.system.domain.Rush;
import com.sky.system.service.IRushService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 今日疯抢Controller
 * 
 * @author yeboyu
 * @date 2024-11-25
 */
@RestController
@RequestMapping("/rush/rush")
public class RushController extends BaseController
{
    @Autowired
    private IRushService rushService;

    /**
     * 查询今日疯抢列表
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:list')")
    @GetMapping("/list")
    public TableDataInfo list(Rush rush)
    {
        startPage();
        List<Rush> list = rushService.selectRushList(rush);
        return getDataTable(list);
    }

    /**
     * 导出今日疯抢列表
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:export')")
    @Log(title = "今日疯抢", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rush rush)
    {
        List<Rush> list = rushService.selectRushList(rush);
        ExcelUtil<Rush> util = new ExcelUtil<Rush>(Rush.class);
        util.exportExcel(response, list, "今日疯抢数据");
    }

    /**
     * 获取今日疯抢详细信息
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rushService.selectRushById(id));
    }

    /**
     * 新增今日疯抢
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:add')")
    @Log(title = "今日疯抢", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Rush rush)
    {
        return toAjax(rushService.insertRush(rush));
    }

    /**
     * 修改今日疯抢
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:edit')")
    @Log(title = "今日疯抢", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rush rush)
    {
        return toAjax(rushService.updateRush(rush));
    }

    /**
     * 删除今日疯抢
     */
    @PreAuthorize("@ss.hasPermi('rush:rush:remove')")
    @Log(title = "今日疯抢", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rushService.deleteRushByIds(ids));
    }
}
