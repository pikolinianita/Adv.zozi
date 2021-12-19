(ns adv.day01-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day1 :as sut]))

(deftest part1
  (testing " part 1"
    (is (= 7 (sut/day-1-p-1 [199 200 208 210 200 207 240 269 260 263])))) ) 

(deftest part2
  (testing " part 2"
    (is (= 5 (sut/day-1-p-2 [199 200 208 210 200 207 240 269 260 263])))) ) 