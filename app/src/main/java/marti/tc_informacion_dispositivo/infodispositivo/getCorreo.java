package marti.tc_informacion_dispositivo.infodispositivo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Daniel on 07/07/2017.
 */

public class getCorreo {


    public static String getEmailDispositivo(Context context){
        String possibleEmail="example@gmail.com";
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts(); /* necesita permiso GET_ACCOUNTS */

        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;

            }
        }

        return possibleEmail;
    }
}
