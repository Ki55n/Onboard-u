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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class HomeFragmentActivity extends Fragment {
	
	private ArrayList<String> listString = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listMap2 = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear16;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private TextView textview3;
	private LinearLayout linear5;
	private LinearLayout linear14;
	private ListView listview1;
	private MaterialButton materialbutton1;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear4;
	private ImageView imageview1;
	private LinearLayout card_container;
	private LinearLayout card_container2;
	private LinearLayout card_container3;
	private CardView cardview1;
	private TextView textview4;
	private LinearLayout linear6;
	private ImageView imageview2;
	private CardView cardview4;
	private TextView textview5;
	private LinearLayout linear11;
	private ImageView imageview5;
	private CardView cardview5;
	private TextView textview6;
	private LinearLayout linear13;
	private ImageView imageview6;
	private TextView textview7;
	private LinearLayout linear17;
	private LinearLayout linear15;
	private Spinner spinner1;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear16 = _view.findViewById(R.id.linear16);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		textview3 = _view.findViewById(R.id.textview3);
		linear5 = _view.findViewById(R.id.linear5);
		linear14 = _view.findViewById(R.id.linear14);
		listview1 = _view.findViewById(R.id.listview1);
		materialbutton1 = _view.findViewById(R.id.materialbutton1);
		textview1 = _view.findViewById(R.id.textview1);
		textview2 = _view.findViewById(R.id.textview2);
		linear4 = _view.findViewById(R.id.linear4);
		imageview1 = _view.findViewById(R.id.imageview1);
		card_container = _view.findViewById(R.id.card_container);
		card_container2 = _view.findViewById(R.id.card_container2);
		card_container3 = _view.findViewById(R.id.card_container3);
		cardview1 = _view.findViewById(R.id.cardview1);
		textview4 = _view.findViewById(R.id.textview4);
		linear6 = _view.findViewById(R.id.linear6);
		imageview2 = _view.findViewById(R.id.imageview2);
		cardview4 = _view.findViewById(R.id.cardview4);
		textview5 = _view.findViewById(R.id.textview5);
		linear11 = _view.findViewById(R.id.linear11);
		imageview5 = _view.findViewById(R.id.imageview5);
		cardview5 = _view.findViewById(R.id.cardview5);
		textview6 = _view.findViewById(R.id.textview6);
		linear13 = _view.findViewById(R.id.linear13);
		imageview6 = _view.findViewById(R.id.imageview6);
		textview7 = _view.findViewById(R.id.textview7);
		linear17 = _view.findViewById(R.id.linear17);
		linear15 = _view.findViewById(R.id.linear15);
		spinner1 = _view.findViewById(R.id.spinner1);
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		_init();
	}
	
	public void _init() {
		vscroll1.smoothScrollTo(0, 0);
		for(int _repeat13 = 0; _repeat13 < (int)(5); _repeat13++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("A", "Categories");
				listMap2.add(_item);
			}
			
		}
		for(int _repeat17 = 0; _repeat17 < (int)(6); _repeat17++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("A", "Earpod");
				listMap.add(_item);
			}
			
		}
		listview1.setAdapter(new Listview1Adapter(listMap));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		Spinner1Adapter spinnerAdapter = new Spinner1Adapter(listMap2);
		spinner1.setAdapter(spinnerAdapter);
		spinnerAdapter.notifyDataSetChanged();
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.customview_listview_catalogue, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			textview1.setText(_data.get((int)_position).get("A").toString());
			
			return _view;
		}
	}
	
	public class Spinner1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Spinner1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinner_list_cuatomview, null);
			}
			
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final View linear1 = _view.findViewById(R.id.linear1);
			
			textview1.setText(_data.get((int)_position).get("A").toString());
			
			return _view;
		}
	}
}