/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;

import java.util.Date;

/**
 *
 * @author nicolas
 */
public abstract class Artist extends Contestant{    
    private String email;

    public Artist(String name) {
        super(name);        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getFormationDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
