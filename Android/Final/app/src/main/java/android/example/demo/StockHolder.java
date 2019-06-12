package android.example.demo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class StockHolder implements Serializable {

    private static final long serialVersionUID = 2333;

    public StockHolder(String[] _data)
    {
        code        = _data[0];
        name        = _data[1];
        date        = _data[2];
        open        = _data[3];
        high        = _data[4];
        low         = _data[5];
        close       = _data[6];
        upDown      = _data[7];
        upDownP     = _data[8];
        amplitude   = _data[9];
        volume_num  = _data[10];
        deal_num    = _data[11];
        deal_price  = _data[12];
        goban       = _data[16];
        mk_value    = _data[17];
        mk_percent  = _data[18];
        PER         = _data[19];
        PBR         = _data[20];
        PER_4Season = _data[21];
        turnover    = _data[22];

    }

    public String GetValueByKey(String _key)
    {
        switch(_key)
        {
            case "CODE":
                return code;

            case "OPEN":
                return open;

            case "CLOSE":
                return close;

            case "HIGH":
                return high;

            case "LOW":
                return low;

            case "UP_DOWN":
                return upDown;

            case "UP_DOWN_PERCENT":
                return upDownP;

            case "PER":
                return PER;

            case "PER_4S":
                return PER_4Season;

            default:
                return "Key Error!";
        }
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

    public String GetVolume_num() {
        return volume_num;
    }

    public String GetTurnover() {
        return turnover;
    }

    public String GetAmplitude() {
        return amplitude;
    }

    public String GetDeal_num() {
        return deal_num;
    }

    public String GetDeal_price() {
        return deal_price;
    }

    public String GetGoban() {
        return goban;
    }

    public String GetMk_value() {
        return mk_value;
    }

    public String GetMk_percent() {
        return mk_percent;
    }

    public String GetPBR() {
        return PBR;
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
    private String amplitude;   // 震幅(%)
    private String volume_num;  // 成交量
    private String deal_num;    // 成交筆數
    private String deal_price;  // 成交金額(千)
    private String goban;       // 股本
    private String mk_value;    // 市值
    private String mk_percent;  // 市值
    private String PBR;         // 淨值比
    private String turnover;    // 周轉率


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
