(ns adv.day05-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day5 :as sut]))

(def test-data [[0 9 5 9]
                [8 0 0 8]
                [9 4 3 4]
                [2 2 2 1]
                [7 0 7 4]
                [6 4 2 0]
                [0 9 2 9]
                [3 4 1 4]
                [0 0 8 8]
                [5 5 8 2]])

(deftest utils
  (testing "line?"
    (is (= true (sut/line? [8 0 8 8]))))
  (testing "sorted Range p2"
    (is (= [1 2 3] (sut/sorted-range-p2 1 3)))
    (is (= [3 2 1] (sut/sorted-range-p2 3 1)))
    (is (= [3] (sut/sorted-range-p2 3 3))))
  (testing "vectorize"
    (is (= [0 9 5 9] (sut/vectorize "0,9 -> 5,9"))))
  (testing "make vector dia"
    (is (= [[0 10][1 11][2 12]] (sut/make-vectr-dia [0 10 2 12 ])))))

(deftest  part1
  (testing "first part"
    (is (= 5 (sut/day-5-p-1 test-data)))))

(deftest  part2
  (testing "sec part"
    (is (= 12 (sut/day-5-p-2 test-data)))))
