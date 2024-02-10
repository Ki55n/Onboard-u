package com.example.wifimodule;

import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.*;

import java.util.*;

public class AddSingleCatalogueActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview4;
	private CardView cardview1;
	private TextView textview6;
	private CardView cardview2;
	private TextView textview8;
	private CardView cardview4;
	private TextView textview9;
	private CardView cardview5;
	private TextView textview10;
	private CardView cardview6;
	private TextView textview11;
	private CardView cardview7;
	private LinearLayout linear7;
	private LinearLayout linear12;
	private MaterialButton materialbutton1;
	private LinearLayout linear5;
	private ImageView product_image;
	private EditText product_image_edittext;
	private LinearLayout linear6;
	private EditText product_name_edittext;
	private LinearLayout linear8;
	private Spinner product_category_spinner;
	private LinearLayout linear9;
	private EditText product_description_edittext;
	private LinearLayout linear10;
	private EditText product_features_edittext;
	private LinearLayout linear11;
	private TextView textview12;
	private EditText edittext2;
	private TextView textview13;
	private TextView textview14;
	private EditText edittext5;
	private TextView textview15;
	private EditText edittext6;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_single_catalogue);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview4 = findViewById(R.id.textview4);
		cardview1 = findViewById(R.id.cardview1);
		textview6 = findViewById(R.id.textview6);
		cardview2 = findViewById(R.id.cardview2);
		textview8 = findViewById(R.id.textview8);
		cardview4 = findViewById(R.id.cardview4);
		textview9 = findViewById(R.id.textview9);
		cardview5 = findViewById(R.id.cardview5);
		textview10 = findViewById(R.id.textview10);
		cardview6 = findViewById(R.id.cardview6);
		textview11 = findViewById(R.id.textview11);
		cardview7 = findViewById(R.id.cardview7);
		linear7 = findViewById(R.id.linear7);
		linear12 = findViewById(R.id.linear12);
		materialbutton1 = findViewById(R.id.materialbutton1);
		linear5 = findViewById(R.id.linear5);
		product_image = findViewById(R.id.product_image);
		product_image_edittext = findViewById(R.id.product_image_edittext);
		linear6 = findViewById(R.id.linear6);
		product_name_edittext = findViewById(R.id.edtTradeName);
		linear8 = findViewById(R.id.linear8);
		product_category_spinner = findViewById(R.id.product_category_spinner);
		linear9 = findViewById(R.id.linear9);
		product_description_edittext = findViewById(R.id.product_description_edittext);
		linear10 = findViewById(R.id.linear10);
		product_features_edittext = findViewById(R.id.product_features_edittext);
		linear11 = findViewById(R.id.linear11);
		textview12 = findViewById(R.id.textview12);
		edittext2 = findViewById(R.id.edittext2);
		textview13 = findViewById(R.id.textview13);
		textview14 = findViewById(R.id.textview14);
		edittext5 = findViewById(R.id.edittext5);
		textview15 = findViewById(R.id.textview15);
		edittext6 = findViewById(R.id.edittext6);
	}
	
	private void initializeLogic() {
	}
	
	public class Product_category_spinnerAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Product_category_spinnerAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.spinner_list_cuatomview, null);
			}
			
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final View linear1 = _view.findViewById(R.id.linear1);
			
			return _view;
		}
	}
}
