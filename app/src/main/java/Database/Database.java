package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Order;

public class Database extends SQLiteAssetHelper {

  //  private static final String DB_NAME="assDB.db";
    private static final int DB_VER=1;



    public Database(Context context) {

         super(context, "assDB.db",null, 2);

    }

    public List<Order> getCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlQuery = {"orderId", "orderName", /*"quantity",*/ "price"};
        String table = "OrderDetail";
        qb.setTables(table);

        Cursor c = qb.query(db,sqlQuery,null, null,null,null,null);

        final List<Order> results = new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                results.add(new Order(c.getString(c.getColumnIndex("orderId")),
                           c.getString(c.getColumnIndex("orderName")),
                          // c.getString(c.getColumnIndex("quantity")),
                           c.getString(c.getColumnIndex("price"))));
            }while (c.moveToNext());
        }
        return results;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(OrderId,orderName,price) VALUES('%s','%s','%s');", // ADD QUANTITIES BACK IN?
                order.getOrderId(),
                order.getOrderName(),
              //  order.getQuantity(),
                order.getPrice());
        db.execSQL(query);
    }

    public void clearCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE * FROM OrderDetail");
        db.execSQL(query);
    }
}
