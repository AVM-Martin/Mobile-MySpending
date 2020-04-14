package id.my.avmmartin.myspending.model;

import android.content.ContentValues;
import android.database.Cursor;

import id.my.avmmartin.myspending.database.Spendings;
import id.my.avmmartin.myspending.MySpending;

public class Spending {
    private static final String ID = Spendings.ID;
    private static final String NAMA = Spendings.NAMA;
    private static final String NOMINAL = Spendings.NOMINAL;
    private static final String TANGGAL = Spendings.TANGGAL;
    private static final int NEW_SPENDING_ID = MySpending.NEW_SPENDING_ID;

    private int id;
    private String nama;
    private int nominal;
    private String tanggal;

    public Spending(String _nama, String _nominal) throws Exception {
        id = NEW_SPENDING_ID;
        set_nama(_nama);
        set_nominal(_nominal);
        tanggal = MySpending.get_today_date();
    }

    public Spending(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(ID));
        nama = cursor.getString(cursor.getColumnIndex(NAMA));
        nominal = cursor.getInt(cursor.getColumnIndex(NOMINAL));
        tanggal = cursor.getString(cursor.getColumnIndex(TANGGAL));
    }

    public ContentValues to_content_values() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAMA, nama);
        contentValues.put(NOMINAL, nominal);
        contentValues.put(TANGGAL, tanggal);

        return contentValues;
    }

    public boolean is_new_spending() {
        return id == NEW_SPENDING_ID;
    }

    public int get_id() {
        return id;
    }

    public String get_nama() {
        return nama;
    }

    public String get_nominal() {
        return Integer.toString(nominal);
    }

    public String get_formatted_nominal() {
        return "Rp. " + get_nominal();
    }

    public String get_tanggal() {
        return tanggal;
    }

    public void set_nama(String _nama) throws Exception {
        if (_nama.equals("")) {
            throw new Exception("Nama harus diisi");
        } else {
            nama = _nama;
        }
    }

    public void set_nominal(String _nominal) throws Exception {
        if (_nominal.equals("")) {
            throw new Exception("Nominal harus diisi");
        } else if (!MySpending.is_valid_number(_nominal)) {
            throw new Exception("Nominal harus angka");
        } else {
            nominal = Integer.parseInt(_nominal);
        }
    }
}
