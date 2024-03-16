package com.flashfuse.activity.deck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flashfuse.R;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Deck;

public class EditDeckActivity extends AppCompatActivity {
    EditText et_deck_name, et_deck_description;
    Button bt_save, bt_cancel;

    Deck deck;

    DeckDAO deckDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        et_deck_name = findViewById(R.id.et_deck_name);
        et_deck_description = findViewById(R.id.et_deck_description);
        bt_save = findViewById(R.id.btn_save);
        bt_cancel = findViewById(R.id.btn_cancel);
        DeckDAO deckDAO = new DeckDAO(this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("deck") != null) {
            deck = (Deck) intent.getSerializableExtra("deck");
        }

        et_deck_name.setText(deck.getName());
        et_deck_description.setText(deck.getDescription());

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save deck
                deck.setName(et_deck_name.getText().toString());
                deck.setDescription(et_deck_description.getText().toString());
                deckDAO.updateDeck(deck);
                finish();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}