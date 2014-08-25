package com.beacon.tester;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.view.View.OnClickListener;

public class RecordSound extends Activity {

    final Context context = this;
    RecordSource source;

    private OnClickListener createListener(final RecordSource rs) {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                source = rs;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_sound);

        source = RecordSource.NOT_SELECTED;

        RadioButton built_in_button = (RadioButton) findViewById(R.id.built_in);
        built_in_button.setOnClickListener(createListener(RecordSource.BUILT_IN));

        RadioButton usb_button = (RadioButton) findViewById(R.id.usb);
        usb_button.setOnClickListener(createListener(RecordSource.USB));

        RadioButton bluetooth_button = (RadioButton) findViewById(R.id.bluetooth);
        bluetooth_button.setOnClickListener(createListener(RecordSource.BLUETOOTH));

        Button bt = (Button) findViewById(R.id.recordButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                if (source == RecordSource.NOT_SELECTED) {
                    alert.setMessage("Please select a recording source");
                } else {
                    String title = "Recording from ";

                    if (source == RecordSource.BUILT_IN) title += "built_in recorder";
                    else if (source == RecordSource.USB) title += "usb recorder";
                    else if (source == RecordSource.BLUETOOTH) title += "bluetooth recorder";

                    alert.setTitle(title);
                    alert.setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                }
                alert.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.record_sound, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
