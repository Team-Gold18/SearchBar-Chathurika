package com.hit.searchbarapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hit.searchbarapplication.adapter.RecyclerviewAdapter;
import com.hit.searchbarapplication.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_bar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        showKeyboards(searchView);

        findViewById(R.id.userRecycler).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });

      /*  searchView.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });*/

        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String s = searchView.getText().toString().trim();
                if (actionId == EditorInfo.IME_ACTION_DONE){

                    hideKeyboard(searchView);
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            private void hideKeyboard(EditText searchView) {

               InputMethodManager manager = (InputMethodManager) getSystemService(
                 Context.INPUT_METHOD_SERVICE
               );

               manager.hideSoftInputFromWindow(searchView.getApplicationWindowToken()
               ,0);
            }
        });

        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(new UserData("Anderson Thomas", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man1));
        userDataList.add(new UserData("Adams Green", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man2));
        userDataList.add(new UserData("Betty Len", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man3));
        userDataList.add(new UserData("Roberts Turner", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man4));
        userDataList.add(new UserData("laura Michelle", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman1));
        userDataList.add(new UserData("Garcia Lewis", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman2));
        userDataList.add(new UserData("Mary Jackson", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman3));
        userDataList.add(new UserData("Sarah Scott", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman4));
        userDataList.add(new UserData("Anderson Thomas", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man1));
        userDataList.add(new UserData("Adams Green", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man2));
        userDataList.add(new UserData("Betty Len", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man3));
        userDataList.add(new UserData("Roberts Turner", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_man4));
        userDataList.add(new UserData("laura Michelle", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman1));
        userDataList.add(new UserData("Garcia Lewis", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman2));
        userDataList.add(new UserData("Mary Jackson", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman3));
        userDataList.add(new UserData("Sarah Scott", "I regard myself to be a very open minded person, ready for new challenge, especially when it comes to technology.", R.drawable.im_woman4));

        setUserRecycler(userDataList);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void showKeyboards(EditText searchView) {
        InputMethodManager manager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE
        );

        manager.showSoftInput(searchView.getRootView()
        ,InputMethodManager.SHOW_IMPLICIT);

        searchView.requestFocus();

    }

    private void setUserRecycler(List<UserData> userDataList){

        userRecycler = findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(this, userDataList);
        userRecycler.setAdapter(recyclerviewAdapter);

    }

}