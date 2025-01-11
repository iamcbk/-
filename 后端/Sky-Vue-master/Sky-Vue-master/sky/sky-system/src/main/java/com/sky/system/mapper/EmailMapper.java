package com.sky.system.mapper;

import java.util.List;
import com.sky.system.domain.Email;

/**
 * 售后邮件数据库Mapper接口
 * 
 * @author yeboyu
 * @date 2024-11-24
 */
public interface EmailMapper 
{
    /**
     * 查询售后邮件数据库
     * 
     * @param emailId 售后邮件数据库主键
     * @return 售后邮件数据库
     */
    public Email selectEmailByEmailId(Long emailId);

    /**
     * 查询售后邮件数据库列表
     * 
     * @param email 售后邮件数据库
     * @return 售后邮件数据库集合
     */
    public List<Email> selectEmailList(Email email);

    /**
     * 新增售后邮件数据库
     * 
     * @param email 售后邮件数据库
     * @return 结果
     */
    public int insertEmail(Email email);

    /**
     * 修改售后邮件数据库
     * 
     * @param email 售后邮件数据库
     * @return 结果
     */
    public int updateEmail(Email email);

    /**
     * 删除售后邮件数据库
     * 
     * @param emailId 售后邮件数据库主键
     * @return 结果
     */
    public int deleteEmailByEmailId(Long emailId);

    /**
     * 批量删除售后邮件数据库
     * 
     * @param emailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmailByEmailIds(Long[] emailIds);
}
