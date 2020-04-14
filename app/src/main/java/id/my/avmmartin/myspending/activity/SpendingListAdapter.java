package id.my.avmmartin.myspending.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.my.avmmartin.myspending.MySpending;
import id.my.avmmartin.myspending.R;
import id.my.avmmartin.myspending.database.Spendings;
import id.my.avmmartin.myspending.model.Spending;

class SpendingListAdapter extends RecyclerView.Adapter<SpendingListAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama;
        TextView tv_nominal;
        TextView tv_tanggal;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            init_components();
            set_events();
        }

        private void init_components() {
            tv_nama = itemView.findViewById(R.id.adapter_spendinglist_tv_nama);
            tv_nominal = itemView.findViewById(R.id.adapter_spendinglist_tv_nominal);
            tv_tanggal = itemView.findViewById(R.id.adapter_spendinglist_tv_tanggal);
        }

        private void set_events() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(context, SpendingDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(MySpending.INTENT_SPENDING_ID, position + 1);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_spending_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Spending spending = spendings_db.get_spending_by_id(position + 1);

        holder.tv_nama.setText(spending.get_nama());
        holder.tv_nominal.setText(spending.get_formatted_nominal());
        holder.tv_tanggal.setText(spending.get_tanggal());
    }

    @Override
    public int getItemCount() {
        return spendings_db.get_size();
    }

    private Context context;
    private Spendings spendings_db;

    SpendingListAdapter(Context _context) {
        context = _context;
        spendings_db = new Spendings(context);
    }
}
