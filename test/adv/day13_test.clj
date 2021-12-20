(ns adv.day13-test
   (:require [clojure.test :refer [deftest is testing]]
             [adv.day13 :as sut]
             [clojure.string :as str]))

(def test-numbers [6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0])

(def test-set (set (partition 2 test-numbers)))

(deftest parts
  (testing "first part"
    (is (= 17 (count (sut/fold test-set 7 :y))))
    (is (= 16 (count (sut/fold (sut/fold test-set 7 :y) 5 :x))))))


