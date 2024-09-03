package br.com.citrus.ticket.shared.utils;

import java.util.Random;

public class Helper {
    public static boolean isUUIDValid(String uuid){
        if(uuid == null){
            return false;
        }
        return UUIDValidator.validateUUID(uuid);
    }

    public static String generatesNumber(int tamanho) {
        String numero = "";

        for(int i = 0; i < tamanho; ++i) {
            String str = String.valueOf((new Random()).nextInt());
            if (!str.equals("0")) {
                numero = numero + str.charAt(str.length() - 1);
            } else {
                --i;
            }
        }

        return numero;
    }
}
