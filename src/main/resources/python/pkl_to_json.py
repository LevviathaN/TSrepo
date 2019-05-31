
#!/usr/local/bin/python3
'''
Convert a pkl file into json file
'''
import sys
import os
import _pickle as pickle
import json


def convert_dict_to_json(file_path):
    with open(file_path, 'rb') as fpkl:
        with open('token.json', 'w') as fjson:
            json.dump(pickle.load(fpkl), fjson)


def main():
    if sys.argv[1] and os.path.isfile(sys.argv[1]):
        file_path = sys.argv[1]
        print("Processing %s ..." % file_path)
        convert_dict_to_json(file_path)
    else:
        print("Usage: %s abs_file_path" % (__file__))


if __name__ == '__main__':
    main()