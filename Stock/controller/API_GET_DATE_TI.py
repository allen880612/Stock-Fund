import requests
import json 
import time
import pandas as pd
import numpy as np

class API_GET_DATE_TI:
    def __init__(self,token):
        self.token = token
    def api_get_date_ti_rsi5(self,code):
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'A7-12478a/'+str(code)+'/1'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)
        #判斷是否成功
        if (data_res.status_code==200): 
            #解析與顯示資料
            try:
                data=json.loads(data_res.text)
                args = data.get('Data')
                return args[0][5]
            except:
                try:
                    data=json.loads(data_res.text)
                    args = data.get('Data')
                    return args[0][5]
                except:
                    return 0
        else:
            return False