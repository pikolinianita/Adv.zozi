(ns adv.day1-test
  (:require [clojure.test :refer :all]
            [adv.day1 :refer :all]))

(deftest part1
  (testing " part 1"
    (is (= 7 (day-1-p-1 [199 200 208 210 200 207 240 269 260 263])))) ) 

(deftest part2
  (testing " part 2"
    (is (= 5 (day-1-p-2 [199 200 208 210 200 207 240 269 260 263])))) ) 