package com.arllain.UserRegistrationSystem.service;
/*
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.arllain.UserRegistrationSystem.dto.UserInfo;
import com.arllain.UserRegistrationSystem.repository.UserInfoJpaRepository;

public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoJpaRepository userInfoJpaRepository;
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo user = userInfoJpaRepository.findByUserName(userName);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado com o nome: " + userName);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities(UserInfo user){
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
	}

}*/
