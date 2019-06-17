import requests
import json 
import time
import pandas as pd
import sys 
sys.path.append('../controller')
sys.path.append('../model')
import API_GET_DATE_P as AGDP
import API_GET_DATE_AP as AGDAP
import API_GET_DATE_TI as AGDTI
import datetime
import csv
from bs4 import BeautifulSoup

class strategy:
    def __init__(self,token):
        self.token = token
        #self.toCompany = {}
        
    def api_get_strategy_positive(self):
        
        # 設定日期
        tdt = datetime.datetime.now()
        y = str(tdt.year)
        m = str(tdt.month)
        if tdt.month < 10:
            m = '0'+str(tdt.month)
        d = str(tdt.day)
        if tdt.day < 10:
            d = '0'+str(tdt.day)
        dt = y+m+d
        dt2 = y+'-'+m+'-'+d
        filter_condition = []
        
        # 設置url
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        
        #url = data_url + "E1-13000b"       #ETF 指標
        #url = data_url + "L1-352433b"      #基金 年化報酬率
        url = data_url + "BABA0010-14644b"  #基金 基本資料
        #url = data_url + "L1-352098b"      #基金 基本資料 (403

        #url = data_url + "F8-310631b"      #基金 規模 (403

        #url = data_url + "L1-352100a"      #基金 beta
        
        #url += "/" + "00965469" + "/1"
        #url = "https://owl.cmoney.com.tw/OwlApi/api/v2/json/L1-352100a/00974066/1"
        
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        #data_res = requests.request('GET', url, headers = data_headers)

        data_res = requests.request('GET', url, headers = data_headers)
        
        print ("status cade = " + str(data_res.status_code))
        
        #判斷是否成功
        if (data_res.status_code==200):
            #解析與顯示資料
            data = json.loads(data_res.text)

            title = data.get('Title')
            print(title);
            args = data.get('Data')
            print("length = " + str(len(args)))
            
            #print(args)
            
            self.handle(args[0], dt)
            
            return
        
            for arg in args:
                #print( str(arg[0]) + " " + str(arg[1]))
                self.handle(arg, dt)
            
                
    # 以權重衡量基金
    def handle(self, data, dt):
        # 確定是有效基金資料
        '''
        if (not data[18].strip()):
            return ""
        '''

        print(data)
        print("==============================================================================")
        '''
        code = data[0]
        name = data[1]
        #fType = data[7]
        fType = data[13]
        founding_time = data[18]
        pa = data[19]
        x1 = data[25]
        x2 = data[26]
        area = data[28]
        risk = data[30]
        print(code, name, founding_time, x1, x2, area, risk)
        '''
        





    
