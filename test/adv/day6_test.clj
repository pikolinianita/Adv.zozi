(ns adv.day6-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day6 :as sut]))

(def start-seq [3 4 3 1 2])

(deftest helpers
  (testing "simulate"
    (is (= 26 (sut/simulate start-seq 18)))
    (is (= 10 (sut/simulate start-seq 7)))
    (is (= 20 (sut/simulate start-seq 14)))
    (is (= 5 (sut/simulate start-seq 1)))
    (is (= 10 (sut/simulate start-seq 5)))))

(deftest p1
  (testing "parts"
    (is (= 5934 (sut/day-6-p-1 start-seq)))
    (is (= 26984457539 (sut/day-6-p-2 start-seq)))))

