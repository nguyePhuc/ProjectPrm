package com.flashfuse.activity.deck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flashfuse.R;
import com.flashfuse.activity.card.CreateCardActivity;
import com.flashfuse.adapter.CardRecycleViewAdapter;
import com.flashfuse.data.dao.CardDAO;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Card;
import com.flashfuse.data.entity.Deck;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DeckDetailActivity extends AppCompatActivity {
    Deck deck;
    TextView tv_deck_name, tv_deck_description;
    List<Card> listCard;
    DeckDAO deckDAO;
    CardDAO cardDAO;
    RecyclerView cardRecyclerView;
    CardRecycleViewAdapter adapter;
    Button bt_learn;
    ImageView iv_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_detail);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("deck") != null) {
            deck = (Deck) intent.getSerializableExtra("deck");
        }

        tv_deck_name = findViewById(R.id.tv_deck_name);
        tv_deck_description = findViewById(R.id.tv_deck_description);
        cardRecyclerView = findViewById(R.id.rv_card_list);
        iv_star = findViewById(R.id.iv_star);
        bt_learn= findViewById(R.id.bt_learn_deck);

        tv_deck_name.setText(deck.getName());
        tv_deck_description.setText(deck.getDescription());

        deckDAO = new DeckDAO(this);
        cardDAO = new CardDAO(this);
        listCard = cardDAO.getCardByDeckId(deck.getId());
        adapter = new CardRecycleViewAdapter(DeckDetailActivity.this, listCard);

        cardRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        cardRecyclerView.setLayoutManager(manager);

        //star deck
        if (deck.getStar() == 0) {
            iv_star.setImageResource(R.drawable.baseline_star_border_24);
        } else {
            iv_star.setImageResource(R.drawable.baseline_star_24);
        }

        iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deck.getStar() == 0) {
                    deck.setStar(1);
                    iv_star.setImageResource(R.drawable.baseline_star_24);
                } else {
                    deck.setStar(0);
                    iv_star.setImageResource(R.drawable.baseline_star_border_24);
                }
                deckDAO.updateDeck(deck);
            }
        });

        //learn
        bt_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeckDetailActivity.this, LearnDeckActivity.class);
                intent.putExtra("deck", deck);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deck_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_edit_deck){
            Intent intent = new Intent(DeckDetailActivity.this, EditDeckActivity.class);
            intent.putExtra("deck", deck);
            startActivity(intent);
        }else if(item.getItemId()== R.id.action_delete_deck){
            //popup confirm delete
            new AlertDialog.Builder(this)
                    .setTitle("Delete Deck")
                    .setMessage("Are you sure you want to delete this deck?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            deckDAO.deleteDeck(deck.getId());
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if(item.getItemId()== R.id.action_add_card){
            Intent intent = new Intent(DeckDetailActivity.this, CreateCardActivity.class);
            intent.putExtra("DECK_ID", (long) deck.getId());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    };

    @Override
    protected void onResume() {
        super.onResume();
        listCard = cardDAO.getCardByDeckId(deck.getId());
        adapter.setList(listCard);
        //update deck name and description
        deck = deckDAO.getDeckById(deck.getId());
        tv_deck_name.setText(deck.getName());
        tv_deck_description.setText(deck.getDescription());
        adapter.notifyDataSetChanged();
    }
}