package id.my.avmmartin.myspending.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.my.avmmartin.myspending.MySpending;
import id.my.avmmartin.myspending.model.Spending;

public class Spendings extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "spending";
    private static final int VERSION = 1;

    public static final String ID = "id";
    public static final String NAMA = "nama";
    public static final String NOMINAL = "nominal";
    public static final String TANGGAL = "tanggal";

    public Spendings(Context context) {
        super(context, MySpending.DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAMA + " TEXT, "
            + NOMINAL + " INTEGER, "
            + TANGGAL + " TEXT"
            + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";"
            );
            onCreate(db);
        }
    }

    public Spending get_spending_by_id(int id) {
        String selection = (
            ID + " = ?"
        );
        String[] selection_args = {
            Integer.toString(id)
        };

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, selection, selection_args, null, null, null);

        cursor.moveToFirst();
        Spending result = new Spending(cursor);

        cursor.close();
        db.close();

        return result;
    }

    public void insert_spending(Spending spending) {
        SQLiteDatabase db = getWritableDatabase();

        if (spending.is_new_spending()) {
            db.insert(TABLE_NAME, null, spending.to_content_values());
        } else {
            String where_clause = (
                ID + " = ?"
            );
            String[] where_args = {
                Integer.toString(spending.get_id())
            };

            db.update(TABLE_NAME, spending.to_content_values(), where_clause, where_args);
        }

        db.close();
    }

    public int get_size() {
        SQLiteDatabase db = getReadableDatabase();
        int result = (int)DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return result;
    }
}
