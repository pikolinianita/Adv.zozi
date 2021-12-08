(ns adv.day8 
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn add-235 
  "235 have 5 lines. 7 is subset of 3 (but not 2 or 5), 
   then 4 has one line wrong with 5, and two lines wrong with 2"
  [m d]
    (cond
      (= 0 (count d)) m
      (set/subset? (m 7) (first d)) (recur (assoc m 3 (first d)) (rest d))
      (= 1 (count (set/difference (m 4) (first d)))) (recur (assoc m 5 (first d)) (rest d)) 
      (= 2 (count (set/difference (m 4) (first d)))) (recur (assoc m 2 (first d)) (rest d))
      :else (throw "Mistake in 235")))

(defn add-690 
  "690 have 6 lines, 3 is subset of 9 only. If not, then 1 is subset of 0, 6 is not"
  [m d]
    (cond
      (= 0 (count d)) m
      (set/subset? (m 3) (first d)) (recur (assoc m 9 (first d)) (rest d))
      (= 1 (count (set/difference (m 1) (first d) ))) (recur (assoc m 6 (first d)) (rest d)) 
      (= 0 (count (set/difference (m 1) (first d) ))) (recur (assoc m 0 (first d)) (rest d))
      :else (throw "Mistake in 690")))

(defn solve-line [line]
  (let [[data [_ & question]] (split-with  (complement #{"|"}) (str/split line #" "))
        data-part (group-by count (map set data))
        observations (-> {}
                   (assoc 1 (get-in data-part [2 0]))
                   (assoc 7 (get-in data-part [3 0]))
                   (assoc 4 (get-in data-part [4 0]))
                   (assoc 8 (get-in data-part [7 0]))
                   (add-235 (data-part 5))
                   (add-690 (data-part 6))
                   (set/map-invert))]
     (Integer/parseInt (apply str (map observations (map set question)))))) 

(defn day-8-p-1 [s]
  (let [drop-start (fn [coll] (rest (drop-while #(not (#{"|"} %)) coll)))
        code-map (group-by count (flatten (map #(drop-start (str/split % #" ")) s)))]
    (count (flatten (map code-map [2 4 3 7])))))

(defn day-8-p-2 [s] 
  (reduce + (map solve-line s)))

(defn get-answer [path-to-file]
  (let [s  (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-8-p-1 s)))
    (time (println "Answer for second part is: " (day-8-p-2 s)))))