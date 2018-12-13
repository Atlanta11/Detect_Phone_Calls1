package enterprise.sample.com.detect_phone_calls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class InterceptCall extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {

            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    System.out.println("incomingNumber : "+incomingNumber);
                    Toast.makeText(context, "incomingNumber : "+incomingNumber, Toast.LENGTH_SHORT).show();
                }
            },PhoneStateListener.LISTEN_CALL_STATE);



            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, "Ringing!", Toast.LENGTH_SHORT).show();
            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Toast.makeText(context, "Received!", Toast.LENGTH_SHORT).show();
            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Idle!", Toast.LENGTH_SHORT).show();
            }
            

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
