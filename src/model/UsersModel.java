package model;

public class UsersModel {
    private String UserID;
    private String name;
    private String gender;
    private String age;

    public UsersModel(String userID, String name, String gender, String age) {
        UserID = userID;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

	@Override
	public String toString() {
		return "ID : " + UserID + ", Name :" + name + ", Gender :" + gender + ", Age :" + age ;
	}
    
    
}
