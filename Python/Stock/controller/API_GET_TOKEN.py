import requests import json import time import pandas as pd

class API_GET_TOKEN():
    def __init__(self,get):
        self.get = get
    
    def api_post(self):
        #帳號設定------------
        appid='20190507130014440' #cycu.owl@gmail.com
        appsecret='fffee680708411e9aa98000c29beef84'
        #連線---------------
        token_url='https://owl.cmoney.com.tw/OwlApi/auth'
        data_url='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        #組連線參數--------------
        token_params = 'appId='+appid+'&appSecret='+appsecret
        token_headers = {'content-type': 'application/x-www-form-urlencoded'}
        #步驟一:驗證----------
        token_res = requests.request('POST', token_url, data=token_params, headers=token_headers)
        if (token_res.status_code==200):
            token_data=json.loads(token_res.text)
            token=token_data.get('token')
            return token
        else:
            return False
