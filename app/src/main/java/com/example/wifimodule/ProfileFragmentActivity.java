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
import android.view.View;
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

public class ProfileFragmentActivity extends Fragment {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview2;
	private TextView textview2;
	private TextView textview3;
	private LinearLayout first_view_container;
	private CardView cardview1;
	private MaterialButton materialbutton1;
	private MaterialButton materialbutton_view_all;
	private ImageView imageview1;
	private TextView textview1;
	private CardView cardview3;
	private CardView cardview4;
	private CardView cardview5;
	private CardView cardview6;
	private CardView cardview7;
	private CardView cardview8;
	private LinearLayout linear5;
	private LinearLayout linear8;
	private TextView textview11;
	private MaterialButton phone_button;
	private ImageView imageview4;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView textview12;
	private MaterialButton materialbutton2;
	private ImageView imageview5;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView textview13;
	private MaterialButton materialbutton3;
	private ImageView imageview6;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private TextView textview14;
	private MaterialButton materialbutton4;
	private ImageView imageview7;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private TextView textview15;
	private MaterialButton materialbutton5;
	private ImageView imageview8;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private TextView textview16;
	private MaterialButton materialbutton6;
	private ImageView imageview9;
	private LinearLayout linear3;
	private TextView textview4;
	private EditText edittext_business_name;
	private TextView textview5;
	private EditText edittext_enter_address;
	private LinearLayout locate_shop_container;
	private TextView textview7;
	private EditText edittext_enter_email_address;
	private TextView textview8;
	private EditText edittext_phone_number;
	private TextView textview9;
	private EditText edittext_bank_account_details;
	private TextView textview10;
	private EditText edittext_enter_shipping_method;
	private ImageView imageview3;
	private TextView textview6;

	private EditText edittext_name;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.profile_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		vscroll1 = _view.findViewById(R.id.vscroll1);
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		imageview2 = _view.findViewById(R.id.imageview2);
		textview2 = _view.findViewById(R.id.textview2);
		textview3 = _view.findViewById(R.id.textview3);
		first_view_container = _view.findViewById(R.id.first_view_container);
		cardview1 = _view.findViewById(R.id.cardview1);
		materialbutton1 = _view.findViewById(R.id.materialbutton1);
		materialbutton_view_all = _view.findViewById(R.id.materialbutton_view_all);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview1 = _view.findViewById(R.id.textview1);
		cardview3 = _view.findViewById(R.id.cardview3);
		cardview4 = _view.findViewById(R.id.cardview4);
		cardview5 = _view.findViewById(R.id.cardview5);
		cardview6 = _view.findViewById(R.id.cardview6);
		cardview7 = _view.findViewById(R.id.cardview7);
		cardview8 = _view.findViewById(R.id.cardview8);
		linear5 = _view.findViewById(R.id.linear5);
		linear8 = _view.findViewById(R.id.linear8);
		textview11 = _view.findViewById(R.id.textview11);
		phone_button = _view.findViewById(R.id.phone_button);
		imageview4 = _view.findViewById(R.id.imageview4);
		linear9 = _view.findViewById(R.id.linear9);
		linear10 = _view.findViewById(R.id.linear10);
		textview12 = _view.findViewById(R.id.textview12);
		materialbutton2 = _view.findViewById(R.id.materialbutton2);
		imageview5 = _view.findViewById(R.id.imageview5);
		linear11 = _view.findViewById(R.id.linear11);
		linear12 = _view.findViewById(R.id.linear12);
		textview13 = _view.findViewById(R.id.textview13);
		materialbutton3 = _view.findViewById(R.id.materialbutton3);
		imageview6 = _view.findViewById(R.id.imageview6);
		linear13 = _view.findViewById(R.id.linear13);
		linear14 = _view.findViewById(R.id.linear14);
		textview14 = _view.findViewById(R.id.textview14);
		materialbutton4 = _view.findViewById(R.id.materialbutton4);
		imageview7 = _view.findViewById(R.id.imageview7);
		linear15 = _view.findViewById(R.id.linear15);
		linear16 = _view.findViewById(R.id.linear16);
		textview15 = _view.findViewById(R.id.textview15);
		materialbutton5 = _view.findViewById(R.id.materialbutton5);
		imageview8 = _view.findViewById(R.id.imageview8);
		linear17 = _view.findViewById(R.id.linear17);
		linear18 = _view.findViewById(R.id.linear18);
		textview16 = _view.findViewById(R.id.textview16);
		materialbutton6 = _view.findViewById(R.id.materialbutton6);
		imageview9 = _view.findViewById(R.id.imageview9);
		linear3 = _view.findViewById(R.id.linear3);
		textview4 = _view.findViewById(R.id.textview4);
		edittext_business_name = _view.findViewById(R.id.edittext_business_name);
		textview5 = _view.findViewById(R.id.textview5);
		edittext_enter_address = _view.findViewById(R.id.edittext_enter_address);
		locate_shop_container = _view.findViewById(R.id.locate_shop_container);
		textview7 = _view.findViewById(R.id.textview7);
		edittext_enter_email_address = _view.findViewById(R.id.edittext_enter_email_address);
		textview8 = _view.findViewById(R.id.textview8);
		edittext_phone_number = _view.findViewById(R.id.edittext_phone_number);
		textview9 = _view.findViewById(R.id.textview9);
		edittext_bank_account_details = _view.findViewById(R.id.edittext_bank_account_details);
		textview10 = _view.findViewById(R.id.textview10);
		edittext_enter_shipping_method = _view.findViewById(R.id.edittext_enter_shipping_method);
		imageview3 = _view.findViewById(R.id.imageview3);
		textview6 = _view.findViewById(R.id.textview6);
		edittext_name = _view.findViewById(R.id.edittext_business_name);
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (materialbutton1.getText().toString().equals("Edit Profile")) {
					_showUpdateProfile();
				}
				else {
					if (materialbutton1.getText().toString().equals("Update Profile")) {
						_showEditProfile();
					}
				}
				first_view_container.setVisibility(View.GONE);
				cardview1.setVisibility(View.VISIBLE);
			}
		});
		
		materialbutton_view_all.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				first_view_container.setVisibility(View.GONE);
				cardview1.setVisibility(View.VISIBLE);
				_showEditProfile();
			}
		});
		
		phone_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
		
		materialbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
		
		materialbutton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
		
		materialbutton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
		
		materialbutton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
		
		materialbutton6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_edit_details();
			}
		});
	}
	
	private void initializeLogic() {
		imageview3.setColorFilter(0xFF676767, PorterDuff.Mode.MULTIPLY);
		_init();
	}
	
	public void _showEditProfile() {
		edittext_business_name.setEnabled(false);
		edittext_enter_address.setEnabled(false);
		edittext_enter_email_address.setEnabled(false);
		edittext_phone_number.setEnabled(false);
		edittext_bank_account_details.setInputType(InputType.TYPE_NULL);
		
		edittext_enter_shipping_method.setInputType(InputType.TYPE_NULL);
		edittext_business_name.setHint("Ashram Enterprises Limited");
		edittext_enter_address.setHint("Ashram Building, Suite 3, Ghandi Avenue, Chennai");
		edittext_enter_email_address.setHint("hello@ashram.com");
		edittext_phone_number.setHint("+912786756745");
		edittext_bank_account_details.setHint("View Bank Account Details");
		edittext_enter_shipping_method.setHint("Self-Shipping");
		locate_shop_container.setVisibility(View.GONE);
		Drawable drawable = getResources().getDrawable(R.drawable.default_image); // Replace with your image resource
		
		// Set the bounds of the drawable (adjust as needed)
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		
		// Set the compound drawables for left, top, right, and bottom
		edittext_enter_email_address.setCompoundDrawables(null, null, drawable, null);
		
		edittext_phone_number.setCompoundDrawables(null, null, drawable, null);
		
		edittext_enter_shipping_method.setCompoundDrawables(null, null, drawable, null);
		
		
		edittext_bank_account_details.setCompoundDrawables(null, null, drawable, null);
		materialbutton1.setText("Edit Profile");
		// Set touch listener on the right drawable
		edittext_bank_account_details.setOnTouchListener(new View.OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        // Check if the touch event is within the bounds of the right drawable
				        if (event.getAction() == MotionEvent.ACTION_UP &&
				            event.getRawX() >= (edittext_bank_account_details.getRight() - edittext_bank_account_details.getCompoundDrawables()[2].getBounds().width())) {
					            // Handle the click event on the right drawable
					            // Replace the following line with your desired action
					            Toast.makeText(getContext().getApplicationContext(), "Drawable Clicked!", Toast.LENGTH_SHORT).show();
					            return true; // Consume the touch event
					        }
				        return false; // Allow other touch events to be handled
				    }
		});
		
		edittext_enter_shipping_method.setOnTouchListener(new View.OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        // Check if the touch event is within the bounds of the right drawable
				        if (event.getAction() == MotionEvent.ACTION_UP &&
				            event.getRawX() >= (edittext_enter_shipping_method.getRight() - edittext_enter_shipping_method.getCompoundDrawables()[2].getBounds().width())) {
					            // Handle the click event on the right drawable
					            // Replace the following line with your desired action
					            Toast.makeText(getContext().getApplicationContext(), "Drawable Clicked!", Toast.LENGTH_SHORT).show();
					            return true; // Consume the touch event
					        }
				        return false; // Allow other touch events to be handled
				    }
		});
		materialbutton_view_all.setVisibility(View.GONE);
	}
	
	
	public void _showUpdateProfile() {
		edittext_business_name.setEnabled(true);
		edittext_enter_address.setEnabled(true);
		edittext_enter_email_address.setEnabled(true);
		edittext_phone_number.setEnabled(true);
		edittext_bank_account_details.setInputType(InputType.TYPE_CLASS_TEXT);
		
		edittext_enter_shipping_method.setInputType(InputType.TYPE_CLASS_TEXT);
		edittext_business_name.setHint("Enter Business Name");
		edittext_enter_address.setHint("Enter Address");
		edittext_enter_email_address.setHint("Enter Email Address");
		edittext_phone_number.setHint("Enter Phone No");
		edittext_bank_account_details.setHint("Enter Bank Account Details");
		edittext_enter_shipping_method.setHint("Select Shipping Method");
		locate_shop_container.setVisibility(View.VISIBLE);
		Drawable drawable = getResources().getDrawable(R.drawable.default_image); // Replace with your image resource
		
		// Set the bounds of the drawable (adjust as needed)
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		
		// Set the compound drawables for left, top, right, and bottom
		edittext_enter_email_address.setCompoundDrawables(null, null, null, null);
		
		edittext_phone_number.setCompoundDrawables(null, null, null, null);
		
		edittext_enter_shipping_method.setCompoundDrawables(null, null, drawable, null);
		
		
		edittext_bank_account_details.setCompoundDrawables(null, null, null, null);
		materialbutton1.setText("Update Profile");
		edittext_bank_account_details.setOnTouchListener(null);
		
		edittext_enter_shipping_method.setOnTouchListener(new View.OnTouchListener() {
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        // Check if the touch event is within the bounds of the right drawable
				        if (event.getAction() == MotionEvent.ACTION_UP &&
				            event.getRawX() >= (edittext_enter_shipping_method.getRight() - edittext_enter_shipping_method.getCompoundDrawables()[2].getBounds().width())) {
					            // Handle the click event on the right drawable
					            // Replace the following line with your desired action
					            Toast.makeText(getContext().getApplicationContext(), "Drawable Clicked!", Toast.LENGTH_SHORT).show();
					            return true; // Consume the touch event
					        }
				        return false; // Allow other touch events to be handled
				    }
		});
	}
	
	
	public void _init() {
		cardview1.setVisibility(View.GONE);
		first_view_container.setVisibility(View.VISIBLE);
		materialbutton_view_all.setVisibility(View.VISIBLE);
	}
	
	
	public void _edit_details() {
		first_view_container.setVisibility(View.GONE);
		cardview1.setVisibility(View.VISIBLE);
		_showEditProfile();
	}
	
}
