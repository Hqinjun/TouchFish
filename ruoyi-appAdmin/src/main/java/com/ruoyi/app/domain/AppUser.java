package com.ruoyi.app.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * application 用户对象 app_user
 *
 * @author huqinjun
 * @date 2020-06-15
 */
public class AppUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 登录名 */
    @Excel(name = "登录名")
    private String username;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String password;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userPhoto;

    /** 用户性别，0：男，1：女 */
    @Excel(name = "用户性别，0：男，1：女")
    private Integer userSex;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private Long accountBalance;

    /** 用户状态，0：正常 */
    @Excel(name = "用户状态，0：正常")
    private Integer status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setUserPhoto(String userPhoto)
    {
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto()
    {
        return userPhoto;
    }
    public void setUserSex(Integer userSex)
    {
        this.userSex = userSex;
    }

    public Integer getUserSex()
    {
        return userSex;
    }
    public void setAccountBalance(Long accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public Long getAccountBalance()
    {
        return accountBalance;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("password", getPassword())
                .append("phone", getPhone())
                .append("nickName", getNickName())
                .append("userPhoto", getUserPhoto())
                .append("userSex", getUserSex())
                .append("accountBalance", getAccountBalance())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}