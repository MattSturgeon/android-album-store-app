package com.northcoders.albumstore.ui.mainactivity;

import android.content.Context;
import android.widget.SearchView;
import android.widget.Toast;

class SearchHandler implements SearchView.OnQueryTextListener {

    private final Context context;

    SearchHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO
        Toast.makeText(context, "Search: " + newText, Toast.LENGTH_SHORT).show();
        return false;
    }
}
