package com.example.babyneeds;

import android.os.Bundle;

import com.example.babyneeds.data.DataBaseHandler;
import com.example.babyneeds.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText enter_item;
    private EditText size;
    private EditText color;
    private EditText qty;
    private Button saveButton;
    private DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });

        db = new DataBaseHandler(MainActivity.this);

    }

    private void saveItem(View view) {

        Item item = new Item();
        item.setItemName(enter_item.getText().toString());
        item.setItemSize(Integer.parseInt(size.getText().toString()));
        item.setItemColor(color.getText().toString().trim());
        item.setItemQty(Integer.parseInt(qty.getText().toString()));

        db.AddItem(item);


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

    private void createPopupDialog() {

        builder = new AlertDialog.Builder(this);// it will act like a popup
        View view = getLayoutInflater().inflate(R.layout.popup_layout,null);

        enter_item = view.findViewById(R.id.item);
        qty = view.findViewById(R.id.qty);
        color = view.findViewById(R.id.color);
        size = view.findViewById(R.id.size);

        saveButton = view.findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!enter_item.getText().toString().isEmpty()
                        && !color.getText().toString().isEmpty()
                        && !size.getText().toString().isEmpty()
                        && !qty.getText().toString().isEmpty()){
                    saveItem(view);
                    Snackbar.make(view,"Item Saved", Snackbar.LENGTH_SHORT)
                            .show();
                }else {
                    Snackbar.make(view, "All Items Needed ", Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });
        builder.setView(view);
        dialog = builder.create();
        dialog.show();



    }
}