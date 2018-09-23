package com.example.petermontanaro.assignment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import Database.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Model.Order;
import ViewHolder.CartHolder;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotal;
    Button btnOrder;
    Button btnClear;


    List<Order> cart = new ArrayList<>();
    CartHolder adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // FIrebaseTiNGS

        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotal = (TextView)findViewById(R.id.total);
        btnOrder = (Button)findViewById(R.id.btnOrder);
        btnClear = (Button)findViewById(R.id.btnClearCart);

      /*  btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });*/

        loadFoods();
    }

     /*   private void showAlert() {
            AlertDialog.Builder dial = new AlertDialog.Builder(Cart.this);
            dial.setTitle("WARNING");
            dial.setMessage("Are you sure you want to clear your cart?");

            dial.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }*/


    private void loadFoods() {
        cart = new Database(this).getCart();
        adapter = new CartHolder(cart,this);
        recyclerView.setAdapter(adapter);

        // calculate total price
        int total = 0;
        for(Order order:cart)
        {
            total+=(Float.parseFloat(order.getPrice()));

            Locale locale = new Locale("en", "US");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

            txtTotal.setText(fmt.format(total));
        }
    }
}
