(ns adv.day21-test
   (:require [clojure.test :refer [deftest is testing]]
             [adv.day21 :as sut]
             [clojure.string :as str]))

(deftest dice-test
  (testing "dice"
    (is (= (range 1 101 ) (take 100 sut/dice-seq)))
    (is (= [100 1] (take 2 (drop 99 sut/dice-seq))))    
    ))

(deftest rolling-test
(testing "sigle players rolls"
  (is (= [10 10] (sut/roll [4 0] sut/dice-seq)))
  (is (= [3 3] (sut/roll [8 0] (drop 3 sut/dice-seq))))
  (is (= [4 14] (sut/roll [10 10] (drop 6 sut/dice-seq))))
  (is (= [6 9] (sut/roll [3 3] (drop 9 sut/dice-seq))))))

(deftest game-test
(testing "full game"
  (is (= 336 (sut/game [4 0] [8 0] 0 sut/dice-seq 21)))
  (is (= 135 (sut/game [4 0] [8 0] 0 sut/dice-seq 20)))
  (is (= 384 (sut/game [4 -10] [8 1] 0 sut/dice-seq 21)))
  (is (= 739785  (sut/game [4 0] [8 0] 0 sut/dice-seq 1000)))
      
      ))

(deftest alles-klar
  (testing "full"
    (is (= nil (sut/get-answer "inp/21.txt")))))