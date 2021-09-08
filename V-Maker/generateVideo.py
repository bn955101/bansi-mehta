import boto3
import cv2
from PIL import Image
from moviepy.editor import *
import matplotlib.image as mpimg
from io import BytesIO

mean_height = 0
mean_width = 0

# Checking the current directory path
print(os.getcwd())

# Folder which contains all the images
# from which video is to be generated
os.chdir("C:\\Users\\Janvi.DESKTOP-P36UV26\\Desktop\\Python")
path = "C:\\Users\\Janvi.DESKTOP-P36UV26\\Desktop\\Python"

num_of_images = len(os.listdir('.'))
# print(num_of_images)
# Finding the mean height and width of all images.
# This is required because the video frame needs
# to be set with same width and height. Otherwise
# images not equal to that width height will not get
# embedded into the video
mean_width = int(mean_width / num_of_images)
mean_height = int(mean_height / num_of_images)

def writeContent(final):
    # push the content in the csv file
    s3 = boto3.resource('s3')
    bucketName = "output"
    s3.Object(bucketName, bucketName).put(Body=final)
    print('Successfully generation of trainVector.csv on bucket named - traindatab00863421')


    # Video Generating function
def generate_video(Images,frame):
    image_folder = '.' # make sure to use your folder
    video_name = 'output.mp4v'
    os.chdir("C:\\Users\\Janvi.DESKTOP-P36UV26\\Desktop\\Python")
    images = Images

    # setting the frame width, height width
    # the width, height of first image
    height, width, layers = frame.shape

    video = cv2.VideoWriter(video_name, 0, 1, (width, height))

    # Appending the images to the video one by one
    for image in images:
        video.write(cv2.imread(image))

        # Deallocating memories taken for window creation
    cv2.destroyAllWindows()
    video.release()  # releasing the video generated


if __name__ == "__main__":
    s3_bucket_name = "firstbucketb00858613"
    s3 = boto3.resource('s3')
    obj = s3.Bucket(s3_bucket_name)
    print("Image")
    # Iterates through all the objects, doing the pagination for you. Each obj
    # is an ObjectSummary, so it doesn't contain the body. You'll need to call
    # get to get the whole body.
    filesContent = ""
    Images = ""
    video_name = 'output.mp4v'
    video = cv2.VideoWriter(video_name, 0, 1, (200, 200))
    for obj in obj.objects.all():
        image = mpimg.imread(BytesIO(obj.get()['Body'].read()), 'jpg')
        video.write(image)


    cv2.destroyAllWindows()
    video.release()  # releasing the video generated
    #writeContent(video)


# Calling the generate_video function

# video_name = 'output.mp4v'
# input_audio = 'C:\\Users\\Janvi.DESKTOP-P36UV26\\Desktop\\file_example_MP3_700KB.mp3'
#
# clip = VideoFileClip(video_name)
# audio = AudioFileClip(input_audio)
# final = clip.set_audio(audio)
# final = final.write_videofile("final.mp4", fps=30, threads=1, codec="libx264")
# ffmpeg.concat(input_video, input_audio, v=1, a=1).output('output.mp4').run(overwrite_output=True)
#subprocess.run(f"ffmpeg -i {input_video} -i {input_audio} -c copy {input_video}")
