package cn.shaolingweb.demo.model;

public class PhoneNumber {
	private String countryCode;
	private String stateCode;
	private String number;
	
	public PhoneNumber() {
	}
	public PhoneNumber(String fromString) {
		if (fromString!=null) {
			String [] arr=fromString.split("-");
			if (arr!=null) {
				if (arr.length>0) {
					setCountryCode(arr[0]);
				}
				if (arr.length>1) {
					setStateCode(arr[1]);
				}
				if (arr.length>2) {
					setNumber(arr[2]);
				}
			}
		}
	}
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return getCountryCode()+"-"+getStateCode()+"-"+getNumber();
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}