package tw.edu.au.csie.listdatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    SQLiteDatabase db;
    ArrayList<String> list;
    Cursor cc;

    ArrayList<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv_db);

        db = openOrCreateDatabase("mydb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS schedule (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "event TEXT NOT NULL," +
                "datetime TEXT NOT NULL)");
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<String>();
        eventList = new ArrayList<Event>();

        cc = db.rawQuery("SELECT * FROM schedule",null);
        cc.moveToFirst();
        while(!cc.isAfterLast()){
            int id = cc.getInt(0);
            String event = cc.getString(1);
            String date = cc.getString(2);

            eventList.add(new Event(id, event, date));
            String content = event + "@" + date;
            list.add(content);
            cc.moveToNext();
        }
        ListAdapter adaptor = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        lv.setAdapter(adaptor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("ACTION", "INSERT");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
