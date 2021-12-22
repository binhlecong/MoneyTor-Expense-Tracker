package com.hcmus.group14.moneytor.ui.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.navigation.ui.AppBarConfiguration;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.hcmus.group14.moneytor.data.model.Relate;

import com.hcmus.group14.moneytor.data.model.Spending;
import com.hcmus.group14.moneytor.databinding.ActivityContactBinding;


import com.hcmus.group14.moneytor.R;
import com.hcmus.group14.moneytor.services.spending.SpendingViewModel;
import com.hcmus.group14.moneytor.ui.base.NoteBaseActivity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends NoteBaseActivity<ActivityContactBinding> {

    private AppBarConfiguration appBarConfiguration;
    private ActivityContactBinding binding;
    private SpendingViewModel spendingViewModel;
    private List<Relate> contacts;
    private ContactAdapter contactAdapter;
    private Context context;
    private SearchView searchView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        this.context = this.getApplicationContext();
        // do what you want
        this.setTitle("Relate");
        contacts = getData();
        initializeViews();
    }

    List<Relate> getData() {
        List<Relate> data = new ArrayList<>();
        data.add(new Relate("Hieu", "01234556789"));
        data.add(new Relate("Hieu1", "01234556789"));
        data.add(new Relate("Hieu2", "01234556789"));
        data.add(new Relate("Hieu3", "01234556789"));
        data.add(new Relate("Hieu4", "01234556789"));
        data.add(new Relate("Hieu5", "01234556789"));
        data.add(new Relate("Hieu6", "01234556789"));
        data.add(new Relate("Hieu7", "01234556789"));
        data.add(new Relate("Hieu8", "01234556789"));
        data.add(new Relate("Hieu9", "01234556789"));
        data.add(new Relate("Hieu10", "01234556789"));
        data.add(new Relate("Hieu11", "01234556789"));
        return data;
    }

    private void initializeViews() {
        contactAdapter = new ContactAdapter(this, contacts, findViewById(R.id.selectedContact));
        binding.contactList.setAdapter(contactAdapter);
        binding.contactList.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Relate> filter(String text) {
        List<Relate> filteredList = new ArrayList<>();
        for (Relate item : contacts) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                contactAdapter.filterList(filter(s));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                contactAdapter.filterList(filter(s));
                return false;
            }
        });
        MenuItem doneButton = menu.findItem(R.id.actionConfirm);
        doneButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                List<Relate> selectedContacts= contactAdapter.getSelectedContacts();
                Intent replyIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("contacts", (Serializable) selectedContacts);
                replyIntent.putExtras(bundle);
                setResult(RESULT_OK, replyIntent);
                finish();
                return true;
            }
        });
        return true;

    }
}