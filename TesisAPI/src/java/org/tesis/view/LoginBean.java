package org.tesis.view;

import java.io.Serializable;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.tesis.controller.LoginController;

/**
 *
 * @author rjsan
 */
@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {
    
    private final LoginController loginController;
    private String inputuser;
    private String inputpass;

    public LoginBean() throws SQLException {
        this.loginController = new LoginController();
    }
    
    public String getInputuser() {
        return inputuser;
    }

    public void setInputuser(String inputuser) {
        this.inputuser = inputuser;
    }

    public String getInputpass() {
        return inputpass;
    }

    public void setInputpass(String inputpass) {
        this.inputpass = inputpass;
    }

    public String validateUser() {
        String result = null;

        if(loginController.checkUser(inputuser, inputpass)){
            String perfil = loginController.checkProfile(inputuser);
            System.out.println("Esto vino" + perfil);
            switch(perfil){
                case "sa":
                    result ="menu/menu_sa";
                    break;
                case "sv":
                    result ="menu/menu_sv";
                    break;
                case "op":
                    result ="menu/menu_op";
                    break;
            }

        } else {
            System.out.println("usuario y/o clave invalidos");
        }
        return result;
    }

}

