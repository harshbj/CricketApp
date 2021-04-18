import requests
import json
import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore
from datetime import datetime
import time
import numpy as np


def save(collection_id, document_id, data):
    db.collection(collection_id).document(document_id).set(data)


if __name__ == "__main__":
    cred = credentials.Certificate("/Users/harshjasani/Desktop/ISC/automattion/ipl-2021.json")

    firebase_admin.initialize_app(cred)
    db = firestore.client()
    # while True:
    for i in range(58,114):
        # time.sleep(10)
        headers = {
            'authority': 'hs-consumer-api.espncricinfo.com',
            'pragma': 'no-cache',
            'cache-control': 'no-cache',
            'sec-ch-ua': '"Google Chrome";v="89", "Chromium";v="89", ";Not\\"A\\\\Brand";v="99"',
            'sec-ch-ua-mobile': '?1',
            'user-agent': 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Mobile Safari/537.36',
            'accept': '*/*',
            'origin': 'https://www.espncricinfo.com',
            'sec-fetch-site': 'same-site',
            'sec-fetch-mode': 'cors',
            'sec-fetch-dest': 'empty',
            'referer': 'https://www.espncricinfo.com/',
            'accept-language': 'en-GB,en-US;q=0.9,en;q=0.8',
        }

        params = (
            ('seriesId', '1249214'),
            ('matchId', '12540'+str(i)),
        )

        response = requests.get('https://hs-consumer-api.espncricinfo.com/v1/pages/match/home', headers=headers, params=params)

        # print(response.text)
        j = json.loads(response.text)
        # print(j)
        if j['match']['teams'][0]['isLive']:
            score = j['match']['teams'][0]['score']
            over = j['match']['teams'][0]['scoreInfo']
        else:
            score = j['match']['teams'][1]['score']
            over = j['match']['teams'][1]['scoreInfo']
        d = {
            "match": {
                "match_name": j['content']['matchPlayers']['teamPlayers'][0]['team']['abbreviation'] + ' vs ' + j['content']['matchPlayers']['teamPlayers'][1]['team']['abbreviation'],
                "start_time": j['match']['startTime'],
                "state": j['match']['state']
            },
            "teams": [
                {
                    "id": j['content']['matchPlayers']['teamPlayers'][0]['team']['id'],
                    "name": j['content']['matchPlayers']['teamPlayers'][0]['team']['abbreviation'],
                    "logo": 'https://espncricinfo.com'+j['content']['matchPlayers']['teamPlayers'][0]['team']['image']['url']
                },
                {
                    "id": j['content']['matchPlayers']['teamPlayers'][1]['team']['id'],
                    "name": j['content']['matchPlayers']['teamPlayers'][1]['team']['abbreviation'],
                    "logo": 'https://espncricinfo.com'+j['content']['matchPlayers']['teamPlayers'][1]['team']['image']['url']
                }
            ],
            "team_players": [
                {
                        "players": [i['player']['longName'] for i in j['content']['matchPlayers']['teamPlayers'][0]['players']],
                },
                {
                    "players": [i['player']['longName'] for i in j['content']['matchPlayers']['teamPlayers'][1]['players']],
                }
            ],
            "recent_ball_stats": {
                    "over": over,
                    "run": j['content']['recentBallCommentary']['ballComments'][0]['batsmanRuns'],
                    "comment": j['content']['recentBallCommentary']['ballComments'][0]['title'],
                    "score": score
            },

        }
        print(d)

        time_1 = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

        save('collection', document_id=d['match']['match_name'], data=d)
