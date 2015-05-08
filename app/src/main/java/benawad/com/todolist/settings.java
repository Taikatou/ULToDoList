package benawad.com.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int defaultValue = 0;
        long alpha;
        alpha = sharedPref.getInt("isAlpha", defaultValue);


        radioGroup = (RadioGroup) findViewById(R.id.radio);

        if(alpha == 1)
        {
            radioGroup.check(R.id.cronological_radioButton);
        }
        else
        {
            radioGroup.check(R.id.alphabetical_radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.alphabetical_radioButton) {

                    Toast.makeText(getApplicationContext(), "Alphabetically",

                            Toast.LENGTH_SHORT).show();


                }
                else {

                    Toast.makeText(getApplicationContext(), "Chronologically",

                            Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public void not(String message)
    {

        Intent resultIntent = new Intent(this, MainActivity.class);

    // Because clicking the notification opens a new ("special") activity, there's
    // no need to create an artificial back stack.
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder n = new Notification.Builder(this)
                    .setContentTitle(message)
                    .setContentText("To do")
                    .setSmallIcon(R.drawable.ic_menu_add)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int mNotificationId = 001;

        notificationManager.notify(mNotificationId, n.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.alphabetical_radioButton:
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                if (checked) {
                    editor.putInt("previous", sharedPref.getInt("isAlpha", 1));
                    editor.putInt("isAlpha", 0);
                }
                else {
                    editor.putInt("previous", sharedPref.getInt("isAlpha", 0));
                    editor.putInt("isAlpha", 1);
                }
                editor.commit();
                    break;
            case R.id.cronological_radioButton:
                SharedPreferences sharedPrefB = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorB = sharedPrefB.edit();
                if (checked) {
                    editorB.putInt("previous", sharedPrefB.getInt("isAlpha", 0));
                    editorB.putInt("isAlpha", 1);
                }
                else {
                    editorB.putInt("previous", sharedPrefB.getInt("isAlpha", 1));
                    editorB.putInt("isAlpha", 0);
                }

                editorB.commit();
                    break;
        }
        not("Saved Preferences");
    }

    public void DeleteAll(View v)
    {
        System.exit(2);
    }
}
