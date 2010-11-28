(ns tabrepl.test.core
  (:use [tabrepl.core] :reload)
  (:use [clojure.test]))

(deftest test-calc-completions
  (is (some
       (partial = "println")
       (calc-completions "print"))))
