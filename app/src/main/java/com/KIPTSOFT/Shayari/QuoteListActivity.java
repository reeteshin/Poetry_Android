package com.KIPTSOFT.Shayari;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dard); // reuse existing layout
        recyclerView = findViewById(R.id.r1);

        findViewById(R.id.backbutton).setOnClickListener(v -> onBackPressed());

        String category = getIntent().getStringExtra("CATEGORY");
        if (category == null) category = "success";

        int arrayId = getResources().getIdentifier(category + "_quotes", "array", getPackageName());
        if (arrayId != 0) {
            quotes = getResources().getStringArray(arrayId);
        } else {
            quotes = new String[]{"Stay positive and work hard!", "Success is a journey, not a destination.", "Believe you can and you're halfway there."};
        }

        QuoteAdapter adapter = new QuoteAdapter(this, quotes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {
    String[] data;
    Context context;

    public QuoteAdapter(Context c, String[] data) {
        this.context = c;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(data[position]);
        holder.whatsapp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            intent.putExtra(Intent.EXTRA_TEXT, data[position]);
            try {
                context.startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(context, "Whatsapp Not Installed", Toast.LENGTH_SHORT).show();
            }
        });

        holder.button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, data[position]);
            context.startActivity(intent);
        });

        holder.textView2.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("quote", data[position]);
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
            }
        });
        
        holder.c1.setOnClickListener(v -> {
            Intent intent = new Intent(context, Allinone.class);
            intent.putExtra("tit", data[position]);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView textView2, button, whatsapp, c1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.a1);
            button = itemView.findViewById(R.id.share1);
            textView2 = itemView.findViewById(R.id.copy1);
            whatsapp = itemView.findViewById(R.id.whatsapp1);
            c1 = itemView.findViewById(R.id.c1);
        }
    }
}
