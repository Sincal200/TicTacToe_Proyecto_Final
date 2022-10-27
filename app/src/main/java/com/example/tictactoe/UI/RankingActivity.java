package com.example.tictactoe.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.w3c.dom.Text;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_list);
        btnRegresar = findViewById(R.id.btnRankRegresar);

        //Query
        Query query = firebaseFirestore.collection("users").orderBy("points", Query.Direction.DESCENDING).limit(3);
        //RecyclerOptions
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query,User.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<User, userViewHolder>(options) {
            @NonNull
            @Override
            public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent,false);
                return new userViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull userViewHolder holder, int position, @NonNull User model) {
                holder.tvRankName.setText(model.getName());
                holder.tvRankPartidas.setText("Partidas Jugadas: " + model.getPatidasJugadas() + "");
                holder.tvRnakPuntos.setText("Puntos: " + model.getPoints() + "");
            }
        };

         mFirestoreList.setHasFixedSize(true);
         mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
         mFirestoreList.setAdapter(adapter);


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RankingActivity.this,FindGameActivity.class));
            }
        });
    }




    private class userViewHolder extends RecyclerView.ViewHolder{

        private TextView tvRankName;
        private TextView tvRankPartidas;
        private TextView tvRnakPuntos;


        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRankName = itemView.findViewById(R.id.textViewRankName);
            tvRankPartidas = itemView.findViewById(R.id.textViewRankPartidas);
            tvRnakPuntos = itemView.findViewById(R.id.textViewRankPoints);

        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    class CustomApdater extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            return view;
        }
    }


}