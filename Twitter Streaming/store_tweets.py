import csv

class StoreTweets:
    def __init__(self, filePath, fileName, fileIndex, tweetCount):
        self.outFilePath = filePath
        self.outFileName = fileName
        self.fileIndex = fileIndex
        self.tweetCount = tweetCount

    def createFile(self):
        filePath = self.outFilePath + self.outFileName + str(self.fileIndex) + ".csv"
        try:
            with open(filePath, mode = 'w', newline='', encoding='utf-8') as fileOut:
                writer = csv.writer(fileOut)
        except BaseException as e:
            print(str(e))
        finally:
            fileOut.close()

    def writeInFile(self, row):
        filePath = self.outFilePath + self.outFileName + str(self.fileIndex) + ".csv"
        try:
            with open(filePath, mode = 'a', newline='', encoding='utf-8') as fileOut:
                writer = csv.writer(fileOut)
                writer.writerow(row)
        except BaseException as e:
            print(str(e))
        finally:
            fileOut.close()