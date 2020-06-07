package com.beans;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.User;
import com.utils.Token;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService {
	static final long ONE_MINUTE_IN_MILLIS=60000;
    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }
    
    public Token getToken(String username,String password) {
    	System.out.println("Token del usuario");
    	Token token=null;
    	User user=User.getUser(username);
    	if(user.getPassword().equals(password)) {
    		Calendar date = Calendar.getInstance();
    		long t= date.getTimeInMillis();
    		Date expirationTime=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
    		token=new Token(username,expirationTime);
    	}
    	return token;
    }
    
    public boolean checkToken(Token token) {
    	return token.isValid();
    }
    
    public boolean createUser(String username, String email,String name,String password,String lastname,String phone) {
    	System.out.println("Servicio de persistir un usuario");
    	User user=new User();
    	user.setEmail(email);
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setName(name);
    	user.setLastname(lastname);
    	user.setPhone(phone);
    	return user.save();
    }

	public boolean updateUser(String username, String email, String name, String newPassword,String lastname,String phone) {
		System.out.println("Actualizando o modificando un usuario");
		User user=User.getUser(username);
		if(email!=null) {user.setEmail(email);}
		if(name!=null) {user.setName(name);}
		if(newPassword!=null) {user.setPassword(newPassword);}
		if(lastname!=null) {user.setLastname(lastname);}
		if(phone!=null) {user.setPhone(phone);}
		return user.save();
	}

	public boolean deleteUser(String username) {
		System.out.println("removiendo usuario");
		return User.deleteByUsername(username);
	}

	public User getUserInfo(String username, String password) {
		System.out.println("obtener información de usuario");
		User user=User.getUser(username);
		if(this.getToken(username, password)!=null) {
			return user;
		}else {
			return null;
		}
	}
    
}
