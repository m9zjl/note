# Enum

## checking using enum

this is especially useful if you want to add an enum with some checks

```java
public enum CheckingEnum{
    ONE {
        @override
        public void check(){
            //TODO check method
        }
    }
    abstract void check();
}
```
