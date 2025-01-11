package com.sky.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 员工业绩对象 employee
 * 
 * @author yeboyu
 * @date 2024-11-27
 */
public class Employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工ID */
    private Long employeeId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String employeeName;

    /** 员工头像 */
    @Excel(name = "员工头像")
    private String employeeAvatar;

    /** 成交订单数 */
    @Excel(name = "成交订单数")
    private Long star;

    /** 业绩总额 */
    @Excel(name = "业绩总额")
    private BigDecimal salary;

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }
    public void setEmployeeAvatar(String employeeAvatar) 
    {
        this.employeeAvatar = employeeAvatar;
    }

    public String getEmployeeAvatar() 
    {
        return employeeAvatar;
    }
    public void setStar(Long star) 
    {
        this.star = star;
    }

    public Long getStar() 
    {
        return star;
    }
    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("employeeName", getEmployeeName())
            .append("employeeAvatar", getEmployeeAvatar())
            .append("star", getStar())
            .append("salary", getSalary())
            .toString();
    }
}
