from tweepy import OAuthHandler
from tweepy import Cursor
from constants import filterKeyWords
from store_tweets import StoreTweets
import tweepy
import api_keys as Keys


global tweetCount
global fileIndex
tweetCount = 1
fileIndex = 0

# Automate Getting Twitter Data in Python Using Tweepy and API Access", Earth Data Science - Earth Lab, 2021. [Online].
# Available: https://www.earthdatascience.org/courses/use-data-open-source-python/intro-to-apis/twitter-data-in-python/.
# [Accessed: 12- Mar- 2021].
class SearchData:
    def __init__(self, filePath, fileName):
        self.outFilePath = filePath
        self.outFileName = fileName
        api = self.createConnection()
        self.getTweets(api)

    def createConnection(self):
        auth = OAuthHandler(Keys.apiKey, Keys.apiSecretKey)
        auth.set_access_token(Keys.accessToken, Keys.accessTokenSecret)
        api = tweepy.API(auth)
        return api

    def getTweets(self, api: tweepy.api):
        global  tweetCount
        global  fileIndex
        dateSince = "2021-01-01"
        searchKey = filterKeyWords

        for key in searchKey:
            tweets = Cursor(api.search, q = key, lang = "en", since = dateSince).items(500)
            for tweet in tweets:
                try:
                    row = [str(tweet.created_at), str(tweet.user.name), str(tweet.user.location), str(tweet.text)]
                    if tweetCount == 0 or tweetCount % 100 == 0:
                        # Need to create New file
                        fileIndex = fileIndex + 1
                        storeTweets = StoreTweets(self.outFilePath, self.outFileName, fileIndex + 1, tweetCount)
                        storeTweets.createFile()

                    storeTweets = StoreTweets(self.outFilePath, self.outFileName, fileIndex + 1, tweetCount)
                    storeTweets.writeInFile(row)
                    tweetCount = tweetCount + 1
                    print(row)
                except BaseException as e:
                    print(str(e))


if __name__ == '__main__':
    searchOutFilePath = "D:/Data warehousing 5408/assignment_3/Search/"
    searchOutFileName = "Search"

    searchData = SearchData(searchOutFilePath, searchOutFileName)