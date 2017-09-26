/**
  * Author: Ian Gallagher <igallagher@securityinnovation.com>
  *
  * This code utilizes jBCrypt, which you need installed to use.
  * jBCrypt: http://www.mindrot.org/projects/jBCrypt/
  */
package com.sms.spring.web.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {
	 public static String setUp() {
		 

		  String hashedPassword = BCrypt.hashpw("pass", BCrypt.gensalt());
		return hashedPassword;

		 

		
		}
	public static void main(String[] args) {
	
System.out.println(setUp());
	}

}
