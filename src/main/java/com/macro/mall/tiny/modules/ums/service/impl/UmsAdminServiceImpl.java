package com.macro.mall.tiny.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.common.exception.Asserts;
import com.macro.mall.tiny.domain.AdminUserDetails;
import com.macro.mall.tiny.modules.ums.dto.UmsAdminParam;
import com.macro.mall.tiny.modules.ums.dto.UpdateAdminPasswordParam;
import com.macro.mall.tiny.modules.ums.mapper.*;
import com.macro.mall.tiny.modules.ums.model.*;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import com.macro.mall.tiny.modules.ums.service.UmsAdminRoleRelationService;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.modules.ums.service.UmsUserDepartmentRelationService;
import com.macro.mall.tiny.security.util.JwtTokenUtil;
import com.macro.mall.tiny.security.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * ?????????????????????Service?????????
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper,UmsAdmin> implements UmsAdminService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UmsAdminLoginLogMapper loginLogMapper;
  @Autowired
  private UmsAdminRoleRelationService adminRoleRelationService;
  
  @Autowired
  private UmsUserDepartmentRelationService userDepartmentRelationService;
  @Autowired
  private UmsRoleMapper roleMapper;
  
  @Autowired
  private UmsDepartmentMapper departmentMapper;
  @Autowired
  private UmsResourceMapper resourceMapper;

  @Override
  public UmsAdmin getAdminByUsername(String username) {
    UmsAdmin admin = getCacheService().getAdmin(username);
    if(admin!=null) return  admin;
    QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsAdmin::getUsername,username);
    List<UmsAdmin> adminList = list(wrapper);
    if (adminList != null && adminList.size() > 0) {
      admin = adminList.get(0);
      getCacheService().setAdmin(admin);
      return admin;
    }
    return null;
  }

  @Override
  public UmsAdmin register(UmsAdminParam umsAdminParam) {
    UmsAdmin umsAdmin = new UmsAdmin();
    BeanUtils.copyProperties(umsAdminParam, umsAdmin);
    umsAdmin.setCreateTime(new Date());
    umsAdmin.setStatus(1);
    //???????????????????????????????????????
    QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsAdmin::getUsername,umsAdmin.getUsername());
    List<UmsAdmin> umsAdminList = list(wrapper);
    if (umsAdminList.size() > 0) {
      return null;
    }
    //???????????????????????????
    String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
    umsAdmin.setPassword(encodePassword);
    baseMapper.insert(umsAdmin);
    return umsAdmin;
  }

  @Override
  public String login(String username, String password) {
    String token = null;
    //????????????????????????????????????
    try {
      UserDetails userDetails = loadUserByUsername(username);
      if(!passwordEncoder.matches(password,userDetails.getPassword())){
        Asserts.fail("???????????????");
      }
      if(!userDetails.isEnabled()){
        Asserts.fail("??????????????????");
      }
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtTokenUtil.generateToken(userDetails);
      //updateLoginTimeByUsername(username);
      insertLoginLog(username);
    } catch (AuthenticationException e) {

      LOGGER.warn("????????????:{}", e.getMessage());
    }
    return token;
  }

  /**
   * ??????????????????
   * @param username ?????????
   */
  private void insertLoginLog(String username) {
    UmsAdmin admin = getAdminByUsername(username);
    if(admin==null) return;
    UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
    loginLog.setAdminId(admin.getId());
    loginLog.setCreateTime(new Date());
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    assert attributes != null;
    HttpServletRequest request = attributes.getRequest();
    loginLog.setIp(request.getRemoteAddr());
    loginLogMapper.insert(loginLog);
  }

  /**
   * ?????????????????????????????????
   */
  private void updateLoginTimeByUsername(String username) {
    UmsAdmin record = new UmsAdmin();
    record.setLoginTime(new Date());
    QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsAdmin::getUsername,username);
    update(record,wrapper);
  }

  @Override
  public String refreshToken(String oldToken) {
    return jwtTokenUtil.refreshHeadToken(oldToken);
  }

  @Override
  public Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
    Page<UmsAdmin> page = new Page<>(pageNum,pageSize);
    QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<UmsAdmin> lambda = wrapper.lambda();
    if(StrUtil.isNotEmpty(keyword)){
      lambda.like(UmsAdmin::getUsername,keyword);
      lambda.or().like(UmsAdmin::getNickName,keyword);
    }
    return page(page,wrapper);
  }

  @Override
  public boolean update(Long id, UmsAdmin admin) {
    admin.setId(id);
    UmsAdmin rawAdmin = getById(id);
    if(rawAdmin.getPassword().equals(admin.getPassword())){
      //??????????????????????????????????????????
      admin.setPassword(null);
    }else{
      //?????????????????????????????????????????????
      if(StrUtil.isEmpty(admin.getPassword())){
        admin.setPassword(null);
      }else{
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
      }
    }
    boolean success = updateById(admin);
    getCacheService().delAdmin(id);
    return success;
  }

  @Override
  public boolean delete(Long id) {
    getCacheService().delAdmin(id);
    boolean success = removeById(id);
    getCacheService().delResourceList(id);
    return success;
  }

  @Override
  public int updateRole(Long adminId, List<Long> roleIds) {
    int count = roleIds == null ? 0 : roleIds.size();
    //????????????????????????
    QueryWrapper<UmsAdminRoleRelation> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsAdminRoleRelation::getAdminId,adminId);
    adminRoleRelationService.remove(wrapper);
    //???????????????
    if (!CollectionUtils.isEmpty(roleIds)) {
      List<UmsAdminRoleRelation> list = new ArrayList<>();
      for (Long roleId : roleIds) {
        UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
        roleRelation.setAdminId(adminId);
        roleRelation.setRoleId(roleId);
        list.add(roleRelation);
      }
      adminRoleRelationService.saveBatch(list);
    }
    getCacheService().delResourceList(adminId);
    return count;
  }

  @Override
  public List<UmsRole> getRoleList(Long adminId) {
    return roleMapper.getRoleList(adminId);
  }
  
  @Override
  public List<UmsDepartment> getDepartmentList(Long adminId) {
    return departmentMapper.getDepartmentList(adminId);
  }
  
  @Override
  public List getExamineList(Map<String, Object> params) {
    List userList;
    /*
     * 1.????????????????????????/??????????????????????????????
     * 2.?????????????????????/???????????????????????????
     */
    List roleIds = (List) params.get("roleIds");
    List examineRoles = examineRolesConfrim(roleIds);
    List departmentIds = (List) params.get("departments");
    
    //String roles = Convert.toStr(examineRoles);
    if(roleIds.contains(14)){
      userList = baseMapper.getAdminByRoleAndDepartement(examineRoles, departmentIds);
    }else{
      userList = baseMapper.getAdminByRole(examineRoles);
    }
    
    return userList;
  }
  
  private List examineRolesConfrim(List roleIds){
    ArrayList<Long> roles = new ArrayList<>();
    //??????????????????????????????????????????
    if(roleIds.contains(14)){
      roles.add(12L);
    }
    //????????????????????????????????????
    if(roleIds.contains(12)){
      roles.add(13L);
    }
    //???????????????????????????????????????
    if(roleIds.contains(13)){
      roles.add(15L);
    }
    //??????????????????????????????????????????
    if(roleIds.contains(15)){
      roles.add(11L);
    }
    //????????????????????????????????????
    if(roleIds.contains(11)){
      roles.add(10L);
    }
    //?????????????????????????????????
    if(roleIds.contains(10)){
      roles.add(9L);
    }
    if(roleIds.contains(9)){
      roles.add(0L);
    }
    return roles;
  }
  @Override
  public int updateDepartment(Long adminId, List<Long> departmentIds) {
    int count = departmentIds == null ? 0 : departmentIds.size();
    //????????????????????????
    QueryWrapper<UmsUserDepartmentRelation> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsUserDepartmentRelation::getUserId,adminId);
    userDepartmentRelationService.remove(wrapper);
    //???????????????
    if (!CollectionUtils.isEmpty(departmentIds)) {
      List<UmsUserDepartmentRelation> list = new ArrayList<>();
      for (Long departmentId : departmentIds) {
        UmsUserDepartmentRelation departmentRelation = new UmsUserDepartmentRelation();
        departmentRelation.setUserId(adminId);
        departmentRelation.setDepartmentId(departmentId);
        list.add(departmentRelation);
      }
      userDepartmentRelationService.saveBatch(list);
    }
    getCacheService().delResourceList(adminId);
    return count;
  }

  @Override
  public List<UmsResource> getResourceList(Long adminId) {
    List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
    if(CollUtil.isNotEmpty(resourceList)){
      return  resourceList;
    }
    resourceList = resourceMapper.getResourceList(adminId);
    if(CollUtil.isNotEmpty(resourceList)){
      getCacheService().setResourceList(adminId,resourceList);
    }
    return resourceList;
  }

  @Override
  public int updatePassword(UpdateAdminPasswordParam param) {
    if(StrUtil.isEmpty(param.getUsername())
        ||StrUtil.isEmpty(param.getOldPassword())
        ||StrUtil.isEmpty(param.getNewPassword())){
      return -1;
    }
    QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
    wrapper.lambda().eq(UmsAdmin::getUsername,param.getUsername());
    List<UmsAdmin> adminList = list(wrapper);
    if(CollUtil.isEmpty(adminList)){
      return -2;
    }
    UmsAdmin umsAdmin = adminList.get(0);
    if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
      return -3;
    }
    umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
    updateById(umsAdmin);
    getCacheService().delAdmin(umsAdmin.getId());
    return 1;
  }

  @Override
  public UserDetails loadUserByUsername(String username){
    //??????????????????

    UmsAdmin admin = getAdminByUsername(username);
    if (admin != null) {
      List<UmsResource> resourceList = getResourceList(admin.getId());
      return new AdminUserDetails(admin,resourceList);
    }
    throw new UsernameNotFoundException("????????????????????????");
  }

  @Override
  public UmsAdminCacheService getCacheService() {
    return SpringUtil.getBean(UmsAdminCacheService.class);
  }
}
