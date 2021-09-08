from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
import tweepy
import json
import api_keys as Keys
from constants import filterKeyWords
from store_tweets import StoreTweets

global tweetCount
global fileIndex
tweetCount = 1
fileIndex = 0

# Python API Tutorial: Working with Streaming Twitter Data", Dataquest, 2021. [Online].
# Available: https://www.dataquest.io/blog/streaming-data-python/.
# [Accessed: 10- Mar- 2021].
class StreamingData:
    def __init__(self, filePath, fileName):
        self.outFilePath = filePath
        self.outFileName = fileName
        api = self.createConnection()
        stream = self.getTweets(api)
        self.filterTweets(stream)

    def createConnection(self):
        auth = OAuthHandler(Keys.apiKey, Keys.apiSecretKey)
        auth.set_access_token(Keys.accessToken, Keys.accessTokenSecret)
        api = tweepy.API(auth)
        return api


    def getTweets(self, api: tweepy.api):
        outputListener = StreamListener(self.outFilePath, self.outFileName)
        stream = tweepy.Stream(api.auth, outputListener)
        return stream

    def filterTweets(self, stream):
        stream.filter(track = filterKeyWords)

class StreamListener(tweepy.StreamListener):

    def __init__(self, filePath, fileName):
        self.outFilePath = filePath
        self.outFileName = fileName

    def on_status(self, status):
        print(status.text)

    def on_error(self, status_code):
        if status_code == 420:
            return False

    def on_data(self, tweet):
        global  tweetCount
        global  fileIndex
        jsonData = json.loads(tweet)
        try:
            row = [jsonData['created_at'], jsonData['user']['name'], jsonData['user']['location'], jsonData['text']]
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
        finally:
            return True

if __name__ == '__main__':
    streamOutFilePath = "D:/Data warehousing 5408/assignment_3/Stream/"
    streamOutFileName = "Stream"

    streamingData = StreamingData(streamOutFilePath, streamOutFileName)