## opencv notes 
----
### how to train a object detection with opencv 
1. download picture on google, an easier way is to download by googleimagedownloader
    ```shell
    googleimagesdownload  -k "房间内部" -l 1000 -px 127.0.0.1:1087 -es 640,480 -cd /Users/ben/chromedriver
    ```
    here is an example how to use gooleimagesdownload
    |key|value|explaination|
    |:--:|:--:|:--:|
    |-k| 房间内部 | search key works|
    |-l| 1000| image count to download , -cd is need if count > 100|
    | -es| 640,480 | picture size|
    | -cd| /Users/ben/chromedriver| url to chromedriver ,[download url](http://chromedriver.chromium.org/downloads)|
2. annotate images by opencv_annotation
   >opencv_annotation --annotations=/Users/ben/Downloads/a.txt -images=/Users/ben/Downloads/socks/
   
   the first parameter is your annotaion file to be places the second parameter is your images loacation 
3. when annotations file is ready use opencv_createsamples to creat sample file
   ```shell
   opencv_createsamples -info a\ copy.txt -bg test.txt 255 -w 640 -h 480 -num 500 -vec object.vec
   ```
