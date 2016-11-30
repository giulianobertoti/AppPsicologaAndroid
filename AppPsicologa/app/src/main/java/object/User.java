package object;

/**
 * Created by Victor on 30/11/2016.
 */

public class User {

    private String name;
    private int instCode;
    private UserType type;
    private int userCode;
    private String token;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getInstCode() {
        return instCode;
    }
    public void setInstCode(int instCode) {
        this.instCode = instCode;
    }
    public UserType getType() {
        return type;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public int getUserCode() {
        return userCode;
    }
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", instCode=" + instCode + ", type=" + type + ", userCode=" + userCode
                + ", token=" + token + "]";
    }


}

