import requests
import json 
import time
import pandas as pd
import numpy as np
from pandas_datareader import data

class API_GET_DATE_AP:
    def __init__(self,token):
        self.token = token
        
    def api_get_date_5days_ap(self,code):
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'A7-12478a/'+str(code)+'/200'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)
        #判斷是否成功
        if (data_res.status_code==200): 
            #解析與顯示資料
            try:
                data=json.loads(data_res.text)
                args = data.get('Data')
                return args[0][3]
            except:
                try:
                    data=json.loads(data_res.text)
                    args = data.get('Data')
                    return args[0][3]
                except:
                    return 0
        else:
            return False
    def api_get_200days_ap(self,code,date2):
        stock = str(code) + '.TW'
        try:
            data_ap = data.DataReader(stock, "yahoo", "2000-01-01",date2)
            df = data_ap.tail(240)
            c = df['Close']
            dd = 0.00
            sum1 = 0.00
            for dd in c:
                sum1 += dd
            re = sum1/len(c)
            return re
        except:
            try:
                data_ap = data.DataReader(stock+'O', "yahoo", "2000-01-01",date2)
                df = data_ap.tail(240)
                c = df['Close']
                dd = 0.00
                sum1 = 0.00
                for dd in c:
                    sum1 += dd
                re = sum1/len(c)
                return re
            except:
                return 0
