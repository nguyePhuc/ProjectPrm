package com.flashfuse.activity.card;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flashfuse.R;
import com.flashfuse.adapter.CardRecycleViewAdapter;
import com.flashfuse.data.dao.CardDAO;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Card;
import com.flashfuse.data.entity.Deck;

public class CreateCardActivity extends AppCompatActivity {
    TextView tv_deck_name;

    EditText et_card_front, et_card_back;
    Button btn_create_card, btn_done;
    RecyclerView card_recycle_view;
    CardDAO cardDAO;
    CardRecycleViewAdapter adapter;
    long deckId = 0;
    DeckDAO deckDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        tv_deck_name = findViewById(R.id.tv_deck_name);
        et_card_front = findViewById(R.id.et_card_front);
        et_card_back = findViewById(R.id.et_card_back);
        btn_create_card = findViewById(R.id.btn_create_card);
        btn_done = findViewById(R.id.btn_done);
        card_recycle_view = findViewById(R.id.rcCard);
        cardDAO = new CardDAO(this);
        deckDAO= new DeckDAO(this);

        Intent intent= getIntent();
        if(intent.hasExtra("DECK_ID")){
            deckId= intent.getLongExtra("DECK_ID", 0);
        }

        Deck deck= deckDAO.getDeckById((int) deckId);

        tv_deck_name.setText(deck.getName());

        adapter = new CardRecycleViewAdapter(this, cardDAO.getCardByDeckId((int)deckId));
        LinearLayoutManager manager= new LinearLayoutManager(CreateCardActivity.this, RecyclerView.VERTICAL, false);
        card_recycle_view.setLayoutManager(manager);
        card_recycle_view.setAdapter(adapter);

        btn_create_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String front = et_card_front.getText().toString();
                String back = et_card_back.getText().toString();
                cardDAO.insertCard(new Card(front, back, (int) deckId));
                adapter = new CardRecycleViewAdapter(CreateCardActivity.this, cardDAO.getCardByDeckId((int)deckId));
                card_recycle_view.setAdapter(adapter);
                et_card_front.setText("");
                et_card_back.setText("");
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}