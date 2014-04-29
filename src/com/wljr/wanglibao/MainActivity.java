package com.wljr.wanglibao;

import fragment.HomePage;
import fragment.Recommend;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager.OnCancelListener;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * Ö÷½çÃæ
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements OnClickListener{
    private LinearLayout menu1,menu2,menu3,menu4;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	

	private void init(){
		menu1=(LinearLayout)this.findViewById(R.id.menu1);
		menu2=(LinearLayout)this.findViewById(R.id.menu2);
		menu3=(LinearLayout)this.findViewById(R.id.menu3);
		menu4=(LinearLayout)this.findViewById(R.id.menu4);
		menu1.setOnClickListener(this);
		menu2.setOnClickListener(this);
		menu3.setOnClickListener(this);
		menu4.setOnClickListener(this);
		fManager = getSupportFragmentManager();
		fTransaction = fManager.beginTransaction();
		HomePage homePage = new HomePage();
		fTransaction.add(R.id.container, homePage, "homePage");
		fTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		fManager = getSupportFragmentManager();
		fTransaction = fManager.beginTransaction();
		switch (arg0.getId()) {
		case R.id.menu1:
			HomePage homePage = new HomePage();
			fTransaction.replace(R.id.container, homePage, "homePage");
			menu1.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_home_check));
			menu2.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_scan_normal));
			menu3.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_search_normal));
			menu4.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_shopping_normal));
			fTransaction.commit(); 
			break;
		case R.id.menu2:
			Recommend recommend = new Recommend();
			fTransaction.replace(R.id.container, recommend, "recommend");
			menu1.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_home_normal));
			menu2.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_scan_check));
			menu3.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_search_normal));
			menu4.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_shopping_normal));
			fTransaction.commit();
			break;
		case R.id.menu3:
			menu1.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_home_normal));
			menu2.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_scan_normal));
			menu3.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_search_check));
			menu4.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_shopping_normal));
			break;
		case R.id.menu4:
			menu1.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_home_normal));
			menu2.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_scan_normal));
			menu3.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_search_normal));
			menu4.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_shopping_check));
			break;

		default:
			break;
		}
	}

}
