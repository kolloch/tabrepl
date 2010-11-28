(defproject tabrepl "0.1.0-SNAPSHOT"
  :description "Basic dynamic autocompletion for clojure repl"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [jline "0.9.94"]]
  
  :dev-dependencies [[autodoc "0.7.1"]
                     [lein-clojars "0.5.0"]
                     [swank-clojure "1.3.0-SNAPSHOT"]
                     [lein-eclipse "1.0.0"]]
  
  :main tabrepl.main)

