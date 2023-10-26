//package com.project.moviebooking.security;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.feemanager.security.model;
//
//import com.daynilgroup.feemanager.constants.LocaleEnum;
//import com.daynilgroup.feemanager.constants.RoleTypeEnum;
//import com.daynilgroup.feemanager.constants.UserTypeEnum;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.FieldDefaults;
//
///**
// *
// * @author bhavesh
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class CustomUserDetail implements UserDetails {
//
//	static final long serialVersionUID = 1L;
//
//	Long id;
//
//	String name;
//
//	String emailId;
//
//	String username;
//
//	String password;
//
//	Long orgId;
//
//	RoleTypeEnum roleTypeEnum;
//
//	LocaleEnum locale;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> auth = new ArrayList<>();
//		auth.add(new SimpleGrantedAuthority(roleTypeEnum.name()));
//		return auth;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
//
//public class fdfadafs {
//
//}
