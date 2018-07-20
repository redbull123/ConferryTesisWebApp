/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

//    private String user = "Juan";
//    private String pass = "1234";
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
            System.out.println("Llego a validar el usuario");
            String perfil = loginController.checkProfile(inputuser);
            switch(perfil){
                case "sa":
                    result ="menu_sa";
                    break;
                case "sv":
                    result ="menu_sv";
                    break;
                case "op":
                    result ="menu_op";
                    break;
            }

        } else {
            System.out.println("usuario y/o clave invalidos");
        }
        return result;
    }

}

