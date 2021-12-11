(ns adv.day10
  (:require [clojure.string :as str]
            [clojure.set :refer [map-invert]]))

(def points {\) 3 \] 57  \} 1197 \> 25137 nil 0})

(def points-p2 {\) 1 \] 2 \} 3 \> 4 nil 0})

(def open {\( \) \[ \] \{ \} \< \>})

(defn error-score [line]
  (let [charz (seq line)]
(loop [sq charz
       q []]
  ;(println q " : " (peek q) " : " (first sq) " : " (open (first sq)) " : " (close (first sq)))
  (cond
    (open (first sq)) (recur (rest sq) (conj q (open (first sq))))
    (= (first sq) (peek q)) (recur (rest sq) (pop q))
    :else (points (first sq))))))

(defn score-p2 [count char]
(+ (* 5 count) (points-p2 char))  
)

(defn missing-chars [line]
  (let [charz (seq line)]
    (loop [sq charz
           q []]
  ;(println q " : " (peek q) " : " (first sq) " : " (open (first sq)) " : " (close (first sq)))
      (cond
        (open (first sq)) (recur (rest sq) (conj q (open (first sq))))
        (= (first sq) (peek q)) (recur (rest sq) (pop q))
        (not-empty sq) 0
        :else (reduce score-p2 0 (reverse q))
        ))))



(defn day-10-p-1 [s] 
  (reduce + (map error-score s)))

(defn day-10-p-2 [s] 
  (let [sorted-results (vec (sort (filter pos? (map missing-chars s))))]
    (sorted-results (int (/ (count sorted-results) 2)))))

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-10-p-1 s)))
    (time (println "Answer for second part is: " (day-10-p-2 s)))))