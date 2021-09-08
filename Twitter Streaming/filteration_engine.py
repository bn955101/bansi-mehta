from constants import columnsToBeCleaned
from constants import columnNames
from clean import Patterns
import os
import csv
from pymongo import MongoClient

def cleanTweets(outFilePath, cleanOutFilePath):
    pattern = Patterns()

    # fetch all the files of that folder
    listOfFiles = os.listdir(outFilePath)

    # iterate over all files
    for fileName in listOfFiles:
        filePath = outFilePath + fileName
        cleanFilePath = cleanOutFilePath + 'clean' + fileName
        cleanRow = []

        # open file in read mode
        with open(filePath, mode = 'r', newline='', encoding='utf-8') as fileIn:
            reader = csv.reader(fileIn)
            # iterate over each row
            for row in reader:
                # iterate over each column that is to be cleaned
                for col in columnsToBeCleaned:
                    row[col] = pattern.cleanUsingPatterns(row[col])

                # cleaned row
                cleanRow.append(row)
            fileIn.close()

        with open(cleanFilePath, mode = 'w', newline='', encoding='utf-8') as fileOut:
            writer = csv.writer(fileOut)
            writer.writerow(columnNames)
            for data in cleanRow:
                writer.writerow(data)
            fileOut.close()

        print("Cleaned file: " + fileName)
        print("Inserting data in mongoDB for file:  clean" + fileName)
        insertIntoMongoDb(cleanFilePath)
        print("Insertion complete for file: clean" + fileName)

def insertIntoMongoDb(fileToRead):
    client = MongoClient("mongodb+srv://bmehta:B00875640@mongotweet.bvfxi.mongodb.net/test")
    db = client.myMongoTweet
    collection = db.tweets

    with open(fileToRead, mode='r', newline='', encoding='utf-8') as fileIn:
        # Reading csv in the form of json
        reader = csv.DictReader(fileIn)
        for row in reader:
            collection.insert_one(row)
        fileIn.close()


if __name__ == '__main__':
    cleanTweets("D:/Data warehousing 5408/assignment_3/Stream/", "D:/Data warehousing 5408/assignment_3/Clean_Stream/")
    cleanTweets("D:/Data warehousing 5408/assignment_3/Search/", "D:/Data warehousing 5408/assignment_3/Clean_Search/")