package com.example.wifimodule;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class HomeScreenActivity extends AppCompatActivity {
	
	private LinearLayout linear18;
	private FrameLayout fragment_container;
	private BottomNavigationView bottomnavigation1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home_screen);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear18 = findViewById(R.id.linear18);
		fragment_container = findViewById(R.id.fragment_container);
		bottomnavigation1 = findViewById(R.id.bottomnavigation1);
		
		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();
				Fragment selectedFragment = null;
				
				int itemId = item.getItemId();
				
				if (itemId == R.id.action_home) {
					    selectedFragment = new HomeFragmentActivity();
				} else if (itemId == R.id.nav_additem) {
					    selectedFragment = new AdditemFragmentActivity();
				} else if (itemId == R.id.nav_mycatalogue) {
					    selectedFragment = new MycatalogueFragmentActivity();
				} else if (itemId == R.id.action_profile) {
					    selectedFragment = new ProfileFragmentActivity();
				}
				
				if(selectedFragment != null){
					getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
				}
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		_init();
	}
	
	public void _init() {
		Fragment selectedFragment = new HomeFragmentActivity();
		
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
	}
	
}
