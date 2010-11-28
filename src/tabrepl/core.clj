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
      (re-seq #"[/a-zA-Z0-9_-]+")
      last))

(def completor
     (reify Completor
            (complete
             [this buffer cursor candidates]
             (let [word (last-word buffer)
                   completions (calc-completions word)]
               (.addAll candidates completions)
               (- cursor (.length word))))))

(defn read-for-repl [cr request-prompt request-exit]
  (let [line (.readLine cr)]
    (if line (read-string line) request-exit)))
