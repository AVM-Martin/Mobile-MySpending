package id.my.avmmartin.myspending.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.avmmartin.myspending.MySpending;
import id.my.avmmartin.myspending.R;
import id.my.avmmartin.myspending.database.Spendings;
import id.my.avmmartin.myspending.model.Spending;

public class SpendingDetailsActivity extends AppCompatActivity {
    EditText et_nama;
    EditText et_nominal;
    Button btn_submit;
    Spendings spendings_db;
    int spending_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_spending);
        super.onCreate(savedInstanceState);

        init_components();
        load_data();
        set_events();
    }

    private void init_components() {
        et_nama = findViewById(R.id.addspending_et_nama);
        et_nominal = findViewById(R.id.addspending_et_nominal);
        btn_submit = findViewById(R.id.addspending_btn_submit);
        spendings_db = new Spendings(this);
        spending_id = getIntent().getIntExtra(MySpending.INTENT_SPENDING_ID, MySpending.NEW_SPENDING_ID);
    }

    private void load_data() {
        if (spending_id != MySpending.NEW_SPENDING_ID) {
            Spending spending = spendings_db.get_spending_by_id(spending_id);

            et_nama.setText(spending.get_nama());
            et_nominal.setText(spending.get_nominal());
        }
    }

    private void set_events() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_submit_onclick();
            }
        });
    }

    private void btn_submit_onclick() {
        try {
            String nama = et_nama.getText().toString().trim();
            String nominal = et_nominal.getText().toString().trim();

            Spending spending;
            if (spending_id == MySpending.NEW_SPENDING_ID) {
                spending = new Spending(nama, nominal);
            } else {
                spending = spendings_db.get_spending_by_id(spending_id);
                spending.set_nama(nama);
                spending.set_nominal(nominal);
            }

            spendings_db.insert_spending(spending);

            finish();
        } catch (Exception e) {
            Toast.makeText(SpendingDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
