import csv

def getAllTweets(filePath):
    allTweets = []
    with open(filePath, mode='r', encoding='utf-8') as fileIn:
        index = 0
        reader = csv.reader(fileIn, quotechar='"', delimiter=',')
        for line in reader:
            if index > 0:
                allTweets.append(line)
            index += 1
    return allTweets

def createBagOfWords(filePath):
    allTweets = getAllTweets(filePath)
    bagOfWords = []
    if len(allTweets) > 0:
        for tweetIndex in range(len(allTweets)):
            bagOfWord = {}
            wordKeys = []
            words = allTweets[tweetIndex][1].split()
            if len(words) > 0:
                for word in words:
                    if word in wordKeys:
                        bagOfWord[word] = bagOfWord[word] + 1
                    else:
                        wordKeys.append(word)
                        bagOfWord[word] = 1
            print(bagOfWord)
            bagOfWords.append(bagOfWord)
    return bagOfWords

# positive-words.txt", Gist, 2021. [Online].
# Available: https://gist.github.com/mkulakowski2/4289437. [Accessed: 11- Apr- 2021].
def getAllPositiveWords():
    fileRead = open('D:/Data warehousing 5408/assignment_4/positive_words.txt', mode = 'r')
    fileContent = fileRead.read()
    fileRead.close()
    return fileContent.split()

# negative-words.txt", Gist, 2021. [Online].
# Available: https://gist.github.com/mkulakowski2/4289441. [Accessed: 11- Apr- 2021].
def getAllNegativeWords():
    fileRead = open('D:/Data warehousing 5408/assignment_4/negative_words.txt', mode = 'r')
    fileContent = fileRead.read()
    fileRead.close()
    return fileContent.split()

def assignPolarity(bagOfWords, filePath):
    allTweets = getAllTweets(filePath)
    allPositiveWords = getAllPositiveWords()
    allNegativeWords = getAllNegativeWords()
    if len(bagOfWords) > 0:
        index = 0
        for bagOfWord in bagOfWords:
            positiveWords = 0
            negativeWords = 0
            matchedWords = []
            keys = list(bagOfWord.keys())
            if len(keys) > 0:
                for key in keys:
                    if key in allPositiveWords:
                        positiveWords += bagOfWord[key]
                        matchedWords.append(key)
                    elif key in allNegativeWords:
                        negativeWords += bagOfWord[key]
                        matchedWords.append(key)

            allTweets[index][2] = ', '.join(matchedWords)

            if positiveWords > negativeWords:
                allTweets[index][3] = 'Positive'
            elif negativeWords > positiveWords:
                allTweets[index][3] = 'Negative'
            else:
                allTweets[index][3] = 'Neutral'

            index += 1
    return allTweets

def writeInFile(filePath, fileContent):
    columnName = ['Serial No', 'Tweet Text', 'Matched Words', 'Polarity']
    with open(filePath, mode = 'w', encoding = 'utf-8') as fileOut:
        writer = csv.writer(fileOut)
        writer.writerow(columnName)
        writer.writerows(fileContent)

if __name__ == '__main__':
    filePath = input("Enter input file path: ")
    bagOfWords = createBagOfWords(filePath)
    fileContent = assignPolarity(bagOfWords, filePath)
    writeInFile(filePath, fileContent)