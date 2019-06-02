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
from bs4 import BeautifulSoup

class strategy2:
    def __init__(self,token):
        self.token = token
        
    def api_get_strategy2_buy(self):
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
        data_url ='https://owl.cmoney.com.tw/OwlApi/api/v2/json/'
        url = data_url+'BABA0010-14663b'
        #呼叫API
        data_headers = {'authorization': 'Bearer '+self.token}
        data_res = requests.request('GET', url, headers=data_headers)

        print ("status cade = " + str(data_res.status_code))
        
        #判斷是否成功
        if (data_res.status_code==200):
            #解析與顯示資料
            data=json.loads(data_res.text)
            args = data.get('Data')
            print(args)
            for arg in args:
                code = str(arg[0])
                print("股票編號 : " + code)
                g = self.handel1(code,dt,dt2)
                #print(g)
                if g != "":
                    filter_condition.append(g)
            if len(filter_condition)==0:
                return "No_stock"
            else:
                return filter_condition
        else:
            return False
    def handel1(self, code, date, date2):
        #print(get, date, date2)
        obj1 = AGDP.API_GET_DATE_P(self.token)
        obj2 = AGDTI.API_GET_DATE_TI(self.token)
        obj3 = AGDAP.API_GET_DATE_AP(self.token)
        np = obj1.api_get_now_p(code)#股價最新收盤價
        ap = obj3.api_get_200days_ap(code, date2);#股價兩百日均線
        rsi5 = obj2.api_get_date_ti_rsi5(code)#取rsi(5)

        #0->Long_Position,1->Short_Position
        if np==0 or ap == 0 or rsi5 == 0:
            return ""
        if float(np) > float(ap) and float(rsi5) < 10:
            return {"code":code,"type":str(0),"date":str(date)}#做多買入
        elif float(np) < float(ap) and float(rsi5) > 90:
            return {"code":code,"type":str(1),"date":str(date)}#做空買入
        else:
            return ""
        
    def api_get_strategy2_sell(self, args):
        tdt = datetime.datetime.now()
        y = str(tdt.year)
        m = str(tdt.month)
        if tdt.month < 10:
            m = '0'+str(tdt.month)
        d = str(tdt.day)
        if tdt.day < 10:
            d = '0'+str(tdt.day)
        dt = y+m+d
        filter_condition = []
        '''
        data_url ='https://ntutwebtest.000webhostapp.com/Untitled-4/controller/hold_stock.php'
        #呼叫API
        data_res = requests.request('POST', data_url, data="", headers="")
        #print(data_res)
        
        data_get = data_res.text
        #print(data_get)
        
        
        if data_res=="":
            return False
        data=json.loads(data_get)
        args = data.get('Data')
        '''
        
        if args == "":
            return "No_action"
        for arg in args:
            g = self.handel2(arg, dt)
            #print("sell : " + str(g))
            if g != "":
                filter_condition.append(g)
        if len(filter_condition)==0:
                return "No_action"
        else:
            return filter_condition
    def handel2(self, get, date):
        code = str(get["code"])
        sType = str(get["type"])
        
        obj1 = AGDP.API_GET_DATE_P(self.token)
        obj2 = AGDAP.API_GET_DATE_AP(self.token)
        np = obj1.api_get_now_p(code)#股價最新收盤價
        np = float(np)
        pp = obj1.api_get_past_p(code,date)#股票當時購買價
        pp = float(pp)
        ap = obj2.api_get_date_5days_ap(code)#股價五日均線
        ap = float(ap)

        #print("code: " + code + ", type: " + sType)

        if np==0 or pp == 0 or ap == 0:
            return ""		
		#2->Long_Sign_Liquidation,3->Short_Sign_Liquidation,4->Stop_Loss_Liquidation
        np_f = 0.0000000
        np_f = (np*0.001425)+(np*0.003)
        pr = 0.0000000
        pr = (np-np_f)/(pp+(pp*0.001425))
        if float(pr)<0.9 and sType == 0:#作多時股價跌幅達10%
            return {"code":code,"type":str(4),"date":str(date)}#止損賣出
        elif float(pr)>1.1 and sType == 1:#作空時股價漲幅達10%
            return {"code":code,"type":str(4),"date":str(date)}#止損賣出
        elif (float(pr) > 1.15 or float(np) > float(ap)) and sType == 0:#作多時股價漲幅達15%或購買價超越5日均線
            return {"code":code,"type":str(2),"date":str(date)}#做多賣出
        elif (float(pr) < 0.85 or float(np) < float(ap)) and sType == 1:#作空時股價漲幅達15%或購買價低於5日均線
            return {"code":code,"type":str(3),"date":str(date)}#做空賣出
        else:
            return ""

