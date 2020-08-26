# Moore`s voting algorythm

## 1. find the num that appear more than n/2 times

choose the first element ant count it, 
if the value appears again count++ if not count--
when count reach 0, change the value to another value;

>explanation: just like voting, if someone vote to the candidate,
vote count + 1, if voting to the opponent vote count -1;

## 2. fint the num appear more than n/3 times

this problems is the same as the previous one except for there will be two candidates

>random algorythm

we count randomly choose one candidates and test if this is the right one, as we konw the probolity is 2/3, so if we choose 100 times, and the probolity of getting the right anwser is (2/3)^100,
which mean we get the result in O(100n) time complexity and we will probably get the right anwser.