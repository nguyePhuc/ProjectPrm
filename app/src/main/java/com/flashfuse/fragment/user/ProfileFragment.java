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

import com.flashfuse.R;
import com.flashfuse.activity.IntroduceActivity;


public class ProfileFragment extends Fragment {

    Button btn_account_info, btn_send_feedback, btn_introduce, btn_delete_account, btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_account_info = view.findViewById(R.id.btn_account_info);
        btn_send_feedback = view.findViewById(R.id.btn_send_feedback);
        btn_introduce = view.findViewById(R.id.btn_introduce);
        btn_delete_account = view.findViewById(R.id.btn_delete_account);
        btn_logout = view.findViewById(R.id.btn_logout);

        btn_account_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), IntroduceActivity.class);
                startActivity(intent);
            }
        });

        btn_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}