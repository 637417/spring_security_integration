package com.spring.security.integration.sys.model;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SysUser implements UserDetails, Serializable {
    
    private static final long serialVersionUID = 2743762150853538921L;
    
    /**
     * 用户编号
     */
    private Integer userId;
    
    /**
     * 用户名称
     */
    private String username;
    
    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 所属部门/科室
     */
    private String departmentId;
    
    /**
     * 职务/职称
     */
    private String function;
    
    /**
     * 状态
     */
    private Integer status = 1;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 入职时间
     */
    private String entryTime;
    
    /**
     * 学历
     */
    private String education;
    
    /**
     * 邮箱
     */
    private String mailbox;
    
    /**
     * 联系电话
     */
    private String contactTel;
    
    /**
     * 联系地址
     */
    private String contactAddress;
    
    /**
     * 紧急联系人
     */
    private String emergentContact;
    
    /**
     * 紧急联系人电话
     */
    private String emergentContactTel;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    
    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getFunction() {
        return function;
    }
    
    public void setFunction(String function) {
        this.function = function;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getEntryTime() {
        return entryTime;
    }
    
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getMailbox() {
        return mailbox;
    }
    
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
    
    public String getContactTel() {
        return contactTel;
    }
    
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    
    public String getContactAddress() {
        return contactAddress;
    }
    
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
    
    public String getEmergentContact() {
        return emergentContact;
    }
    
    public void setEmergentContact(String emergentContact) {
        this.emergentContact = emergentContact;
    }
    
    public String getEmergentContactTel() {
        return emergentContactTel;
    }
    
    public void setEmergentContactTel(String emergentContactTel) {
        this.emergentContactTel = emergentContactTel;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        if (status.compareTo(1) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof SysUser) {
            if (this.userId.equals(((SysUser)object).getUserId()))
                return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }
}