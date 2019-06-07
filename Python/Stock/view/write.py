

import requests
import json 
import time
import pandas as pd
import sys 
sys.path.append('../controller')
sys.path.append('../model')
import API_GET_TOKEN as AGT
import strtategy2 as strategy2
import strtategy_demo as strategy

def ReadResours():
    stocks = pd.read_csv("stock.csv")

    print("<string-array name=\"list_fund_pos_stock\">")
    
    for i in range( len(stocks["股票代號"]) ):
        code_str = str(stocks.loc[i, "股票代號"])
        print("<item>" + code_str + "</item>")


    print("</string-array>")

    '''
    <string-array name="list_fund_pos_stock">
        <item>積極 股票型 1</item>
        <item>積極 股票型 2</item>
        <item>積極 股票型 3</item>
    </string-array>
    '''

def CSV2JSON():
    df = pd.read_csv("stock.csv")

    stocks = []
    
    for i in range(len(df)):
        #print(df.loc[i])
        
        stock = []
        for data in df.loc[i]:
            stock.append(data)
            
        stocks.append(stock)
    print(stocks)
        
       

    '''
    {data : [ [1101, 台泥, ...], [] }
    '''

CSV2JSON()

