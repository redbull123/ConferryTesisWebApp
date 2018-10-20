package org.tesis.controller;

import org.tesis.model.Usuario;
import org.tesis.utils.Constantes;
import org.tesis.utils.SecurePassword;

public class LoginController {

    private final UsuarioFacade userFacade;

    public LoginController()  {
        this.userFacade = new UsuarioFacade();
            verifySuperAdmin();
    }

    public boolean checkUser(String username, String password) {
        Usuario user = userFacade.findByUsuario(username);
        boolean result = false;
        if (null != user) {
            if (username.equals(user.getUsuario())) {
                String pswHash = SecurePassword.getPasswordHash(password);
                if (pswHash.equals(user.getPassword())) {
                    result = true;
                }
            } 
        }
        return result;
    }
    public String checkProfile(String username){
        Usuario us = userFacade.findByUsuario(username);
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
            usuario.setPassword(psw);
            usuario.setStatus(Constantes.STATUS_ACTIVO);
            usuario.setPerfil(Constantes.PERFIL_SUPERADMIN);
            System.out.println(usuario.getPassword());
            createUser(usuario);   
        }    
    }

    private void createUser(Usuario user) {
        try {
            userFacade.create(user);
        } catch (Exception e) {
            System.err.println("Error creando usuario: " + e);
        }
    }
}