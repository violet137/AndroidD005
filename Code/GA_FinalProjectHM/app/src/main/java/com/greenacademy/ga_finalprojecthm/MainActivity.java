package com.greenacademy.ga_finalprojecthm;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.greenacademy.ga_finalprojecthm.adapter.DrawerItemCustomAdapter;
import com.greenacademy.ga_finalprojecthm.fragment.ChiTietSanPhamFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MagazineFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MapFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MyHMFragment;
import com.greenacademy.ga_finalprojecthm.fragment.SupportFragment;
import com.greenacademy.ga_finalprojecthm.model.DataIcon;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private static final int MENU_ADD = Menu.FIRST;
    private static final int MENU_LIST = Menu.FIRST + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerListView = findViewById(R.id.left_drawer);


        setupToolbar();
        DataIcon[] drawerItems = new DataIcon[mNavigationDrawerItemTitles.length];

        for (int i = 0; i < mNavigationDrawerItemTitles.length; i++){
            drawerItems[i] = new DataIcon(0, mNavigationDrawerItemTitles[i]);
        }
        drawerItems[0].setIcon(R.drawable.main_menu_icon_shop_normal);
        drawerItems[4].setIcon(R.drawable.main_menu_icon_inspiration_normal);
        drawerItems[5].setIcon(R.drawable.main_menu_icon_wishlist_normal);
        drawerItems[6].setIcon(R.drawable.main_menu_icon_my_hm_normal);
        drawerItems[7].setIcon(R.drawable.main_menu_icon_services_normal);
        drawerItems[8].setIcon(R.drawable.main_menu_icon_store_locator_normal);
        drawerItems[9].setIcon(R.drawable.main_menu_icon_newsletter_normal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.item_navigation_drawer_listview, drawerItems);
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

//        //generating KeyHash
//        try {
//            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.greenacademy.ga_finalprojecthm", PackageManager.GET_SIGNATURES);
//            for(Signature signature: packageInfo.signatures){
//                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//                messageDigest.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
//            }
//        }
//        catch (Exception e){
//
//        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;

        switch (position) {
            //fragment all products
            case 0:
//                fragment = new GoogleSignInFragment();
                break;
            //fragment Ladies products
            case 1:
//                fragment = new FacebookLoginFragment();
                break;
            //fragment Men products
            case 2:
//                fragment = new MapFragment();
                break;
            //fragment Kids products
            case 3:
//                fragment = new MapFragment();
                break;
            //fragment Magazine
            case 4:
                fragment = new ChiTietSanPhamFragment();
                break;
            //fragment Wish List
            case 5:
//                fragment = new MapFragment();
                break;
            //fragment My H&M
            case 6:
                fragment = new MyHMFragment();
                break;
            //fragment Support
            case 7:
                fragment = new SupportFragment();
                break;
            //fragment Find a Store
            case 8:
                fragment = new MapFragment();
                break;
            //fragment Newsletter
            case 9:
//                fragment = new MapFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerListView.setItemChecked(position, true);
            mDrawerListView.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerListView);
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.main_menu_icon_wishlist:
                Toast.makeText(this, "Wish List selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
}
