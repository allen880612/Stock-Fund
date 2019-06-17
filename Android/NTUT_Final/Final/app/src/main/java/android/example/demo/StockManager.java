package android.example.demo;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockManager implements Serializable{

    private final String OPEN = "OPEN";
    private final String CLOSE = "CLOSE";
    private final String HIGH = "HIGH";
    private final String LOW = "LOW";
    private final String UP_DOWN = "UP_DOWN";
    private final String UP_DOWN_PERCENT = "UP_DOWN_PERCENT";
    private final String PER = "PER";
    private final String PER_4S = "PER_4S";

    public StockManager()
    {
        stock_map = new HashMap<>();
        stock_top50 = new HashMap<>();
        stock_data = new ArrayList<>();
    }

    public StockManager(ArrayList<StockHolder> _data)
    {
        stock_map = new HashMap<>();
        stock_top50 = new HashMap<>();
        stock_data = _data;
    }

    public void SortStock()
    {
        AddStocktoMap(CLOSE, SortByClose());                // sort by close price
        AddStocktoMap(OPEN, SortByOpen());                // sort by close price
        AddStocktoMap(HIGH, SortByHigh());                // sort by close price
        AddStocktoMap(LOW, SortByLow());                // sort by close price
        AddStocktoMap(UP_DOWN, SortByUpDown());             // sort by up down price
        AddStocktoMap(UP_DOWN_PERCENT, SortByUpDownP());    // sort by up down percent
        AddStocktoMap(PER, SortByPER());                    // sort by PER
        AddStocktoMap(PER_4S, SortByPER_4S());              // sort by PER 4 season
    }

    public ArrayList<StockHolder> SortByClose()
    {
        // 微算機真是門好課，clone 真香
        ArrayList<StockHolder> sort =  (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, closeComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", Close Price : " + sh.GetClose());

        return sort;
    }

    public ArrayList<StockHolder> SortByOpen()
    {
        ArrayList<StockHolder> sort =  (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, openComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", Close Price : " + sh.GetClose());

        return sort;
    }

    public ArrayList<StockHolder> SortByHigh()
    {
        ArrayList<StockHolder> sort =  (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, highComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", High Price : " + sh.GetHigh());

        return sort;
    }

    public ArrayList<StockHolder> SortByLow()
    {
        ArrayList<StockHolder> sort =  (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, lowComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", Low Price : " + sh.GetLow());

        return sort;
    }

    public ArrayList<StockHolder> SortByUpDown()
    {
        ArrayList<StockHolder> sort = (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, upDownComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", UpDown : " + sh.GetUpDown());

        return sort;
    }

    public ArrayList<StockHolder> SortByUpDownP()
    {
        ArrayList<StockHolder> sort = (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, upDownPComparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", UpDown percent : " + sh.GetUpDownP());

        return sort;
    }

    public ArrayList<StockHolder> SortByPER()
    {
        ArrayList<StockHolder> sort = (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, PER_Comparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", PER : " + sh.GetPER());

        return sort;
    }

    public ArrayList<StockHolder> SortByPER_4S()
    {
        ArrayList<StockHolder> sort = (ArrayList<StockHolder>) stock_data.clone();
        Collections.sort(sort, PER_4S_Comparator);

//        for ( StockHolder sh : sort )
//            Log.d("auau sort:", sh.GetCode() + ", PER 4season : " + sh.GetPER_4S());

        return sort;
    }

    public void AddStocktoMap(String _name, ArrayList< StockHolder> _list_stock)
    {
        stock_map.put(_name, _list_stock);
    }

    public Map<String, ArrayList< StockHolder> > GetMap()
    {
        return stock_map;
    }

    public void SetTop50()
    {
        for (Map.Entry<String, ArrayList<StockHolder> > entry : stock_map.entrySet())
        {
            // 一言不合就強制轉態
            // 強制轉態失敗，就用他 new 阿
            List<StockHolder> sub = entry.getValue().subList(0, 50);
            ArrayList<StockHolder> top50 = new ArrayList(sub);

            stock_top50.put( entry.getKey(),  top50);
            Log.d("auau map", "key :" + entry.getKey());
        }
    }

    public Map<String, ArrayList<StockHolder> > GetTop50()
    {
        return stock_top50;
    }

    public ArrayList< StockHolder> GetArrayList(String _key)
    {
        if (stock_map.containsKey(_key))
        {
            return stock_map.get(_key);
        }
        return null;
    }

    public StockHolder[] GetArray(String _key)
    {
        if (stock_map.get(_key) != null)
        {
           return stock_map.get(_key).toArray(new StockHolder[GetSize(_key)]);
        }
        return null;
    }

    public int GetSize(String _key)
    {
        if (stock_map.get(_key) != null)
        {
            return stock_map.get(_key).size();
        }
        return 0;
    }

    boolean IsEmpty()
    {
        return stock_map.size() == 0;
    }

    // Private:
    private Map<String, ArrayList< StockHolder> > stock_map;
    private Map<String, ArrayList< StockHolder> > stock_top50;
    private ArrayList< StockHolder> stock_data;
    private static final long serialVersionUID = 1L;


    // compare with close price (down to)
    public static Comparator closeComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetClose().isEmpty() ? 0 : (Float.valueOf(sh1.GetClose()));   // 防空字串
            float n2 =  sh2.GetClose().isEmpty() ? 0 : (Float.valueOf(sh2.GetClose()));   // 防空字串

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with open price (down to)
    public static Comparator openComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetOpen().isEmpty() ? 0 : (Float.valueOf(sh1.GetOpen()));   // 防空字串
            float n2 =  sh2.GetOpen().isEmpty() ? 0 : (Float.valueOf(sh2.GetOpen()));   // 防空字串

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with high price (down to)
    public static Comparator highComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetHigh().isEmpty() ? 0 : (Float.valueOf(sh1.GetHigh()));   // 防空字串
            float n2 =  sh2.GetHigh().isEmpty() ? 0 : (Float.valueOf(sh2.GetHigh()));   // 防空字串

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with low price (down to)
    public static Comparator lowComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetLow().isEmpty() ? 0 : (Float.valueOf(sh1.GetLow()));   // 防空字串
            float n2 =  sh2.GetLow().isEmpty() ? 0 : (Float.valueOf(sh2.GetLow()));   // 防空字串

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with upDown price (down to)
    public static Comparator upDownComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetUpDown().isEmpty() ? 0 : (Float.valueOf(sh1.GetUpDown()));   // 防空字串
            float n2 =  sh2.GetUpDown().isEmpty() ? 0 : (Float.valueOf(sh2.GetUpDown()));   // 防空字串

            n1 = Math.abs(n1);
            n2 = Math.abs(n2);

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with upDown Percent (down to)
    public static Comparator upDownPComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetUpDownP().isEmpty() ? 0 : (Float.valueOf(sh1.GetUpDownP()));   // 防空字串
            float n2 =  sh2.GetUpDownP().isEmpty() ? 0 : (Float.valueOf(sh2.GetUpDownP()));   // 防空字串

            n1 = Math.abs(n1);
            n2 = Math.abs(n2);

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };

    // compare with Price-to-Earning Ratio (up to)
    public static Comparator PER_Comparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetPER().isEmpty() ? 21487 : (Float.valueOf(sh1.GetPER()));   // 防空字串
            float n2 =  sh2.GetPER().isEmpty() ? 21487 : (Float.valueOf(sh2.GetPER()));   // 防空字串


            // Small to Big (up to)
            if (n1 < n2)
                return -1;
            else if (n1 > n2)
                return 1;
            return 0;
        }
    };

    // compare with Price-to-Earning Ratio 4 season (up to)
    public static Comparator PER_4S_Comparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            StockHolder sh1 = (StockHolder) o1;
            StockHolder sh2 = (StockHolder) o2;

            float n1 =  sh1.GetPER_4S().isEmpty() ? 214878 : (Float.valueOf(sh1.GetPER_4S()));   // 防空字串
            float n2 =  sh2.GetPER_4S().isEmpty() ? 214878 : (Float.valueOf(sh2.GetPER_4S()));   // 防空字串

            // Small to Big (up to)
            if (n1 < n2)
                return -1;
            else if (n1 > n2)
                return 1;
            return 0;
        }
    };

}

