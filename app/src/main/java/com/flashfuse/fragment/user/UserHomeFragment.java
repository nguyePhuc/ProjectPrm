package com.flashfuse.fragment.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flashfuse.R;
import com.flashfuse.activity.deck.AllDeckActivity;
import com.flashfuse.activity.deck.DeckDetailActivity;
import com.flashfuse.adapter.DeckRecycleViewAdapter;
import com.flashfuse.data.dao.DeckDAO;
import com.flashfuse.data.entity.Deck;

import java.util.List;

public class UserHomeFragment extends Fragment{
    DeckRecycleViewAdapter adapter, adapterStar;
    RecyclerView recyclerView, recyclerViewStar;
    DeckDAO deckDAO;
    List<Deck> list, listStar;
    TextView viewAllDecks, viewAllStarDecks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);
        deckDAO= new DeckDAO(this.getContext());

        recyclerView= view.findViewById(R.id.rcDeck);
        recyclerViewStar= view.findViewById(R.id.rcStarDeck);
        viewAllDecks= view.findViewById(R.id.viewAllDecks);
        viewAllStarDecks= view.findViewById(R.id.viewAllStarDecks);

        list= deckDAO.getAll();
        listStar= deckDAO.getDeckByStar(1);

        adapter= new DeckRecycleViewAdapter(this.getContext(), list);
        adapterStar= new DeckRecycleViewAdapter(this.getContext(), listStar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        recyclerViewStar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewStar.setAdapter(adapterStar);

        viewAllDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), AllDeckActivity.class);
                intent.putExtra("type", "all");
                startActivity(intent);
            }
        });

        viewAllStarDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), AllDeckActivity.class);
                intent.putExtra("type", "star");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list= deckDAO.getAll();
        listStar= deckDAO.getDeckByStar(1);
        adapter.setList(list);
        adapterStar.setList(listStar);
        adapter.notifyDataSetChanged();
    }
}