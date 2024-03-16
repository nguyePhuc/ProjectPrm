package com.flashfuse.activity.deck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.flashfuse.R;
import com.flashfuse.adapter.DeckFullRecycleViewAdapter;
import com.flashfuse.data.dao.DeckDAO;

public class AllDeckActivity extends AppCompatActivity {
    TextView title;
    private RecyclerView recyclerView;
    private DeckDAO deckDAO;
    private DeckFullRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_deck);

        title = findViewById(R.id.title);
        recyclerView = findViewById(R.id.rcDeck);
        deckDAO = new DeckDAO(this);

        Intent intent= getIntent();
        if(intent.getStringExtra("type")!= null){
            if(intent.getStringExtra("type").equals("all")){
                title.setText("All Decks");
                adapter= new DeckFullRecycleViewAdapter(this, deckDAO.getAll());
            }else if(intent.getStringExtra("type").equals("star")){
                title.setText("Star Decks");
                adapter= new DeckFullRecycleViewAdapter(this, deckDAO.getDeckByStar(1));
            }
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}