package tacos.jpa.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Table(name = "Taco_User")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String password;	
	
	private final String fullname;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String phoneNumber;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	// 返回用户被授予权限的一个集合
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 所有的用户都被授予USER权限
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	// 用户的账户是否可用或者过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 用户的账户是否被未锁定
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
		return true;
	}

}
