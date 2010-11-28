(ns tabrepl.main
  (:gen-class)
  (:require
   [clojure.main]
   [tabrepl.core :as core])
  (:import
   [jline ConsoleReader Completor]))

(defn -main [& args]
  (let [cr
        (doto (ConsoleReader.)
          (.addCompletor core/completor))]
    (clojure.main/repl
     :read
     (partial core/read-for-repl cr))))
  