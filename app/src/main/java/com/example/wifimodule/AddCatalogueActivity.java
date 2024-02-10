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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.button.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class AddCatalogueActivity extends AppCompatActivity {
	
	private ScrollView vscroll2;
	private LinearLayout full_container;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private TextView textview2;
	private CardView cardview1;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear4;
	private TextView textview4;
	private CardView cardview2;
	private TextView textview6;
	private EditText edittext1;
	private TextView textview7;
	private EditText edittext2;
	private TextView textview8;
	private EditText edittext3;
	private TextView textview9;
	private EditText edittext4;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private MaterialButton materialbutton1;
	private LinearLayout linear5;
	private TextView textview5;
	private TextView textview10;
	private TextView textview11;
	private EditText edittext5;
	private EditText edittext6;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_catalogue);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll2 = findViewById(R.id.vscroll2);
		full_container = findViewById(R.id.full_container);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		textview2 = findViewById(R.id.textview2);
		cardview1 = findViewById(R.id.cardview1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		linear4 = findViewById(R.id.linear4);
		textview4 = findViewById(R.id.textview4);
		cardview2 = findViewById(R.id.cardview2);
		textview6 = findViewById(R.id.textview6);
		edittext1 = findViewById(R.id.edittext1);
		textview7 = findViewById(R.id.textview7);
		edittext2 = findViewById(R.id.edittext2);
		textview8 = findViewById(R.id.textview8);
		edittext3 = findViewById(R.id.edittext3);
		textview9 = findViewById(R.id.textview9);
		edittext4 = findViewById(R.id.edittext4);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		materialbutton1 = findViewById(R.id.materialbutton1);
		linear5 = findViewById(R.id.linear5);
		textview5 = findViewById(R.id.textview5);
		textview10 = findViewById(R.id.textview10);
		textview11 = findViewById(R.id.textview11);
		edittext5 = findViewById(R.id.edittext5);
		edittext6 = findViewById(R.id.edittext6);
	}
	
	private void initializeLogic() {
	}
	
}