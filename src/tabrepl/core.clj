(ns tabrepl.core
  (:require
   [clojure.pprint :as pprint]
   [clojure.contrib.ns-utils :as nsu])
  (:import
   [jline ConsoleReader Completor]))

(defn all-symbols2 []
  (apply sorted-set
         (map str
              (concat
               (-> *ns* ns-imports keys)
               (-> *ns* ns-aliases keys)
               (-> *ns* nsu/ns-vars)
               (-> *ns* ns-refers keys)))))

(defn derive-completions [word all-words]
  (take-while #(.startsWith % word)
              (subseq all-words >= word)))

(defn calc-completions [word]
  (derive-completions word (all-symbols2)))

(defn last-word [buffer]
  (->> buffer
      (re-seq #"[\*/a-zA-Z0-9_-]+")
      last))

(defn create-completor []
     (reify Completor
            (complete
             [this buffer cursor candidates]
             (let [word (last-word buffer)
                   completions (calc-completions word)]
               (.addAll candidates completions)
               (- cursor (.length word))))))

(defn create-reader [& opts]
  (doto (ConsoleReader.)
    (.addCompletor (create-completor))))

(defn read-for-repl [cr request-prompt request-exit]
  (let [line (.readLine cr)]
    (if line (read-string line) request-exit)))

(defn hook-into-main-repl []
  (let [cr (create-reader)]
    (clojure.main/repl
     :read
     (partial read-for-repl cr))))

(defn readline-with-prompt [cr]
  (.readLine cr (str *ns* " => ")))

(defn pretty-eval [line]
  (try 
    (pprint/pprint (eval (read-string line)))

    (catch Exception e (.printStackTrace e))))
  
(defn hook-into-console-reader []
  (let [cr (create-reader)]
    (println "Starting...")
    (loop [line (readline-with-prompt cr)]

      (if (nil? line)
        nil
        (do
          (pretty-eval line)
          (recur (readline-with-prompt cr)))))))
