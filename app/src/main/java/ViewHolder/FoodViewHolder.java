package ViewHolder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petermontanaro.assignment.R;

import org.w3c.dom.Text;

import Interface.ItemClickListener;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView foodname;
    public ImageView foodimg;
    public TextView food_desc;
    public TextView food_price;

    private ItemClickListener itemClickListener;
    private FloatingActionButton cartBtn;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        foodname = (TextView) itemView.findViewById(R.id.food_name);
        foodimg = (ImageView)itemView.findViewById(R.id.food_image);
        food_desc = (TextView) itemView.findViewById(R.id.txtDesc);
        food_price = (TextView) itemView.findViewById(R.id.txtPrice);
      //  cartBtn = (FloatingActionButton) itemView.findViewById(R.id.btnCart);

        itemView.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);

    }
}
