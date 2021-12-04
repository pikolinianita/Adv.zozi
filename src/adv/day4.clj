(ns adv.day4
  (:require [clojure.string :as str]))

(def bingo-size 5)

(defn make-board [lines]
  (let [numerify (fn [str] (Integer/parseInt str))
        line-to-vector (fn [line] (vec (map numerify (re-seq #"\d+" line))))] 
     (vec (apply concat [] (map line-to-vector lines)))))

(defn hit [board n]
	(let [hit-pos (.indexOf board n)]
		(if (pos? hit-pos) 
		(update-in board [hit-pos] #(+ 100 %))
		board)
	))

(defn solved? [board]
(let [size (int (Math/sqrt (count board)))
	test-row (fn [pos] (->> board (drop (* pos size)) (take size)))
	test-col (fn [pos] (->> board (drop pos) (take-nth size)))
	hit? (fn [line] (every? #(< 100 %) line))]

 (true? (or
	(some true? (map hit? (map test-row (range size))))
	(some true? (map hit? (map test-col (range size))))))

))


(defn day-4-p-1 [s]
  "not implemented")

(defn day-4-p-2 [s]
  "not implemented")

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-4-p-1 s)))
    (time (println "Answer for second part is: " (day-4-p-2 s)))))
