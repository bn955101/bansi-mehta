import csv
import os
import math

filePathForCold = []

def TFIDF(streamFilePath, searchFilePath):
    streamFileNames = os.listdir(streamFilePath)
    searchFileNames = os.listdir(searchFilePath)
    totalFiles = len(streamFileNames) + len(searchFileNames)
    keyWords = ['flu', 'cold', 'snow']
    output = []
    for key in keyWords:
        word = []
        word.append(key)
        fileCounter = 0
        if len(streamFileNames):
            for fileName in streamFileNames:
                filePath = streamFilePath + '/' + fileName
                with open(filePath, mode='r', encoding='utf-8') as fileIn:
                    reader = csv.reader(fileIn, quotechar='"', delimiter=',')
                    for line in reader:
                        text = line[3]
                        listOfWords = text.split()
                        if key in listOfWords:
                            if key == 'cold':
                                filePathForCold.append(filePath)
                            fileCounter += 1
                            break
        if len(searchFileNames):
            for fileName in searchFileNames:
                filePath = searchFilePath + '/' + fileName
                with open(filePath, mode='r', encoding='utf-8') as fileIn:
                    reader = csv.reader(fileIn, quotechar='"', delimiter=',')
                    for line in reader:
                        text = line[3]
                        listOfWords = text.split()
                        if key in listOfWords:
                            if key == 'cold':
                                filePathForCold.append(filePath)
                            fileCounter += 1
                            break
        word.append(fileCounter)
        word.append(totalFiles/fileCounter)
        word.append(math.log((totalFiles/fileCounter), 10))
        output.append(word)
    return output

def relativeFrequency(outputFilePath):
    output = []
    if len(filePathForCold):
        for path in filePathForCold:
            totalWords = 0
            keyWord = 0
            row = []
            row.append(path)
            with open(path, mode='r', encoding='utf-8') as fileIn:
                reader = csv.reader(fileIn, quotechar='"', delimiter=',')
                for line in reader:
                    text = line[3]
                    listOfWords = text.split()
                    totalWords += len(listOfWords)
                    if 'cold' in listOfWords:
                        keyWord += 1
            row.append(totalWords)
            row.append(keyWord)
            row.append(keyWord/totalWords)
            output.append(row)

    if len(output):
        with open(outputFilePath, mode='w', encoding='utf-8') as fileOut:
            writer = csv.writer(fileOut)
            greatestIndex = 0
            greatestValue = output[0][3]
            for lineIndex in range(len(output)):
                writer.writerow(output[lineIndex])
                if float(output[lineIndex][3]) > float(greatestValue):
                    greatestValue = output[lineIndex][3]
                    greatestIndex = lineIndex
        print("Highest relative Frequeny is in file " + output[greatestIndex][0] + " and is: " + str(output[greatestIndex][3]))

def writeTDIDFOutput(outputFilePath, fileContent):
    with open(outputFilePath, mode='w', encoding='utf-8') as fileOut:
        writer = csv.writer(fileOut)
        for line in fileContent:
            writer.writerow(line)

if __name__ == '__main__':
    streamFilePath = input("Enter Clean Stream File Path: ")
    searchFilePath = input("Enter Clean Search File Path: ")
    tfidfWriteFilePath = input("Enter File Path For TFIDF Output: ")
    relativeFrequencyFilePath = input("Enter File Path For Relative Frequency Output: ")
    output = TFIDF(streamFilePath, searchFilePath)
    writeTDIDFOutput(tfidfWriteFilePath, output)
    relativeFrequency(relativeFrequencyFilePath)