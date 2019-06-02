import requests
import json 
import time
import pandas as pd 

dict_a = {}
dict_a["text"] = ["25", "23", "26"]
dict_a["num"] = 3

print(dict_a)

json_a = json.dumps(dict_a)
print(json_a)

dict_aa = json.loads(json_a)
print(dict_aa)
