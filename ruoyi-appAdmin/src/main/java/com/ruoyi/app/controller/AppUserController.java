package com.ruoyi.app.controller;

import cn.hutool.crypto.SecureUtil;
import com.ruoyi.app.domain.AppUser;
import com.ruoyi.app.service.IAppUserService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * application 用户Controller
 *
 * @author huqinjun
 * @date 2020-06-15
 */
@Controller
@RequestMapping("/app/user")
public class AppUserController extends BaseController
{
    private String prefix = "app/user";

    @Autowired
    private IAppUserService appUserService;

    @RequiresPermissions("app:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询application 用户列表
     */
    @RequiresPermissions("app:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppUser appUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    /**
     * 导出application 用户列表
     */
    @RequiresPermissions("app:user:export")
    @Log(title = "application 用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppUser appUser)
    {
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        ExcelUtil<AppUser> util = new ExcelUtil<AppUser>(AppUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增application 用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存application 用户
     */
    @RequiresPermissions("app:user:add")
    @Log(title = "application 用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppUser appUser)
    {
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 修改application 用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AppUser appUser = appUserService.selectAppUserById(id);
        mmap.put("appUser", appUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存application 用户
     */
    @RequiresPermissions("app:user:edit")
    @Log(title = "application 用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppUser appUser)
    {
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除application 用户
     */
    @RequiresPermissions("app:user:remove")
    @Log(title = "application 用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }


    @RequiresPermissions("app:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{id}")
    public String resetPwd(@PathVariable("id") Long userId, ModelMap mmap)
    {
        mmap.put("user", appUserService.selectAppUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("app:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(AppUser user)
    {
        user.setPassword(SecureUtil.md5(user.getPassword()));
        if (appUserService.updateAppUser(user) > 0)
        {
            return success();
        }
        return error();
    }

}