(ns adv.day17-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day17 :as sut]))

(deftest first-part
  (testing "part1"
    (is (= 15931 (sut/day-17-p-1 " 0 0 -179 0")))))

(deftest partials
  (testing "map x"
    (is (= 3 (count (sut/possible-x-times 8 20 30 20))))
    (is (= 1 (count (sut/possible-x-times 20 20 30 20)))))  
  (testing "map y"
    (is (= 4  (count (sut/possible-y-times -2 -10 -5 20))))))

(deftest sec-part
  (testing "part2"
    (is (= 111  (sut/day-17-p-2 "target area: x=20..30, y=-10..-5")))))


