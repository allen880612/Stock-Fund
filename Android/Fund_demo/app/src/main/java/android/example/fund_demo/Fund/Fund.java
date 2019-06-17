package android.example.fund_demo.Fund;

import java.io.Serializable;

public class Fund implements Serializable{

    private static final long serialVersionUID = 233333;

    public Fund (String[] _input)
    {
        SetCode(_input[0]);
        SetName(_input[1]);
        //SetType(_input[13]);
        SetScale(_input[19]);
        SetRisk(_input[30]);
    }

    public String GetRisk() {
        return risk;
    }
    public void SetRisk(String risk) {
        this.risk = risk;
    }

    public String GetReturn_1y() {
        return return_1y;
    }
    public void SetReturn_1y(String return_1y) {
        this.return_1y = return_1y;
    }

    public String GetReturn_2y() {
        return return_2y;
    }
    public void SetReturn_2y(String return_2y) {
        this.return_2y = return_2y;
    }

    public String GetBeta_1y() {
        return beta_1y;
    }
    public void SetBeta_1y(String beta_1y) {
        this.beta_1y = beta_1y;
    }

    public String GetBeta_2y() {
        return beta_2y;
    }
    public void SetBeta_2y(String beta_2y) {
        this.beta_2y = beta_2y;
    }

    public String GetScale() {
        return scale;
    }
    public void SetScale(String scale) {
        this.scale = scale;
    }

    public String GetName() {
        return name;
    }
    public void SetName(String name) {
        this.name = name;
    }

    public String GetCode() {
        return code;
    }
    public void SetCode(String code) {
        this.code = code;
    }

    public String GetType() {
        return type;
    }
    public void SetType(String type) {
        this.type = type;
    }

    private String code;
    private String name;
    private String risk;
    private String return_1y;
    private String return_2y;
    private String beta_1y;
    private String beta_2y;
    private String scale;
        private String type;
}
