package com.sky.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sky.common.annotation.Excel;
import com.sky.common.core.domain.BaseEntity;

/**
 * 售后邮件数据库对象 email
 * 
 * @author yeboyu
 * @date 2024-11-24
 */
public class Email extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邮件ID */
    private Long emailId;

    /** 发件人名字 */
    @Excel(name = "发件人名字")
    private String senderName;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱地址
 */
    @Excel(name = "邮箱地址 ")
    private String email;

    /** 邮件内容 */
    @Excel(name = "邮件内容")
    private String content;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sentTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String readStatus;

    public void setEmailId(Long emailId) 
    {
        this.emailId = emailId;
    }

    public Long getEmailId() 
    {
        return emailId;
    }
    public void setSenderName(String senderName) 
    {
        this.senderName = senderName;
    }

    public String getSenderName() 
    {
        return senderName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSentTime(Date sentTime) 
    {
        this.sentTime = sentTime;
    }

    public Date getSentTime() 
    {
        return sentTime;
    }
    public void setReadStatus(String readStatus) 
    {
        this.readStatus = readStatus;
    }

    public String getReadStatus() 
    {
        return readStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("emailId", getEmailId())
            .append("senderName", getSenderName())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("content", getContent())
            .append("sentTime", getSentTime())
            .append("readStatus", getReadStatus())
            .toString();
    }
}
