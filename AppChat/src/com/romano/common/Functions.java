/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Leonardo Camargo
 */
public class Functions {
    
     /**
     * Método que formata a data e hora passada por parametro e retorna um String formatado
     * @param date
     * Data que será formatada
     * @return 
     * Retorna a data atual fomatada
     */
    public static String getFormatDate(Date date){
        Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
        if (date == null)
            date = new Date();
        return formatter.format(date);
    }
    
    /**
     * Método que formata a data e hora atual para ser exibida junto com a mensagem.
     * @return 
     * Retorna a data atual fomatada
     */
    public static String getFormatDate(){
        Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss");        
        return formatter.format(new Date());
    }
    
}
