(ns adv.day07-test
  (:require [clojure.test :refer [deftest is testing]]
                            [adv.day7 :as sut]))

(def test-data [16,1,2,0,4,2,7,1,2,14])

(deftest utils
  (testing "result p2"
    (is (= 168 (sut/result-p2 test-data 5)))
    (is (= 206 (sut/result-p2 test-data 2)))   
    )
  (testing "sum-n"
    (is (= 55 (sut/sum_n 10)))
    (is (= 1 (sut/sum_n 1)))
    (is (= 6 (sut/sum_n 3)))   
    ))

(deftest parts 
  (testing "part 1"
    (is (= 37 (sut/day-7-p-1 test-data)))
    (is (= 168 (sut/day-7-p-2 test-data)))
    )) 
