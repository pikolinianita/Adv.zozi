(ns adv.day4
  (:require [clojure.string :as str]))

(defn make-board [lines]
  (let [numerify (fn [str] (Integer/parseInt str))
        line-to-vector (fn [line] (vec (map numerify (re-seq #"\d+" line))))] 
    (vec (map line-to-vector lines))))

(defn hit [board n]
  
  )


(defn day-4-p-1 [s]
  "not implemented")

(defn day-4-p-2 [s]
  "not implemented")

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-4-p-1 s)))
    (time (println "Answer for second part is: " (day-4-p-2 s)))))