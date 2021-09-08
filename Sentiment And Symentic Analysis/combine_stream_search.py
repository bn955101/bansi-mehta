# Combine cleaned stream and search data and create a csv file
import csv
import os

columnName = ['Serial No', 'Tweet Text', 'Matched Words', 'Polarity']

allTweets = []

def readCleanTweets(tweetStreamFilePath):
    listOfFiles = os.listdir(tweetStreamFilePath)

    for file in listOfFiles:
        filePath = tweetStreamFilePath + '/' +file
        index = 0
        with open(filePath, mode = 'r', encoding = 'utf-8') as fileIn:
            reader = csv.reader(fileIn, quotechar='"', delimiter=',')
            for line in reader:
                if index > 0:
                    allTweets.append(line[3])
                index += 1

def writeCleanTweets(outputFilePath):
    with open(outputFilePath, mode = 'w', encoding = 'utf-8') as fileOut:
        writer = csv.writer(fileOut)
        writer.writerow(columnName)
        if len(allTweets) > 0:
            for tweetIndex in range(len(allTweets)):
                row = []
                row.append(tweetIndex + 1)
                row.append(allTweets[tweetIndex])
                writer.writerow(row)

if __name__ == '__main__':
    cleanStreamedFilePath = input("Enter clean stream file path:")
    cleanSearchFilePath = input("Enter clean search file path:")
    outputFilePath = input("Enter output file path:")
    readCleanTweets(cleanStreamedFilePath)
    readCleanTweets(cleanSearchFilePath)
    writeCleanTweets(outputFilePath)
