package android.example.demo;

public class StockHolder {
    public StockHolder(String _code, String _name, String _price, String _date, String _type)
    {
        code = _code;
        name = _name;
        price = _price;
        date = _date;
        type = _type;
        action = Type2Action(_type);
    }

    private String Type2Action(String _type)
    {
        switch(_type)
        {
            case "0":
                return "做多買入";
            case "1":
                return "做空買入";
            case "2":
                return "做多平倉";
            case "3":
                return "做空平倉";
            case "4":
                return "止損賣出";
            case "5":
                return "持倉";
            default:
                return "不做操作";
        }
    }

    public String name;
    public String date;
    public String code;
    public String price;
    public String type;
    public String action;
}
