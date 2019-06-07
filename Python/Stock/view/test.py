import requests
import json 
import time
import pandas as pd
import sys 
sys.path.append('../controller')
sys.path.append('../model')
'''
0 做多買入
1 做空買入
2 做多平倉
3 做空平倉
4 止損賣出
5 持倉
'''
dict_signal= {}
dict_signal[0] = "做多買入"
dict_signal[1] = "做空買入"
dict_signal[2] = "做多平倉"
dict_signal[3] = "做空平倉"
dict_signal[4] = "止損賣出"
dict_signal[5] = "持倉"

    

def getStock(_signal):
    print(dict_signal[_signal])
    data_url ='https://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal='
    server_url = data_url + str(_signal) 
    
    data_res = requests.request('POST', server_url, data="", headers="")
    print(data_res)
            
    data_get = data_res.text
    if data_res=="":
        print("Nob")
        return
    
    data = json.loads(data_get)
    args = data.get('Data')
    
    if (args == "" or args == None):
        return
    
    print("date : ", data["Date"])
    print("Title : ", data["Title"])
    for arg in args:
        print(arg)

def GetStocks( _code):
    datestr = "20190606"
    r = requests.post("http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=" + datestr + "&stockNo=" +_code)
    print (r)
    data = json.loads(r.text)
    print(data)

def ReadCodes():
    stocks = pd.read_csv("stock.csv")
    codes = []
    for i in range( len(stocks["股票代號"]) ):
        code_str = str(stocks.loc[i, "股票代號"])
        codes.append(code_str);
        
    return codes;

GetStocks("1101")

'''
codes = ReadCodes()
for code in codes:
    GetStocks(code)
    time.sleep(5)
'''    
    
'''        
#持倉是三小，先不做
for i in range(5):
    getStock(i)
    print("-------------------")
'''

