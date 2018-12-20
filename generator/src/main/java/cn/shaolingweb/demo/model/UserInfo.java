package cn.shaolingweb.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserInfo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String username;
private String password;
private String usertype;
private String realname;
private String qq;
private String email;
private String address;
private String tel;
private PhoneNumber phoneNumber;

/**
 * @return the phoneNumber
 */
public PhoneNumber getPhoneNumber() {
	return phoneNumber;
}
/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(PhoneNumber phoneNumber) {
	this.phoneNumber = phoneNumber;
}
/**
 * @return the id
 */
public Integer getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(Integer id) {
	this.id = id;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the usertype
 */
public String getUsertype() {
	return usertype;
}
/**
 * @param usertype the usertype to set
 */
public void setUsertype(String usertype) {
	this.usertype = usertype;
}
/**
 * @return the realname
 */
public String getRealname() {
	return realname;
}
/**
 * @param realname the realname to set
 */
public void setRealname(String realname) {
	this.realname = realname;
}
/**
 * @return the qq
 */
public String getQq() {
	return qq;
}
/**
 * @param qq the qq to set
 */
public void setQq(String qq) {
	this.qq = qq;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the address
 */
public String getAddress() {
	return address;
}
/**
 * @param address the address to set
 */
public void setAddress(String address) {
	this.address = address;
}
/**
 * @return the tel
 */
public String getTel() {
	return tel;
}
/**
 * @param tel the tel to set
 */
public void setTel(String tel) {
	this.tel = tel;
}
}