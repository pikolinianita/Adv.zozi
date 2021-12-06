(ns adv.day4
  (:require [clojure.string :as str]))

; Playing board (5x5 for challenge cases) is represented 
; as long vector of integers (length 25 for challenge)
; if number is hit (marked) number is increased by 100
; - there are only numbers 0-99 in challenge data

(defn print-square 
  "print board as crude 5x5 square. adds empty line below"
  [board]
  (doall
   (map println (partition 5 board)))
  (println))

(defn make-board 
  "takes n strings, with n numbers each, and make one vector - one game board"
  [lines]
  (let [numerify (fn [str] (Integer/parseInt str))
        line-to-vector (fn [line] (vec (map numerify (re-seq #"\d+" line))))]
    (vec (apply concat [] (map line-to-vector lines)))))

(defn hit
 "if board has number n - n on board is increased by 100 - means 'was hit'"
  [board n]
  (let [hit-pos (.indexOf board n)]
    (if (<= 0 hit-pos)
      (update-in board [hit-pos] #(+ 100 %))
      board)))

(defn solved? 
  "returns true if all numbers in any row or any column were hit = are 100 or more"
  [board]
  (let [size (int (Math/sqrt (count board)))
        test-row (fn [pos] (->> board (drop (* pos size)) (take size)))
        test-col (fn [pos] (->> board (drop pos) (take-nth size)))
        hit? (fn [line] (every? #(< 99 %) line))]
    (true? (or
            (some true? (map hit? (map test-row (range size))))
            (some true? (map hit? (map test-col (range size))))))))

(defn final-score 
  "sum un-hit board numbers times n"
  [board n]
  (* n (apply + (filter #(> 100 %) board))))

(defn try-solve-p1 [shots boards]
  (let [hit-boards (vec (map #(hit % (first shots)) boards))
        solved-vec (map solved? hit-boards)]
    (if (every? false? solved-vec)
      (recur (rest shots) hit-boards)
      (final-score (get hit-boards (.indexOf solved-vec true)) (first shots)))))

(defn try-solve-p2 [shots boards]
  (let [hit-boards (vec (map #(hit % (first shots)) boards))
        solved-vec (map solved? hit-boards)]
    (if (every? true? solved-vec)     
      (final-score (get hit-boards (.indexOf (map solved? boards) false)) (first shots)) 
      (recur (rest shots) hit-boards))))

(defn advanced-parser 
  "convert lines to vector of bingo numbers and vector of boards, returns vector [shots boards]"
  [[first-line & other-lines]]
  [(map #(Integer/parseInt %) (str/split first-line #","))
   (map make-board (filter #(< 1 (count %)) (partition-by #(= "" %) other-lines)))])

(defn day-4-p-1 [s]
  (let[[shots board-str] (advanced-parser s)]
    (try-solve-p1 shots board-str)))

(defn day-4-p-2 [s]
   (let[[shots board-str] (advanced-parser s)]
    (try-solve-p2 shots board-str)))

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-4-p-1 s)))
    (time (println "Answer for second part is: " (day-4-p-2 s)))))