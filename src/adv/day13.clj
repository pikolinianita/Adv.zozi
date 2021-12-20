(ns adv.day13
  (:require [clojure.string :as str]))

(defn fold [sheet div dir]
  {:pre [(set? sheet) (#{:x :y} dir)]}
  (let [swap-x (fn [[x y]] (if (> x div) [(- (* 2 div) x) y] [x y]))
        swap-y (fn [[x y]] (if (> y div) [x (- (* 2 div) y)] [x y]))
        f (if (= :x dir) swap-x swap-y)]
    (->> (seq sheet)
         (map f)
         (set))))

(defn parse-coord [coord]
  (->> coord
       (re-seq #"\d+")
       (map #(Integer/parseInt %))))

(defn parse-comm [comm]
  (let [dir (re-find #"x|y+" comm)
        row (re-find #"\d+" comm)]
    [(Integer/parseInt row) (keyword dir)]))

(defn day-13-p-1 [s]
  (let [coords (set (map parse-coord (take-while not-empty s)))
        command (first (map parse-comm (rest (drop-while not-empty s))))]
    (count (apply fold coords command))))

(defn day-13-p-2 [s]
  (let [coords (set (map parse-coord (take-while not-empty s)))
        commands  (map parse-comm (rest (drop-while not-empty s)))
        result (reduce #(apply fold %1 %2) coords commands)
        turn-on (fn [board2d [x y]] (assoc-in board2d [y x] "#"))]
    (println)
    (map println (map #(apply str %) (reduce turn-on (vec (repeat 10 (vec (repeat 40 ".")))) (seq result))))))

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-13-p-1 s)))
    (time (println "Answer for second part is: " (day-13-p-2 s)))))