package com.example.sun.studentassistent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,NotesFragment.OnFragmentInteractionListener,TimetableFragment.OnFragmentInteractionListener,AboutFragment.OnFragmentInteractionListener{
    private FloatingActionMenu menu;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
   private  Intent i;
   // public  static int index=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new NotesFragment()).commit();}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu= (FloatingActionMenu) findViewById(R.id.menu);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        menu.setClosedOnTouchOutside(true);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
       // index=Integer.parseInt(getIntent().getStringExtra("p"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
//添加便签
                case R.id.fab1:
                    i=new Intent();
                    i.setClass(MainActivity.this,AddnotesActivity.class);
                    startActivity(i);
                    menu.hideMenu(true);
                    break;
                //添加课程
                case R.id.fab2:
                    i=new Intent();
                    i.setClass(MainActivity.this,AddCourseActivity.class);
                    startActivity(i);
                    menu.hideMenu(true);
                    break;
//                case R.id.fab3:
//
//                  //  text = fab3.getLabelText();
//
//                    break;
            }


        }

    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
private void isopen(){
  if (menu.isMenuHidden()){
      menu.showMenu(true);
  }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
isopen();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new NotesFragment()).commit();

        } else if (id == R.id.nav_timetable) {
            isopen();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new TimetableFragment()).commit();
        }

else if (id == R.id.nav_about) {
            isopen();
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new AboutFragment()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
