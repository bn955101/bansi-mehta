import os
import csv
from constants import columnsUsedInCounting

def combineAllRowFiles(outFilePaths: [], combinedFilePath):
    outText = ""

    # Iterating over input list of file paths
    for file in outFilePaths:
        # fetching all files in the file path
        listOfFiles = os.listdir(file)

        # iterating over list of files
        for fileName in listOfFiles:
            # reading that file
            filePath = file + fileName
            with open(filePath, mode = 'r', newline='', encoding='utf-8') as fileIn:
                reader = csv.reader(fileIn)
                # iterating over all rows
                for row in reader:
                    # iterating over columns that are to be read
                    for col in columnsUsedInCounting:
                        # storing that column value to one list
                        outText = outText + row[col]
                fileIn.close()


    fileOut = open(combinedFilePath, mode = 'w', newline='', encoding='utf-8')
    fileOut.write(outText)
    fileOut.close()

if __name__ == '__main__':
    combineAllRowFiles(
        [
            "D:/Data warehousing 5408/assignment_3/Stream/", "D:/Data warehousing 5408/assignment_3/Search/"
        ],
        "D:/Data warehousing 5408/assignment_3/combine.txt"
    )
