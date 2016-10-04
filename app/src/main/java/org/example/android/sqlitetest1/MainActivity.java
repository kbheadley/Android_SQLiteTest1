package org.example.android.sqlitetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("DROP TABLE contacts");
        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('tim', 6456789,'tim@email.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Fred', 12345,'fred@email.com');");
        Cursor query = sqLiteDatabase.rawQuery("SELECT * from contacts", null);
        if(query.moveToFirst()){
            do {
                // cycle thru all records
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getBaseContext(), "Name = " + name + " phone = " + phone + " email = " + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());
        }
            else
        {
            Toast.makeText(getBaseContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
        }
        sqLiteDatabase.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
