(ns adv.day2
   (:require [clojure.string :as str]))

(defn adjust-position
  "use position and depth. command is [:com value] 
   :forward first +
    :down second +
    :up second -
   "
  [[old-x old-depth] [command value]]
  (cond
    (= :forward command) [(+ old-x value) old-depth]
    (= :up command) [old-x (- old-depth value)]
    (= :down command) [old-x (+ old-depth value)]))

(defn adjust-aim-pos
  ""
  [[old-aim old-x old-depth] [command value]]
   (cond
     (= :forward command) [old-aim (+ old-x value) (+ old-depth (* old-aim value))]
     (= :up command) [(- old-aim value) old-x old-depth]
     (= :down command) [(+ old-aim value) old-x old-depth]))

(defn day-2-p-1 [comms]
  (let [[pos depth] (reduce adjust-position [0 0] comms)]
    (* pos depth)))

(defn day-2-p-2 [comms]
  (let [[_ pos depth] (reduce adjust-aim-pos [0 0 0] comms)]
    (* pos depth)))

(defn tokenify
  "convert string to vector [:direction value]"
  [string]
  (let [[com val] (str/split string #" ")]
    [(keyword com) (Integer/parseInt val)]))

(defn get-answer [path-to-file]
  (let [s (map tokenify (str/split-lines (slurp path-to-file)))]
    (println "Answer for first part is: " (day-2-p-1 s))
    (println "Answer for second part is: " (day-2-p-2 s))))