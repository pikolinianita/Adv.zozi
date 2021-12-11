(ns adv.day10-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day10 :as sut]
            [clojure.string :as str]))

(def test-data
  "[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]")

(deftest utils
  (testing "counting mistakes"
    (is (= [0 0 1197 0 3 57  0 3 25137 0] (map sut/error-score (str/split-lines test-data))))
    (is (= [288957 5566 0 1480781 0 0  995444 0 0 294] (map sut/missing-chars (str/split-lines test-data))))))


(deftest parts
  (testing "parts"
    (is (= 26397 (sut/day-10-p-1 (str/split-lines test-data)))))
    (is (= 288957 (sut/day-10-p-2 (str/split-lines test-data)))))
