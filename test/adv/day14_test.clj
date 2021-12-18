(ns adv.day14-test
  (:require [clojure.test :refer [deftest is testing]]
            [adv.day14 :as sut]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(set! *warn-on-reflection* true)

(def source-data
  "CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C")

(deftest full-map-approach
  (testing "simple examples"
    (is (= (sut/map-input "NCNBCHB") (sut/map-turn (sut/map-input "NNCB") (sut/map-rules (str/split-lines source-data)))))
    (is (= (sut/map-input "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB") (sut/map-turn (sut/map-input "NBBBCNCCNBBNBNBBCHBHHBCHB") (sut/map-rules (str/split-lines source-data))))))
   (testing "part 1"
     (is (= 1588 (sut/run-stuff "NNCB" (str/split-lines source-data) 10)))))

(deftest part-tests
  (testing "str-map"
    (is (= {"NN" 1 "NC" 1 "CB" 1 :not-doubled [\N \B]} (sut/map-input "NNCB")))
    (is (= {"NN" 3 "NC" 1 "CB" 1 :not-doubled [\N \B]} (sut/map-input "NNNNCB"))))
  (testing "rules-map"
    (let [source-lines (str/split-lines source-data)]
      (is (= 16 (count (sut/map-rules source-lines))))      
      (is (= ["CB" "BH"] ((sut/map-rules source-lines) "CH")))))
  (testing "quantity difference"
    (is (= 1 (sut/calculate-diff {"NN" 1 "NC" 1 "CB" 1 "BC" 1 :not-doubled [\N \C]})))
    (is (= 5 (sut/calculate-diff {"NN" 5 "NC" 1 "CB" 1 "BC" 1 :not-doubled [\N \C]})))
    (is (= 2 (sut/calculate-diff {"NN" 1 "NC" 1 "CB" 3 "BC" 3 :not-doubled [\N \C]})))))


   