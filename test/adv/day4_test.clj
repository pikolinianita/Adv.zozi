(ns adv.day4-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day4 :as sut]
            [clojure.string :as str]))

(def test-numbers [7 4 9 5 11 17 23 2 0 14 21 24 10 16 13 6 15 25 12 22 18 20 8 19 3 26 1])

(def board1 ["14 21 17 24  4"
             "10 16 15  9 19"
             "18  8 23 26 20"
             "22 11 13  6  5"
             "2  0 12  3  7"])

(def tiny-board ["1 2"
                 "3 4"])
				 
(def tiny-board2 ["101 2"
                 "3 4"])		

(def tiny-board3 ["101 102"
                 "3 4"])	

(def tiny-board4 ["101 2"
                 "103 4"])					 

(deftest input
  (testing "input processing"
  ;(is (= [[1 2] [3 4]] (sut/make-board tiny-board)))
  (is (= [1 2 3 4] (sut/make-board tiny-board)))
  ))
;(println (sut/make-board board1))

(deftest check-win
	(testing "should not win"
		(is (= false (sut/solved? (sut/make-board tiny-board))))
		(is (= false (sut/solved? (sut/make-board tiny-board2))))
	)
	(testing "should win"
		(is (= true (sut/solved? (sut/make-board tiny-board3))))
		(is (= true (sut/solved? (sut/make-board tiny-board4))))
	)	
)

(deftest check-hit
	(testing "hit proc"
		(is (= [1 2 103 4] (sut/hit [1 2 3 4] 3)))
))

(println (update-in test-numbers [(.indexOf test-numbers 17 )] #(+ 100 %)))
;(println (sut/solve (sut/make-board board1)))
