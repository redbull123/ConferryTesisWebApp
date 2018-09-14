/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tesis.controller;
import org.tesis.model.Usuario;
import org.tesis.utils.Constantes;
import org.tesis.utils.SecurePassword;


/**
 *
 * @author rjsan
 */

public class LoginController {

    private final UsuarioFacade userFacade;

    public LoginController()  {
        this.userFacade = new UsuarioFacade();
            verifySuperAdmin();
    }

    public boolean checkUser(String username, String password) {

        Usuario user = userFacade.findByUsuarioname(username);
        boolean result = false;
        if (null != user) {
            if (username.equalsIgnoreCase(user.getUsuario())) {
                String pswHash = SecurePassword.getPasswordHash(password);

                if (pswHash.equals(user.getPassword())) {
                    result = true;
                }
            } 
        }
        return result;
    }
    public String checkProfile(String username){
        Usuario us = userFacade.findByUsuarioname(username);
        String perfil = us.getPerfil();
           return perfil;
        
    }
    
    private void verifySuperAdmin() {
        String user = "superadmin";
        String psw = "1234";
        boolean verified = checkUser(user, psw);
        if(!verified){
            System.out.println("Llego a crear usuario con " + user + " y " + psw);
            Usuario usuario = new Usuario();
            usuario.setUsuario(user);
            String pswHash = SecurePassword.getPasswordHash(psw);
            usuario.setPassword(pswHash);
            usuario.setStatus(Constantes.STATUS_ACTIVO);
            usuario.setPerfil(Constantes.PERFIL_SUPERADMIN);
            createUser(usuario);
            userFacade.create(usuario);
            
        }    
    }

    private void createUser(Usuario user) {
        try {
            System.out.println("Llego con " + user.getUsuario() 
                    + " " + user.getPerfil() 
                    + " " + user.getStatus()
                    + " " + user.getPassword());
            userFacade.create(user);
        } catch (Exception e) {
            System.err.println("Error creando usuario: " + e);
        }
    }
}
