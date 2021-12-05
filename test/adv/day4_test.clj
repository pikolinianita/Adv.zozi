(ns adv.day4-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day4 :as sut]
            [clojure.string :as str]))

(def test-numbers [7 4 9 5 11 17 23 2 0 14 21 24 10 16 13 6 15 25 12 22 18 20 8 19 3 26 1])

(def board1 ["22 13 17 11  0"
 "8  2 23  4 24"
"21  9 14 16  7"
"6 10  3 18  5"
 "1 12 20 15 19"])
			 
(def board2 ["3 15  0  2 22"
 "9 18 13 17  5"
"19  8  7 25 23"
"20 11 10 24  4"
"14 21 16 12  6"])

(def board3 ["14 21 17 24  4"
"10 16 15  9 19"
"18  8 23 26 20"
"22 11 13  6  5"
" 2  0 12  3  7"])

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
		(is (= [101 2 3 4] (sut/hit [1 2 3 4] 1)))
		
))
board1 board2
(deftest part-1
	(testing "parse input"
		;(is (= [1 2 103 4] (reduce + (map count (sut/day-4-p-1  (str/split-lines (slurp "inp/04.txt")))))))
		(is (= 4512 (sut/try-solve-p1 test-numbers (map sut/make-board [ board2 board3 board1 ] ))))
))

;(println (sut/day-4-p-1 (str/split-lines (slurp "inp/04.txt"))))

(println (update-in test-numbers [(.indexOf test-numbers 17 )] #(+ 100 %)))
;(println (sut/solve (sut/make-board board1)))
