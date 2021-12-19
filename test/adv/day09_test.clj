(ns adv.day09-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day9 :as sut]
            [clojure.string :as str]))

(def test-input
"2199943210
3987894921
9856789892
8767896789
9899965678")
         
(deftest d2-approach 
  (testing "utils"
    (is (=  #{[0 1] [2 2] [4 6] [0 9]} (set (sut/low-points (sut/make-vec-of-vec (str/split-lines test-input))))))
    (is (= 3 (sut/basin-size (sut/make-vec-of-vec (str/split-lines test-input)) [0 1])))))    

(deftest parentes
  (testing "full parts"
    (is (= 15 (sut/day-9-p-1 (str/split-lines test-input))))
    (is (= 1134 (sut/day-9-p-2 (str/split-lines test-input))))))


;(deftest part1
 ;(testing "part1"
  ; (is (= "eh" (sut/get-answer "inp/09.txt")))))

