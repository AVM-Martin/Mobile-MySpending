package id.my.avmmartin.myspending.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import id.my.avmmartin.myspending.R;

public class SpendingListActivity extends AppCompatActivity {
    RecyclerView rv_spendings;
    SpendingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_spending_list);
        super.onCreate(savedInstanceState);

        init_components();
        load_data();
    }

    private void init_components() {
        rv_spendings = findViewById(R.id.spendinglist_rv_spendings);
        adapter = new SpendingListAdapter(this);
    }

    private void load_data() {
        rv_spendings.setLayoutManager(new LinearLayoutManager(this));
        rv_spendings.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_addspending) {
            Intent intent = new Intent(this, SpendingDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
