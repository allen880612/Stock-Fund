import requests
import json 
import time
import pandas as pd

class API_GET_DATE_P:
    def __init__(self,token):
        self.token = token
    def api_get_edate_p(self):
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'BUTe-12747b'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)
        #判斷是否成功
        if (data_res.status_code==200): 
            #解析與顯示資料
            try:
                data=json.loads(data_res.text)
                args = data.get('Data')
                return args
            except:
                try:
                    data=json.loads(data_res.text)
                    args = data.get('Data')
                    return args
                except:
                    return False
        else:
            return False
    def api_get_now_p(self,code):
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'A2-12454a/'+str(code)+'/1'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)
        #判斷是否成功
        if (data_res.status_code==200): 
            #解析與顯示資料
            try:
                data=json.loads(data_res.text)
                args = data.get('Data')
                return args[0][6]
            except:
                try:
                    data=json.loads(data_res.text)
                    args = data.get('Data')
                    return args[0][6]
                except:
                    return 0
        else:
            return False
    def api_get_past_p(self,code,date):
        data_url = 'https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'date/'+str(date)+'/BUTe-12747b'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)
        #判斷是否成功
        if (data_res.status_code==200): 
            #解析與顯示資料
            try:
                data=json.loads(data_res.text)
                args = 0.0000000
                arg = ""
                for arg in data.get('Data'):
                    if str(arg[0][0]) == str(code):
                        args = arg[0][6]
                        break
                return args
            except:
                try:
                    data=json.loads(data_res.text)
                    args = 0.000000
                    arg = ""
                    for arg in data.get('Data'):
                        if str(arg[0][0]) == str(code):
                            args = arg[0][6]
                            break
                    return args
                except:
                    return 0
        else:
            return False
