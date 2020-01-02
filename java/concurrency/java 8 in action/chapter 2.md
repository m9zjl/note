### chapter 2
-----

1. when passing a paprameter, thinking about passing the behiver instead of the value itself;

    ```java
    public static List<String> filter(List<String> list,lambda::(interface a)->{a::function}){
        List<String> tmpList = new ArrayList<>();
        for(String s: list){
            if(a.test(e)){
                tmpList.add(a);
            }
        }
        return tmpList;
    }
    ```

    >while writing function, it`s a good idea to abstracting over List type,eg:
    ```java
    public static List<String> filter(List<String> lists, lambda){}
    public static <T> List<T> filter(List<T> lists,lambda){} 
    ``` 