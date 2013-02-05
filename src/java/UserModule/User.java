/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModule;

import java.util.UUID;

/**
 *
 * @author Quicksort
 */
public class User {
    
    private String id;
    private String displayName;
    private String email;
    private String password;
    private String token;
    
    public String getId() {
	UUID t = UUID.randomUUID();
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }
}
