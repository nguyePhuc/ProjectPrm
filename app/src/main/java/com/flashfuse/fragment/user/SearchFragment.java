package com.flashfuse.fragment.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.flashfuse.R;
import com.flashfuse.adapter.DeckFullRecycleViewAdapter;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Deck;

import java.util.List;

public class SearchFragment extends Fragment {
    private EditText searchDeck;
    private Button searchButton;
    private RecyclerView searchResults;
    private DeckDAO deckDAO;

    private DeckFullRecycleViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchDeck = view.findViewById(R.id.search_deck);
        searchButton = view.findViewById(R.id.search_button);
        searchResults = view.findViewById(R.id.search_results);

        deckDAO = new DeckDAO(getContext());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deckName = searchDeck.getText().toString();
                List<Deck> decks = deckDAO.getDeckByName(deckName);

                adapter= new DeckFullRecycleViewAdapter(getContext(), decks);
                searchResults.setAdapter(adapter);
                LinearLayoutManager manager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                searchResults.setLayoutManager(manager);
            }
        });
        return view;
    }
}