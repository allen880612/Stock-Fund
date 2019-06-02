import requests
import json 
import time
import pandas as pd
import sys 
sys.path.append('../controller')
sys.path.append('../model')
import API_GET_TOKEN as AGT
import strtategy as strategy

def req_stg(token):

    
    stg2 = strategy.strategy(token)

    
    rep = False
    rep = stg2.api_get_strategy_positive()
   
    
    if rep and rep != "" and rep != "No_stock":
        server_post_data = ""
        '''
        for server_post_data in rep:
            print(server_post_data)
            server_url ='https://ntutwebtest.000webhostapp.com/Untitled-4/controller/handel.php'
            server_info = requests.request('POST', server_url, data=json.dumps(server_post_data, ensure_ascii=False).encode('utf-8'), headers={"Content-type": "application/json"})
            print(server_info)
        '''
    elif rep == "No_stock":
        print("No stock")
    else:
        print("Error1")
        return False


get_token = AGT.API_GET_TOKEN(1)
token_res = get_token.api_post()
if token_res:
    print("ok,do next.")
    rep_data2 = req_stg(token_res)

