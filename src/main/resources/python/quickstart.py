from __future__ import print_function
import pickle
import os.path
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
from pprint import pprint
from googleapiclient import discovery

# If modifying these scopes, delete the file token.pickle.
SCOPES = ['https://www.googleapis.com/auth/spreadsheets']

# The ID and range of a sample spreadsheet.
SPREADSHEET_ID = '17Gi72bVavI5Huj32h11-KeZsxfpecwtfZvaiqLu4IOQ'
RANGE_NAME = 'Test!A1'
TICKETS_PAGE_ID = '195260292'

# Paths
PATH_TO_CSV = '/Users/ruslanlevytskyi/Downloads/JIRA.csv'
PATH_TO_TOKEN = os.path.dirname(os.path.realpath(__file__))+'/token.pickle'
PATH_TO_CREDS = os.path.dirname(os.path.realpath(__file__))+'/credentials.json'


def main():
    service = authorisation()
    pasteCsv(PATH_TO_CSV, service)


def authorisation():
    creds = None
    # The file token.pickle stores the user's access and refresh tokens, and is
    # created automatically when the authorization flow completes for the first
    # time.
    if os.path.exists(PATH_TO_TOKEN):
        with open(PATH_TO_TOKEN, 'rb') as token:
            creds = pickle.load(token)
    # If there are no (valid) credentials available, let the user log in.
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(PATH_TO_CREDS, SCOPES)
            creds = flow.run_local_server()
        # Save the credentials for the next run
        with open(PATH_TO_TOKEN, 'wb') as token:
            pickle.dump(creds, token)

    service = discovery.build('sheets', 'v4', credentials=creds)
    return service


def pasteCsv(csvFile, srvc):

    with open(csvFile, 'r') as f:
        csvContents = f.read()
    body = {
        'requests': [{
            'pasteData': {
                "coordinate": {
                    "sheetId": TICKETS_PAGE_ID,
                    "rowIndex": 0,
                    "columnIndex": 0,
                },
                "data": csvContents,
                "type": 'PASTE_NORMAL',
                "delimiter": ',',
            }
        }]
    }
    request = srvc.spreadsheets().batchUpdate(spreadsheetId=SPREADSHEET_ID, body=body)
    response = request.execute()

    pprint(response)
    return response


if __name__ == '__main__':
    main()