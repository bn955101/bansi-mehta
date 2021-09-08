import re

# s/preprocessor", GitHub, 2021. [Online].
# Available: https://github.com/s/preprocessor/blob/master/preprocessor/defines.py.
# [Accessed: 10- Mar- 2021]. (Class Patterns)
class Patterns:
    urlPattern = re.compile(
        r'(?i)\b((?:https?://|www\d{0,3}[.]|[a-z0-9.\-]+[.][a-z]{2,4}/)(?:[^\s()<>]+|\(([^\s()<>]+|(\([^\s()<>]+\)))*\))+(?:\(([^\s()<>]+|(\([^\s()<>]+\)))*\)|[^\s`!()\[\]{};:\'".,<>?\xab\xbb\u201c\u201d\u2018\u2019]))')
    hashtagPattern = re.compile(r'#\w*')
    mentionPattern = re.compile(r'@\w*')
    reservedWordsPattern = re.compile(r'^(RT|FAV)')

    try:
        # UCS-4
        emojiPattern = re.compile(u'([\U00002600-\U000027BF])|([\U0001f300-\U0001f64F])|([\U0001f680-\U0001f6FF])')
    except re.error:
        # UCS-2
        emojiPattern = re.compile(
            u'([\u2600-\u27BF])|([\uD83C][\uDF00-\uDFFF])|([\uD83D][\uDC00-\uDE4F])|([\uD83D][\uDE80-\uDEFF])')

    smileyPattern = re.compile(r"(?:X|:|;|=)(?:-)?(?:\)|\(|O|D|P|S){1,}", re.IGNORECASE)
    numbersPattern = re.compile(r"(^|\s)(\-?\d+(?:\.\d)*|\d+)")

    def cleanUsingPatterns(self, originalText):
        removedHashTag = re.sub(self.hashtagPattern, '', originalText)
        removedMention = re.sub(self.mentionPattern, '', removedHashTag)
        removedUrl = re.sub(self.urlPattern, '', removedMention)
        removedEmoji = re.sub(self.emojiPattern, '', removedUrl)
        finalText = re.sub(self.smileyPattern, '', removedEmoji)
        return finalText
