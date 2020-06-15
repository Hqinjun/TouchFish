package com.ruoyi.app.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.app.mapper.AppUserMapper;
import com.ruoyi.app.domain.AppUser;
import com.ruoyi.app.service.IAppUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * application 用户Service业务层处理
 *
 * @author huqinjun
 * @date 2020-06-15
 */
@Service
public class AppUserServiceImpl implements IAppUserService
{
    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 查询application 用户
     *
     * @param id application 用户ID
     * @return application 用户
     */
    @Override
    public AppUser selectAppUserById(Long id)
    {
        return appUserMapper.selectAppUserById(id);
    }

    /**
     * 查询application 用户列表
     *
     * @param appUser application 用户
     * @return application 用户
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return appUserMapper.selectAppUserList(appUser);
    }

    /**
     * 新增application 用户
     *
     * @param appUser application 用户
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        appUser.setCreateTime(DateUtils.getNowDate());
        return appUserMapper.insertAppUser(appUser);
    }

    /**
     * 修改application 用户
     *
     * @param appUser application 用户
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        appUser.setUpdateTime(DateUtils.getNowDate());
        return appUserMapper.updateAppUser(appUser);
    }

    /**
     * 删除application 用户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppUserByIds(String ids)
    {
        return appUserMapper.deleteAppUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除application 用户信息
     *
     * @param id application 用户ID
     * @return 结果
     */
    @Override
    public int deleteAppUserById(Long id)
    {
        return appUserMapper.deleteAppUserById(id);
    }
}