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

def req_stg2(token):

    
    stg2 = strategy.strategy2(token)
    
    rep_b = False
    rep_s = False
    
    rep_b = stg2.api_get_strategy2_buy()

    if rep_b and rep_b != "" and rep_b != "No_stock":
        print("買入:")
        server_post_data = ""
        for server_post_data in rep_b:
            print(server_post_data)
            #server_url ='https://ntutwebtest.000webhostapp.com/Untitled-4/controller/handel.php'
            #server_info = requests.request('POST', server_url, data=json.dumps(server_post_data, ensure_ascii=False), headers={"Content-type": "application/json"})
            #print(server_info)
    elif rep_b == "No_stock":
        print("No stock")
    else:
        print("Error1")
        return False
    
    
    
    '''
    #Test
    fadachai = []
    fadachai.append({'code': '1310', 'type': '0', 'date': '20190523'})
    fadachai.append({'code': '2597', 'type': '0', 'date': '20190523'})
    fadachai.append({'code': '4429', 'type': '1', 'date': '20190523'})
    fadachai.append({'code': '4564', 'type': '0', 'date': '20190523'})
    fadachai.append({'code': '6412', 'type': '0', 'date': '20190523'})
    rep_b = fadachai
    '''

    rep_s = stg2.api_get_strategy2_sell(fadachai)
    print(rep_s)
    if rep_s and rep_s != "" and rep_b != "No_action":
        print("賣出:")
        server_post_data = ""
        for server_post_data in rep_s:
            print(server_post_data)
            """
            server_url ='https://ntutwebtest.000webhostapp.com/Untitled-4/controller/handel_hold_stock.php'
            server_info = requests.request('POST', server_url, data=json.dumps(server_post_data, ensure_ascii=False), headers={"Content-type": "application/json"})
            print(server_info)
            """
    elif rep_s == "No_action":
        print("No action")
    else:
        print("Error2")
        return False
    
    if rep_b and rep_s:
        re = rep_b+rep_s
        return  re

get_token = AGT.API_GET_TOKEN(1)
token_res = get_token.api_post()
if token_res:
    print("ok,do next.")
    rep_data2 = req_stg2(token_res)

