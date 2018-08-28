package com.coding.login.controller;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = -6148782076805864643L;
    
    @JSONField(name = "session_key")
    private String sessionKey;
    
    @JSONField(name = "verified")
    private boolean verified;
    
    @JSONField(name = "user_uid")
    private String userUid;
    
    @JSONField(name = "login_name")
    private String loginName;
    
    @JSONField(name = "phone")
    private String phone;
    
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "gender")
    private String gender;

    @JSONField(name = "permission_info")
    private Set<String> permissionInfo;

    @JSONField(name = "mall_user_uid")
    private String mallUserUid;

    @JSONField(name = "mall_vendor_uid")
    private String mallVendorUid;

    @JSONField(name = "erp_vendor_uid")
    private String erpVendorUid;

    @JSONField(name = "identity")
    private String identity;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Set<String> getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(Set<String> permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public String getMallUserUid() {
        return mallUserUid;
    }

    public void setMallUserUid(String mallUserUid) {
        this.mallUserUid = mallUserUid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMallVendorUid() {
        return mallVendorUid;
    }

    public void setMallVendorUid(String mallVendorUid) {
        this.mallVendorUid = mallVendorUid;
    }

    public String getErpVendorUid() {
        return erpVendorUid;
    }

    public void setErpVendorUid(String erpVendorUid) {
        this.erpVendorUid = erpVendorUid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
