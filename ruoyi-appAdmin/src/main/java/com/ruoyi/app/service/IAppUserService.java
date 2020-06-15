package com.ruoyi.app.service;

import java.util.List;
import com.ruoyi.app.domain.AppUser;

/**
 * application 用户Service接口
 *
 * @author huqinjun
 * @date 2020-06-15
 */
public interface IAppUserService
{
    /**
     * 查询application 用户
     *
     * @param id application 用户ID
     * @return application 用户
     */
    public AppUser selectAppUserById(Long id);

    /**
     * 查询application 用户列表
     *
     * @param appUser application 用户
     * @return application 用户集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * 新增application 用户
     *
     * @param appUser application 用户
     * @return 结果
     */
    public int insertAppUser(AppUser appUser);

    /**
     * 修改application 用户
     *
     * @param appUser application 用户
     * @return 结果
     */
    public int updateAppUser(AppUser appUser);

    /**
     * 批量删除application 用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppUserByIds(String ids);

    /**
     * 删除application 用户信息
     *
     * @param id application 用户ID
     * @return 结果
     */
    public int deleteAppUserById(Long id);
}