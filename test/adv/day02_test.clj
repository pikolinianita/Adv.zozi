(ns adv.day02-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day2 :as sut]
            [clojure.string :as str]))

(def test-input (map sut/tokenify (str/split-lines "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2")))

(deftest utils
  (testing "tokenify f-ction"
   (is (= [:forward 5] (sut/tokenify "forward 5")))
    (is (= [:down 5] (sut/tokenify "down 5"))))
  (testing "adjust"
   (is (= [5 0] (sut/adjust-position [0 0] [:forward 5])))))

(deftest part-1
  (testing "part-1"
    (is(= 15 (sut/day-2-p-1 [[:forward 5] [:down 4] [:up 1]])))
    (is(= 150 (sut/day-2-p-1 test-input)))))

(deftest part-2
  (testing "part-2"
    (is (= 900 (sut/day-2-p-2 test-input)))))