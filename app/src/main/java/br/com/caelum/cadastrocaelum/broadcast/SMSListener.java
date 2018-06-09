package br.com.caelum.cadastrocaelum.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;

public class SMSListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        String format = (String) intent.getSerializableExtra("format");

        byte[] pdu = (byte[]) pdus[0];

        SmsMessage sms = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            sms = SmsMessage.createFromPdu(pdu, format);
        } else {
            sms = SmsMessage.createFromPdu(pdu);

        }

        String numero = sms.getDisplayOriginatingAddress();

        AlunoDAO alunoDAO = new AlunoDAO(context);

        boolean temAluno = alunoDAO.tenhoAlunoComEsse(numero);

        alunoDAO.close();

        if (temAluno) {

            Toast.makeText(context,
                    "Finge que tá tocando a marcha imperial",
                    Toast.LENGTH_LONG).show();


        }


    }
}
