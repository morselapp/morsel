import json
import requests

file_path = "/Users/kunalsingh.k/Downloads/Hoodi-first-cut.json"
url = "http://localhost:9200/shop_directory/shop/"

file = open(file_path).read()
shopList = json.loads(file)

count = 4
for shop in shopList:
	response = requests.post(url+str(count), data=json.dumps(shop))
	count = count + 1
	print response.json()

