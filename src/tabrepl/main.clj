(ns tabrepl.main
  (:gen-class)
  (:require
   [tabrepl.core :as core]))


(defn -main [& args]
  (core/hook-into-console-reader))
  