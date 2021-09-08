from search_data import SearchData
from streaming_data import StreamingData

if __name__ == '__main__':
    searchOutFilePath = "D:/Data warehousing 5408/assignment_3/Search/"
    searchOutFileName = "Search"
    streamOutFilePath = "D:/Data warehousing 5408/assignment_3/Stream/"
    streamOutFileName = "Stream"

    searchData = SearchData(searchOutFilePath, searchOutFileName)
    streamingData = StreamingData(streamOutFilePath, streamOutFileName)