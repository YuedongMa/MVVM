# MVVM

mvvm封装demo,基本架空viewmodel，可从视图层直接调用泛型指定的repository,但保留了viewmodel数据恢复的特性

采用rxjava+retrofit+viewmodel+livedata组合，其中自定义livedata,

通过tag值区分不同接口发送的数据，页面只需要注册一次监听，根据tag值取不同接口返回的数据

在Repository中接口返回的数据调用  
 
 sendData("tag", response);  来发送数据

 视图层监听
 重写onDataChage(ResponseModel response)方法进行数据接收，从response中的tag值区分不同接口


![Image text](https://github.com/YuedongMa/MVVM/blob/master/img/demo.jpg)
