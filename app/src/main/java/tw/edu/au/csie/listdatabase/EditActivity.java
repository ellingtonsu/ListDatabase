package tw.edu.au.csie.listdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button bt_ok;
    EditText et_event;
    EditText et_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        String action = getIntent().getStringExtra("ACTION");
        Toast.makeText(this, "ACTION = " + action, Toast.LENGTH_LONG).show();

        et_event = (EditText)findViewById(R.id.et_event);
        et_date = (EditText)findViewById(R.id.et_date);
        bt_ok = (Button)findViewById(R.id.bt_update);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
                db.execSQL("INSERT INTO schedule (event, datetime) VALUES (\'"
                        + et_event.getText().toString()
                        + "\',\'"
                        + et_date.getText().toString() + "\')");
                finish();
            }
        });
    }
}






