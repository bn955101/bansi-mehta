allText = sc.textFile("/home/flute_bansi/twitterOutput/combine.txt")
flatenText = allText.flatMap(lambda word: word.split(" "))
mappedWords = flatenText.map(lambda word: (word, 1))
mapping = mappedWords.reduceByKey(lambda  key, frequency: key + frequency)
mapping.saveAsTextFile("/home/flute_bansi/twitterOutput/count.txt")

searchWords = ["flu", "snow", "emergency"]
dictSearchWords = {}
for word in searchWords:
    dictSearchWords[word] = 0
print(dictSearchWords)

for item in mapping.collect():
    for word in dictSearchWords:
        if item[0] == word:
            dictSearchWords[item[0]] = item[1]

print(dictSearchWords);