package ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.petermontanaro.assignment.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Interface.ItemClickListener;
import Model.Order;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_CartName;
    public TextView txt_Price;

    private ItemClickListener itemClickListener;

    public void setTxt_CartName(TextView txt_CartName) {
        this.txt_CartName = txt_CartName;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_CartName = (TextView)itemView.findViewById(R.id.item_name);
        txt_Price = (TextView)itemView.findViewById(R.id.item_price);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CartHolder extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> ListContents = new ArrayList<>();
    private Context context;

    public CartHolder(List<Order> listContents, Context context) {
        ListContents = listContents;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, viewGroup,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

      //  TextDrawable drawable = TextDrawable.builder().buildRound(""+ListContents.get(i).get)
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        float price = (Float.parseFloat(ListContents.get(i).getPrice()));
        cartViewHolder.txt_Price.setText(fmt.format(price));
        cartViewHolder.txt_CartName.setText((ListContents.get(i).getOrderName()));

    }

    @Override
    public int getItemCount() {
        return ListContents.size();
    }
}

