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
import com.sky.system.domain.Email;
import com.sky.system.service.IEmailService;
import com.sky.common.utils.poi.ExcelUtil;
import com.sky.common.core.page.TableDataInfo;

/**
 * 售后邮件数据库Controller
 * 
 * @author yeboyu
 * @date 2024-11-24
 */
@RestController
@RequestMapping("/emile/email")
public class EmailController extends BaseController
{
    @Autowired
    private IEmailService emailService;

    /**
     * 查询售后邮件数据库列表
     */
    @PreAuthorize("@ss.hasPermi('emile:email:list')")
    @GetMapping("/list")
    public TableDataInfo list(Email email)
    {
        startPage();
        List<Email> list = emailService.selectEmailList(email);
        return getDataTable(list);
    }

    /**
     * 导出售后邮件数据库列表
     */
    @PreAuthorize("@ss.hasPermi('emile:email:export')")
    @Log(title = "售后邮件数据库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Email email)
    {
        List<Email> list = emailService.selectEmailList(email);
        ExcelUtil<Email> util = new ExcelUtil<Email>(Email.class);
        util.exportExcel(response, list, "售后邮件数据库数据");
    }

    /**
     * 获取售后邮件数据库详细信息
     */
    @PreAuthorize("@ss.hasPermi('emile:email:query')")
    @GetMapping(value = "/{emailId}")
    public AjaxResult getInfo(@PathVariable("emailId") Long emailId)
    {
        return success(emailService.selectEmailByEmailId(emailId));
    }

    /**
     * 新增售后邮件数据库
     */
    @PreAuthorize("@ss.hasPermi('emile" +
            ":email:add')")
    @Log(title = "售后邮件数据库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Email email)
    {
        return toAjax(emailService.insertEmail(email));
    }

    /**
     * 修改售后邮件数据库
     */
    @PreAuthorize("@ss.hasPermi('emile:email:edit')")
    @Log(title = "售后邮件数据库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Email email)
    {
        return toAjax(emailService.updateEmail(email));
    }

    /**
     * 删除售后邮件数据库
     */
    @PreAuthorize("@ss.hasPermi('emile:email:remove')")
    @Log(title = "售后邮件数据库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emailIds}")
    public AjaxResult remove(@PathVariable Long[] emailIds)
    {
        return toAjax(emailService.deleteEmailByEmailIds(emailIds));
    }
}
