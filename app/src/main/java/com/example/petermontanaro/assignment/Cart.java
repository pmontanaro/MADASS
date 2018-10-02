package com.example.petermontanaro.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Database db;
    Intent billScreen = new Intent(this, Bill.class);

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

        // BUTTON TO WORK ON PLACE ORDER
        btnOrder = (Button)findViewById(R.id.btnOrder);

        // BTN PETER IS WORKING ON TO CLEAR ORDER
        btnClear = (Button)findViewById(R.id.btnClearCart);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder submitOrder = new AlertDialog.Builder(Cart.this);
                submitOrder.setMessage("Are you sure you want to submit you order?");
                submitOrder.setCancelable(true);

                submitOrder.setPositiveButton(
                        "Submit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                startActivity(billScreen);

                            }
                        });

                submitOrder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog clearAlert = submitOrder.create();
                clearAlert.show();
            }
        });




        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Build an alert when clear cart is selected, to confirm clear cart action

                AlertDialog.Builder clearCart = new AlertDialog.Builder(Cart.this);
                clearCart.setMessage("Are you sure you want to clear cart? This will remove all items from the cart.");
                clearCart.setCancelable(true);

                clearCart.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new Database(getBaseContext()).clearCart();
                                loadFoods();
                                txtTotal.setText("$0");
                                dialog.cancel();
                                Toast.makeText(Cart.this, "Cart has been cleared.", Toast.LENGTH_SHORT).show();
                            }
                        });

                clearCart.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog clearAlert = clearCart.create();
                clearAlert.show();
            }
        });

        loadFoods();
    }





    private void loadFoods() {
        cart = new Database(this).getCart();
        adapter = new CartHolder(cart,this);
        recyclerView.setAdapter(adapter);

        // calculate total price
        int xero = 0;
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
