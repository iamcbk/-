package com.sky.system.mapper;

import java.util.List;
import com.sky.system.domain.Employee;

/**
 * 员工业绩Mapper接口
 * 
 * @author yeboyu
 * @date 2024-11-27
 */
public interface EmployeeMapper 
{
    /**
     * 查询员工业绩
     * 
     * @param employeeId 员工业绩主键
     * @return 员工业绩
     */
    public Employee selectEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工业绩列表
     * 
     * @param employee 员工业绩
     * @return 员工业绩集合
     */
    public List<Employee> selectEmployeeList(Employee employee);

    /**
     * 新增员工业绩
     * 
     * @param employee 员工业绩
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改员工业绩
     * 
     * @param employee 员工业绩
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 删除员工业绩
     * 
     * @param employeeId 员工业绩主键
     * @return 结果
     */
    public int deleteEmployeeByEmployeeId(Long employeeId);

    /**
     * 批量删除员工业绩
     * 
     * @param employeeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmployeeByEmployeeIds(Long[] employeeIds);
}
