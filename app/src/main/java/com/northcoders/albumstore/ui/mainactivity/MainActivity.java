package com.northcoders.albumstore.ui.mainactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationBarView;
import com.northcoders.albumstore.R;
import com.northcoders.albumstore.ui.addalbum.AddAlbumFragment;
import com.northcoders.albumstore.ui.library.LibraryFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        NavigationBarView navigationBarView = findViewById(R.id.navigation_bar);
        navigationBarView.setOnItemSelectedListener(this::onNavigationSelected);
        navigationBarView.setSelectedItemId(R.id.nav_library);
    }

    private boolean onNavigationSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        Log.i(TAG, "Navigated to " + itemId);

        Fragment dest;
        if (itemId == R.id.nav_library) {
            dest = new LibraryFragment();
        } else if (itemId == R.id.nav_add_album) {
            dest = new AddAlbumFragment();
        } else {
            throw new IllegalStateException("Unknown menu itemID " + itemId);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, dest)
                .commit();

        return true;
    }
}