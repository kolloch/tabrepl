# tabrepl

Small demo that it is pretty easy to make repl tab completion smarter.

## Usage

Build a jar with all necessary classes:

    $ lein uberjar

Run the standalone jar:

    $ java -jar tabrepl-*-standalone.jar

## Sample Session

    $ java -jar tabrepl-*-standalone.jar
    Starting...
    clojure.core => (print

    print                 print-ctor            print-doc             print-dup             print-method          print-namespace-doc   print-simple          print-special-doc print-str             printf                println               println-str
     clojure.core => (println

     println       println-str
     clojure.core => (println "test")
     test
     nil
     clojure.core => 


## License

Copyright (C) 2010 Peter Kolloch

Distributed under the Eclipse Public License, the same as Clojure.
