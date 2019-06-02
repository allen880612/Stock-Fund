#-*- coding=utf-8 -*-
import pandas as  pd

pd.set_option('display.unicode.ambiguous_as_wide', True)
pd.set_option('display.unicode.east_asian_width', True)
#data = pd.read_csv("l_list.csv", encoding="gb18030")


data = pd.read_csv("l_list.csv", index_col = "ID")
print(data)
stock_dataFrame = pd.DataFrame(data)
print(stock_dataFrame)


'''
data_None_Index = pd.read_csv("None_Index.csv", header = None)
#print(data_None_Index)
column_index = [u"代號", u"公司", u"現價"]
stock_withoiut_Index = pd.DataFrame(data_None_Index.values, columns = column_index)
print(stock_withoiut_Index)
'''
