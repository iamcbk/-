package com.sky.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.system.mapper.EmailMapper;
import com.sky.system.domain.Email;
import com.sky.system.service.IEmailService;

/**
 * 售后邮件数据库Service业务层处理
 * 
 * @author yeboyu
 * @date 2024-11-24
 */
@Service
public class EmailServiceImpl implements IEmailService 
{
    @Autowired
    private EmailMapper emailMapper;

    /**
     * 查询售后邮件数据库
     * 
     * @param emailId 售后邮件数据库主键
     * @return 售后邮件数据库
     */
    @Override
    public Email selectEmailByEmailId(Long emailId)
    {
        return emailMapper.selectEmailByEmailId(emailId);
    }

    /**
     * 查询售后邮件数据库列表
     * 
     * @param email 售后邮件数据库
     * @return 售后邮件数据库
     */
    @Override
    public List<Email> selectEmailList(Email email)
    {
        return emailMapper.selectEmailList(email);
    }

    /**
     * 新增售后邮件数据库
     * 
     * @param email 售后邮件数据库
     * @return 结果
     */
    @Override
    public int insertEmail(Email email)
    {
        return emailMapper.insertEmail(email);
    }

    /**
     * 修改售后邮件数据库
     * 
     * @param email 售后邮件数据库
     * @return 结果
     */
    @Override
    public int updateEmail(Email email)
    {
        return emailMapper.updateEmail(email);
    }

    /**
     * 批量删除售后邮件数据库
     * 
     * @param emailIds 需要删除的售后邮件数据库主键
     * @return 结果
     */
    @Override
    public int deleteEmailByEmailIds(Long[] emailIds)
    {
        return emailMapper.deleteEmailByEmailIds(emailIds);
    }

    /**
     * 删除售后邮件数据库信息
     * 
     * @param emailId 售后邮件数据库主键
     * @return 结果
     */
    @Override
    public int deleteEmailByEmailId(Long emailId)
    {
        return emailMapper.deleteEmailByEmailId(emailId);
    }
}
