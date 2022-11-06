package com.example.myapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.account.LoginActivity;
import com.example.myapp.catalog.CatalogActivity;
import com.example.myapp.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
        CommonUtils.setContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.m_home:
                try {
                    intent = new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    System.out.println("Problem " + ex.getMessage());
                }
                return true;

            case R.id.m_catalog:
                try {
                    intent = new Intent(BaseActivity.this, CatalogActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    System.out.println("Problem " + ex.getMessage());
                }
                return true;

            case R.id.m_create:
                try {
                    intent = new Intent(BaseActivity.this, CreateActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    System.out.println("Problem " + ex.getMessage());
                }
                return true;

            case R.id.m_login:
                try {
                    intent = new Intent(BaseActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    System.out.println("Problem " + ex.getMessage());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
