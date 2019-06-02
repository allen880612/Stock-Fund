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
            
        
#持倉是三小，先不做
for i in range(5):
    getStock(i)
    print("-------------------")

