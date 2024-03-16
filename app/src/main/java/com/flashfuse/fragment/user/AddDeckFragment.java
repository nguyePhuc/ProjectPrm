package com.flashfuse.fragment.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.flashfuse.R;
import com.flashfuse.activity.card.CreateCardActivity;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Deck;

public class AddDeckFragment extends Fragment {

    EditText etDeckName, etDeckDescription;
    Button btnAddDeck;

    DeckDAO deckDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_deck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etDeckName = view.findViewById(R.id.et_deck_name);
        etDeckDescription = view.findViewById(R.id.et_deck_description);
        btnAddDeck= view.findViewById(R.id.btn_add_deck);
        deckDAO= new DeckDAO(getContext());

        btnAddDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deckName = etDeckName.getText().toString();
                String deckDescription = etDeckDescription.getText().toString();
                //TODO: get user id from shared preference
                Deck deck= new Deck(deckName, deckDescription, 1, 0);
                long id= deckDAO.insertDeck(deck);
                //how to get deck id after insert

                Intent intent= new Intent(getActivity(), CreateCardActivity.class);
                intent.putExtra("DECK_ID", id);
                startActivity(intent);
            }
        });

    }
}