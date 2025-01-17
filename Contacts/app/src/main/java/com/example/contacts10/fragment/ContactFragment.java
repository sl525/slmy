package com.example.contacts10.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts10.R;
import com.example.contacts10.adapter.ContactAdapter;
import com.example.contacts10.viewmodel.ContactViewModel;

public class ContactFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactViewModel contactViewModel;
    private ContactAdapter contactAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        setUpRecyclerView(view);
        observeContacts();
        return view;
    }

    private void observeContacts() {
        contactViewModel.getAllContacts().observe(getViewLifecycleOwner(), contacts -> {
            Log.d("ContactFragment","Contact observed" + contacts);
            contactAdapter.submitList(contacts); // 更新列表
        });
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactAdapter = new ContactAdapter(getContext(), contactViewModel);
        recyclerView.setAdapter(contactAdapter);
        Log.d("ContactFragment","RecycleView setup");
    }

}