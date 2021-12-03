(ns adv.day3
  (:require [clojure.string :as str]))

(defn day-3-p-1 [inp]
  (let [half (/ (count inp) 2)
        summed (map #(get (frequencies %) \1) (apply mapv str inp))
        epsilon (Integer/parseInt (apply str (map #(if (> % half) 0 1) summed)) 2)
        gamma (Integer/parseInt (apply str (map #(if (< % half) 0 1) summed)) 2)]
    (* epsilon gamma)))

(defn ones-on-pos
  "gets list of numbers on position pos in coll of strings"
  [pos coll]
  (map #(get % pos) coll))

(defn remove-minority
  "remove strings from collections, which have (more or less, aka f) than half signs of given kind (1 or 0)"
  [f pos coll]
  (let [half (/ (count coll) 2)
        ones  (count (filter #(= \1 %) (ones-on-pos pos coll)))]
    (if (f half ones)
      (filter #(= \0 (get % pos)) coll)
      (filter #(= \1 (get % pos)) coll))))

(defn rating [f inp]
  (loop [pos 0
         coll inp]
    (if (= 1 (count coll))
      (first coll)
      (recur (inc pos) (remove-minority f pos coll)))))

(defn day-3-p-2 [inp]
  (let [oxygen (Integer/parseInt (rating > inp) 2)
        co2 (Integer/parseInt (rating <= inp) 2)]
    (* oxygen co2)))

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-3-p-1 s)))
    (time (println "Answer for second part is: " (day-3-p-2 s)))))