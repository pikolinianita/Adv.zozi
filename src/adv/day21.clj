(ns adv.day21
  (:require [clojure.string :as str]))

(def dice-seq (iterate #(inc (mod % 100)) 1))

(defn roll [[pos points] dice-s]
  (let [result (apply + (take 3 dice-s))
        nw-pos (inc (mod (+ result (dec pos)) 10))]
    [nw-pos (+ points nw-pos)]))

(defn game [[_ _ :as p1] [_ points2 :as p2] n-rolls dice-s threshold]
  (let [[_ nw-points1 :as next-p1] (roll p1 dice-s)
        next-p2 (roll p2 (drop 3 dice-s))]   
    (cond
      (>= (second next-p1) threshold) (* 3 (inc n-rolls) points2)
      (>= (second next-p2) threshold) (* 3 (+ 2 n-rolls) nw-points1)
      :else (recur next-p1 next-p2 (+ 2 n-rolls) (drop 6 dice-s) threshold))))


(defn day-21-p-1 [s]
  (let [[_ p1 _ p2] (map #(Integer/parseInt %) (re-seq #"\d+" s))]
    (game [p1 0] [p2 0] 0 dice-seq 1000)))

(defn day-21-p-2 [s]
  (let [[_ p1 _ p2] (map #(Integer/parseInt %) (re-seq #"\d+" s))])
"not impl"
  )

(defn get-answer [path-to-file]
  (let [s (slurp path-to-file)]
    (time (println "Answer for first part is: " (day-21-p-1 s)))
    (time (println "Answer for second part is: " (day-21-p-2 s)))))