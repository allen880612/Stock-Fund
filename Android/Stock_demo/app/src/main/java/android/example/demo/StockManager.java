package android.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockManager implements Serializable {

    public StockManager()
    {
        map_stock = new HashMap<>();
    }

    public void AddStocktoMap(String _name, ArrayList< StockHolder> _list_stock)
    {
        map_stock.put(_name, _list_stock);
    }

    public ArrayList< StockHolder> GetArrayList(String _key)
    {
        if (map_stock.containsKey(_key))
        {
            return map_stock.get(_key);
        }
        return null;
    }

    public StockHolder[] GetArray(String _key)
    {
        if (map_stock.get(_key) != null)
        {
           return map_stock.get(_key).toArray(new StockHolder[GetSize(_key)]);
        }
        return null;
    }

    public int GetSize(String _key)
    {
        if (map_stock.get(_key) != null)
        {
            return map_stock.get(_key).size();
        }
        return 0;
    }

    boolean IsEmpty()
    {
        return map_stock.size() == 0;
    }

    // Private:
    private Map<String, ArrayList< StockHolder> > map_stock;
    private static final long serialVersionUID = 1L;
}
