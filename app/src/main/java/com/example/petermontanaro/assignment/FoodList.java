package com.example.petermontanaro.assignment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import Interface.ItemClickListener;
import Model.Food;
import Model.Order;
import Database.Database;
import ViewHolder.FoodViewHolder;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;
    DatabaseReference foods;

    FloatingActionButton cartBtn;

    String catId = "";


    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        // INIT FIREBASE
        database = FirebaseDatabase.getInstance();

        foodList = database.getReference("Food");


        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        // get intent
        if(getIntent() != null)
            catId = getIntent().getStringExtra("CategoryId");
        if(!catId.isEmpty() && catId != null)
        {
            loadListFood(catId);
        }
    }

    private void loadListFood(String catId) {

         adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                 R.layout.food_item,
                 FoodViewHolder.class,
                 foodList.orderByChild("MenuID").equalTo(catId))
         {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {

                viewHolder.foodname.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.foodimg);


                viewHolder.food_desc.setText(model.getDescription());

                viewHolder.food_price.setText(model.getPrice());



                final Food local = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                      // THis is the line of code that will execute an SQLite query, which references the Database object.
                        //Look there to see how the queries are handled when an Order object is passed into it.
                        new Database(getBaseContext()).addToCart(new Order(
                                local.getMenuID(),
                                local.getName(),
                                local.getPrice()
                        ));
                        Toast.makeText(FoodList.this, ""+local.getName()+" was added to cart.", Toast.LENGTH_SHORT).show();



                    }
                });
            }
        };

        // Set adapt
        Log.d("TAG", ""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);

    }


}
