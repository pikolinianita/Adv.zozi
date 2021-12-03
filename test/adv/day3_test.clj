(ns adv.day3-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day3 :as sut]))
           
 (def test-data (seq ["00100" "11110" "10110" "10111" "10101" "01111" "00111" "11100" "10000" "11001" "00010" "01010"]))

(deftest first-part
  (testing "inp"
    (is (= 198 (sut/day-3-p-1 test-data)))))

(deftest sec-part
  (testing "all"
    (is (= 230 (sut/day-3-p-2 test-data)))))
 