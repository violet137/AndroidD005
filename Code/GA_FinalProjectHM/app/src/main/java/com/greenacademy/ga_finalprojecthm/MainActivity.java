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
import com.greenacademy.ga_finalprojecthm.asynctask.HomeASyncTask;
import com.greenacademy.ga_finalprojecthm.fragment.HomeFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MagazineFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MapFragment;
import com.greenacademy.ga_finalprojecthm.fragment.MyHMFragment;
import com.greenacademy.ga_finalprojecthm.fragment.SupportFragment;
import com.greenacademy.ga_finalprojecthm.fragment.WishListFragment;
import com.greenacademy.ga_finalprojecthm.model.DataIcon;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalog;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalogResponse;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.session.Session;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.util.OnCatalogSelected;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnCatalogSelected, IReceiverJSON {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private static final int MENU_ADD = Menu.FIRST;
    private static final int MENU_LIST = Menu.FIRST + 1;
    private int catalogsSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerListView = findViewById(R.id.left_drawer);

        setupToolbar();

        HomeASyncTask homeASyncTask = new HomeASyncTask();
        homeASyncTask.execute(getString(R.string.api_server), "DanhSachThoiTrang");
        homeASyncTask.setiReceiverJSON(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .addToBackStack(null)
                .commit();

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
        String fragmentTag = "";

        if (position == 0)                          //fragment all products
            fragment = new HomeFragment();
        else if (position == catalogsSize)          //fragment Magazine
            fragment = new MagazineFragment();
        else if (position == catalogsSize + 1)      //fragment Wish List
            fragment = null;
        else if (position == catalogsSize + 2) {      //fragment My H&M
            fragment = new MyHMFragment();
            fragmentTag = "MyHMFragment";
        } else if (position == catalogsSize + 3)      //fragment Support
            fragment = new SupportFragment();
        else if (position == catalogsSize + 4)      //fragment Find a Store
            fragment = new MapFragment();
        else if (position == catalogsSize + 5)      //fragment Sales
            fragment = null;

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment, fragmentTag).commit();

            mDrawerListView.setItemChecked(position, true);
            mDrawerListView.setSelection(position);

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
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new WishListFragment())
                        .commit();
//                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onCatalogSelected(String fashionCatalog) {
        Toast.makeText(this, fashionCatalog, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getStringJSON(String strJSON) {
        FashionCatalogResponse fashionCatalogResponse = ParsingToModelFromJSON.parseToFashionCatalog(strJSON);
        ArrayList<FashionCatalog> fashionCatalogs = fashionCatalogResponse.getFashionCatalogs();
        catalogsSize = fashionCatalogs.size();

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        DataIcon[] drawerItems = new DataIcon[catalogsSize + mNavigationDrawerItemTitles.length + 1];

        drawerItems[0] = new DataIcon(R.drawable.main_menu_icon_shop_normal, "Sản Phẩm");

        for (int i = 0; i < catalogsSize + mNavigationDrawerItemTitles.length; i++) {
            if (i < catalogsSize)
                drawerItems[i + 1] = new DataIcon(0, textCapSentences(fashionCatalogs.get(i).getFashionCatalogName()));
            else
                drawerItems[i + 1] = new DataIcon(0, textCapSentences(mNavigationDrawerItemTitles[i - catalogsSize]));
        }
        drawerItems[catalogsSize].setIcon(R.drawable.main_menu_icon_inspiration_normal);
        drawerItems[catalogsSize + 1].setIcon(R.drawable.main_menu_icon_wishlist_normal);
        drawerItems[catalogsSize + 2].setIcon(R.drawable.main_menu_icon_my_hm_normal);
        drawerItems[catalogsSize + 3].setIcon(R.drawable.main_menu_icon_services_normal);
        drawerItems[catalogsSize + 4].setIcon(R.drawable.main_menu_icon_store_locator_normal);
        drawerItems[catalogsSize + 5].setIcon(R.drawable.main_menu_icon_newsletter_normal);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.item_navigation_drawer_listview, drawerItems);
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0 && position < catalogsSize - 1) {
                    Fragment fragment = null;
                    if (fragment != null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment).commit();

                        mDrawerListView.setItemChecked(position, true);
                        mDrawerListView.setSelection(position);
                        setTitle(mNavigationDrawerItemTitles[position]);

                        mDrawerLayout.closeDrawer(mDrawerListView);
                    } else {
                        Log.e("MainActivity", "Error in creating fragment");
                    }
                } else
                    selectItem(position);
            }
        });
    }

    public String textCapSentences(String str) {
        String[] words = str.trim().split("[ &]");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (words[i].trim().length() > 0) {
                ret.append(Character.toUpperCase(words[i].trim().charAt(0)));
                ret.append(words[i].trim().substring(1).toLowerCase());
                if (i < words.length - 1) {
                    ret.append(' ');
                }
            }
        }
        return ret.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSession();
    }

    public void loadSession() {
        Session.setLoginSession(this);
        Session.username = Session.getUsername();
        Session.setWishListSession(this, Session.username);
        Session.wishlistProducts = Session.getWishlistProducts();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSesstion();
    }

    private void saveSesstion() {

    }
}
