package android.example.final_2;

import java.io.Serializable;

public class StockHolder implements Serializable {

    private static final long serialVersionUID = 2333;

    public StockHolder(String[] _data)
    {
        code    = _data[0];
        name    = _data[1];
        date    = _data[2];
        open    = _data[3];
        high    = _data[4];
        low     = _data[5];
        close   = _data[6];
        upDown  = _data[7];
        upDownP = _data[8];
        PER     = _data[19];
        PER_4Season = _data[21];

    }


    public String GetCode() {
        return code;
    }

    public String GetName() {
        return name;
    }

    public String GetDate() {
        return date;
    }

    public String GetOpen() {
        return open;
    }

    public String GetHigh() {
        return high;
    }

    public String GetLow() {
        return low;
    }

    public String GetClose() {
        return close;
    }

    public String GetUpDown() {
        return upDown;
    }

    public String GetUpDownP() {
        return upDownP;
    }

    public String GetPER() {
        return PER;
    }

    public String GetPER_4S() {
        return PER_4Season;
    }

    private String code;
    private String name;
    private String date;

    private String open;
    private String high;
    private String low;
    private String close;

    private String upDown;
    private String upDownP;

    private String PER;
    private String PER_4Season;

    private float GetFloat(String _in)
    {
        if (_in.isEmpty())
            return 0;

        return Float.parseFloat(this.close);
    }

//    @Override
//    public int compareTo(Object o)
//    {
//        StockHolder sh = (StockHolder) o;
//
//        int result = 0;
//        float self, another;
//
//        // 防空字串
//        self = GetFloat(this.close);
//        another = GetFloat(sh.close);
//
//        //Log.d("auau compare", this.close + " vs " + sh.close);
//
//        // 大到小
//        if (self > another)
//            result = -1;
//        else if(self < another)
//            result = 1;
//
//        return result;
//    }


} //end of class
