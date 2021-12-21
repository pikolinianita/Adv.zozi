(ns adv.day17-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day17 :as sut]
            [clojure.string :as str]))

(deftest first-part
(testing "part1"
(is (= 15931 (sut/day-17-p-1 " 0 0 0 -179")))
))