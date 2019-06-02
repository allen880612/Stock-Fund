package android.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class StockManager implements Serializable {

    public StockManager()
    {

    }

    public void AddStocktoMap(String _name, ArrayList< StockHolder> _list_stock)
    {
        map_stock.put(_name, _list_stock);
    }

    public Map<String, ArrayList< StockHolder> > map_stock;

}
