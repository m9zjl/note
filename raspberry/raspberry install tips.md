## raspberry installation tips and erros 
[TOC]
--------
# 1. install tensorflow on python3 over raspberry
+ libf77blas.so.3 missing while installing numpy(python3)
    > for libf77blas.so.3, you have to install ATLAS, something like sudo apt-get install libatlas-base-dev
+ use the following commanders to install nonofficial higher version tensorflow 
    ```shell
    sudo apt-get install python3-pip python3-dev
    wget https://github.com/lhelontra/tensorflow-on-arm/releases/download/v1.8.0/tensorflow-1.8.0-cp35-none-linux_armv7l.whl
    sudo pip3 install /tensorflow-1.8.0-cp35-none-linux_armv7l.whl
    sudo apt-get install libatlas-base-dev
    ```

