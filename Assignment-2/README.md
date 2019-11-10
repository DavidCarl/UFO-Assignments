# Letter frequencies
Simple program used to illustrate performance problems. You should be able to optimize this program to run about twice as fast.

### Profiler
We used the profiler to try and analyze where there could be some problems in the code.

This is the profiler before we made any changes
![https://i.imgur.com/P6AZ3yv.png](https://i.imgur.com/P6AZ3yv.png)

As we can see in the image one of the big perfomance hugs are reading from the file. We then tried to change the file reader to a buffered reader, which gave us this profiler.

![https://i.imgur.com/9IhSv99.png](https://i.imgur.com/9IhSv99.png)

After we changed our file reader we got big performance boost (around 100%), however we were still in the market for better performance gains. We noticed that our hashmap put and putvals still filled a ton so we went to look for improvements here. However since HashMap in itself already is pretty optimised we couldnt figure out a better map or storage method to directly replace it. We choose to then make a custom class that held the value. This made it so we didnt have to write to our hashmap as often as we had before.

![https://i.imgur.com/c9sZyBQ.png](https://i.imgur.com/c9sZyBQ.png)


### Benchmarking
I am benchmarking the application by running the algorithm 100 times and then take the total time and divide it with 100.
This way around we should be sure to get a fair result with the CPU speeding up etc.

We do not believe this is 100% free of errors, however we believe in it being precise enough to be used to give a general idea since our benchmarks are not really close together. We also ran it on 3 computers and they give approximately the same improvement, and ran it multiple times on each computer to find the general level of the computer and applications running on it.

They are also ran around the same time and not with several min between.
#### Before any changes
Execution time on average in millis: 46
#### After bufferedReader
Execution time on average in millis: 21
#### After custom object
Execution time on average in millis: 10

### Conclusion
As you can see on our timings in our benchmark we made some big improvements overall and gained 