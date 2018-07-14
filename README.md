# findsum
This project aims to check if an array has two or more integer numbers that sum is equals an expected number.  
If the arguments are [1,2,5,7,10] and 16 it should return true because 1 + 5 + 10 = 16.  
However, for [1,3,7] and 9 it should return false because there is no a combination that sum is 9.  
The algorithm is implemented on the classes that implement the interface com.challenge.findsum.FindSum.  
You can create your own implementation and replace the bean findSum by it, it is defined on class com.challenge.findsum.FindSumApplication.
For example:  
```java
@Bean
FindSum findSum() {
    return new FindSumYoursImpl();
}
```
