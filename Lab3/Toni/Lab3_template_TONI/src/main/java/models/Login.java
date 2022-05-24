package models;

public class Login {

	private String mail = "";
	private String pwd = "";
	private int[] error = {0};
	
	public String getMail(){
		return mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void SetPwd(String pwd) {
		this.pwd=pwd;
	}
	
	public int[] getError() {
		return error;
	}
	
	public boolean isComplete() {
	    return(hasValue(getMail()));
	}
	
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	

	
}