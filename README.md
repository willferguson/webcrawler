# Simple Web Crawler

### Building
 
* To build and run tests: 
    > ./gradlew build

     (use gradle.bat for Windows)
* To build without running tests:
    > ./gradlew jar
    
* This will create a jar with dependencies in ```build/libs```

### Running

* To run, execute the jar with full url of the seed site (including protocol)

    > java - jar webcrawler-1.0.jar http://www.example.com
    
### Caveats 

* Requires Java 8
* Will just ignore and log invalid URLs
* Doesn't handle url fragments properly, so will crawl both www.example/test & www.example/test#fragment
* Does not honour robots.txt disallows.
  
