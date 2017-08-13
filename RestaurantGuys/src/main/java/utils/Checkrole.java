package utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class Checkrole {
	public String checkrole(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String leftPage = "left-restaurant";
		for (GrantedAuthority role : user.getAuthorities()) {
			if (role.getAuthority().equals("ROLE_ADMIN")) {
				leftPage = "left-admin";
				break;
			}
		}
		return leftPage;
	}
	public String PrintRole(){
		String result="";
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (GrantedAuthority role : user.getAuthorities()) {
			result = role.getAuthority();
		}
		return result;
	}
	public String PrintUser(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}
}
