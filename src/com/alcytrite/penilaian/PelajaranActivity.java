package com.alcytrite.penilaian;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.SubMenu;

public class PelajaranActivity extends SherlockActivity {
	private SubMenu mGoItem;
	EditText subjectAdd, idAdd;
	TextView subject,no;
	TableLayout table_layout;
	Button addBtn, updateBtn;
	DbManager db;
	TableRow table_row;

	private static final int GO_ITEM_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pelajaran);

		ActionBar ab = getSupportActionBar();
		ab.setBackgroundDrawable(getApplicationContext().getResources()
				.getDrawable(R.drawable.background));
		ab.setLogo(getResources().getDrawable(R.drawable.phone));
		ab.setDisplayShowTitleEnabled(true);
		
		db = new DbManager(this);
		setupView();
		setupButton();
		updateTable();
		
	}

	private void setupView() {
		
		// TODO Auto-generated method stub
		idAdd = (EditText) findViewById(R.id.idAdd);
		subjectAdd = (EditText) findViewById(R.id.subjectAdd);
		no = (TextView) findViewById(R.id.no);
		subject = (TextView) findViewById(R.id.subject);
		table_layout = (TableLayout) findViewById(R.id.tabel_layout);
		addBtn = (Button) findViewById(R.id.addBtn);
		updateBtn = (Button) findViewById(R.id.updateBtn);
		table_row = (TableRow) findViewById(R.id.table_row);
		idAdd.setVisibility(View.GONE);
		updateBtn.setVisibility(View.GONE);
	}

	private void setupButton() 
	{
		// TODO Auto-generated method stub
		// Button add

		addBtn.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String mySubject = subjectAdd.getText().toString();
				Matapelajaran matapelajaran = new Matapelajaran();
				matapelajaran.setSubject(mySubject);
				
				Toast.makeText(	getBaseContext(),"anda memasukkan data Matapelajaran :   " + mySubject, Toast.LENGTH_SHORT).show();
				db.addMatapelajaran(matapelajaran);
				emptyField();
				updateTable();
				updateBtn.setVisibility(View.GONE);
			}

		});
		
		updateBtn.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String myId = idAdd.getText().toString();
				String mySubject = subjectAdd.getText().toString();
				// convert string to integer
				
				Integer theId = Integer.parseInt(myId);
				
				Matapelajaran matapelajaran = new Matapelajaran();
				matapelajaran.setSubject(mySubject);
				matapelajaran.setId(theId);
				
				Toast.makeText(getBaseContext(),"anda mengupdate data Matapelajaran :   " + mySubject, Toast.LENGTH_SHORT).show();
				db.updateMatapelajaran(matapelajaran);
				emptyField();
				updateTable();
				updateBtn.setVisibility(View.GONE);
			}

		});
		
	
		
		
	}


	private void emptyField() {
		// TODO Auto-generated method stub
		subjectAdd.setText("");
	}
	
	
	private void updateTable() 
	{
		
		// TODO Auto-generated method stub
		while (table_layout.getChildCount() > 1) {
			table_layout.removeViewAt(1);
		}

		List<Matapelajaran> data = db.getAllMatapelajaran();

		int i = 1;
		for (Matapelajaran matapelajaran : data) 
		{	
			TableRow tableBaris = new TableRow(this);
			final int primary = matapelajaran.getId();
			String iString = String.valueOf(i).toString();

			TextView idTxt = new TextView(this);
			idTxt.setText(iString);
			idTxt.setTextColor(Color.BLACK);
			idTxt.setTextSize(18);
			tableBaris.addView(idTxt);

			TextView pelajaranTxt = new TextView(this);
			pelajaranTxt.setText(matapelajaran.getSubject());
			pelajaranTxt.setTextColor(Color.BLACK);
			pelajaranTxt.setTextSize(18);
			tableBaris.addView(pelajaranTxt);
			
			Button upBtn = new Button(this);
			upBtn.setText("Update");
			tableBaris.addView(upBtn);
			
			Button delBtn = new Button(this);
			delBtn.setText("Delete");
			tableBaris.addView(delBtn);
			
			upBtn.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String curentIdTxt = ((TextView) v).getText().toString();
					Toast.makeText(getBaseContext(), "values :" + primary ,
							Toast.LENGTH_SHORT).show();

					Matapelajaran baris = db.getMatapelajaran(primary);
					idAdd.setText(baris.getIdtoString());
					subjectAdd.setText(baris.getSubject());
					updateBtn.setVisibility(View.VISIBLE);
				}
								
			});

			
			delBtn.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String curentIdTxt = ((TextView) v).getText().toString();
					Toast.makeText(getBaseContext(), "Delete :" + primary ,
							Toast.LENGTH_SHORT).show();

					Matapelajaran matapelajaran = db.getMatapelajaran(primary);
					db.deleteMatapelajaran(matapelajaran);
					emptyField();
					updateTable();
					
				}
								
			});
	
			
			table_layout.addView(tableBaris);

			if (i % 2 != 0) {
				tableBaris.setBackgroundColor(Color.rgb(222, 219, 219));
			} else {
				tableBaris.setBackgroundColor(Color.rgb(255, 255, 255));
			}

			i++;
		}
	
	}
	

	
	
	@SuppressLint("InlinedApi")
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

		mGoItem = menu.addSubMenu(0, GO_ITEM_ID, 0, null);
		mGoItem.setIcon(R.drawable.setting);
		mGoItem.add(0, R.style.Theme_Sherlock, 0, "Home");
		mGoItem.add(0, R.style.Theme_Sherlock_Light, 0, "Murid");
		mGoItem.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0, "MataPelajaran");
		mGoItem.add(0, R.style.Theme_Sherlock_Light_NoActionBar, 0, "Keluar");

		mGoItem.getItem().setShowAsAction(
				MenuItem.SHOW_AS_ACTION_ALWAYS
						| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}

	@SuppressLint("InlinedApi")
	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {

		switch (item.getItemId()) {
		case GO_ITEM_ID:
			Toast.makeText(PelajaranActivity.this, "Please Select menu list ",
					Toast.LENGTH_SHORT).show();
			break;
		case R.style.Theme_Sherlock:
			Toast.makeText(PelajaranActivity.this, "Home",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);

			break;
		case R.style.Theme_Sherlock_Light:
			Toast.makeText(PelajaranActivity.this, "Murid",
					Toast.LENGTH_SHORT).show();
			break;
		case R.style.Theme_Sherlock_Light_DarkActionBar:
			Toast.makeText(PelajaranActivity.this, "Matapelajaran",
					Toast.LENGTH_SHORT).show();
			break;
		case R.style.Theme_Sherlock_Light_NoActionBar:
			Toast.makeText(PelajaranActivity.this, "Matapelajaran", Toast.LENGTH_LONG)
					.show();
			super.finish();
			break;

		}

		return true;
	}
	
	
}

