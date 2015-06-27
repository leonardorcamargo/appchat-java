/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.common;

import com.romano.dao.UserDAO;
import com.romano.model.User;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Leonardo Camargo
 */
public class Functions {

    /**
     * Método que formata a data e hora passada por parametro e retorna um
     * String formatado
     *
     * @param date Data que será formatada
     * @return Retorna a data atual fomatada
     */
    public static String getFormatDate(Date date) {
        Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
        if (date == null) {
            date = new Date();
        }
        return formatter.format(date);
    }

    /**
     * Método que formata a data e hora atual para ser exibida junto com a
     * mensagem.
     *
     * @return Retorna a data atual fomatada
     */
    public static String getFormatDate() {
        Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
        return formatter.format(new Date());
    }

    /**
     * Método que cria uma String de segurança com base no login e passwordo do user
     * @param user
     * Usuário utilizado para criar a String de segurança   
     * @return
     * Este método retorna uma String de segurança com o login e password de um user
     * válido para o sistema
     */
    public static String getUserPass(User user) {
        return user.getLogin() + "|" + user.getPassword();
    }

    /**
     * Método que verifica se a String de segurança foi gerada por um usuário válido
     * @param userPass
     * String de segurança que será verificada pela aplicação
     * @return
     * Retorno do método, sendo true caso o usuário seja válido
     */
    public static boolean getUserPassCheck(String userPass) {
        String name = userPass.substring(0, userPass.indexOf("|"));
        String pass = userPass.substring(userPass.indexOf("|"), userPass.length());

        UserDAO userDao = new UserDAO();
        return userDao.getUser(name, pass) != null;
    }

}
