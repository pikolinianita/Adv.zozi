(ns adv.day2
   (:require [clojure.string :as str]))

(defn adjust-position
  "use position and depth. command is [:com value] 
   :forward first +
    :down second +
    :up second -
   "
  [old-pos [command value]]
  (cond
    (= :forward command) [(+ (first old-pos) value) (last old-pos)]
    (= :up command) [(first old-pos) (- (last old-pos) value)]
    (= :down command) [(first old-pos) (+ (last old-pos) value)]
    ))

(defn adjust-aim-pos
  ""
  [[old-aim old-x old-depth] [command value]]
   (cond
     (= :forward command) [old-aim (+ old-x value) (+ old-depth (* old-aim value))]
     (= :up command) [(- old-aim value) old-x old-depth]
     (= :down command) [(+ old-aim value) old-x old-depth])
  )

(defn day-2-p-1 [comms]
  (let [dist-pair (reduce adjust-position [0 0] comms)]
    (* (first dist-pair) (last dist-pair))))

(defn day-2-p-2 [comms]
  (let [dist-pair (reduce adjust-aim-pos [0 0 0] comms)]
    (* (second dist-pair) (last dist-pair))
    ))

(defn tokenify
  "convert string to vector [:direction value]"
  [string]
  (let [pair (str/split string #" ")]
    [(keyword (first pair)) (Integer/parseInt (last pair))]))

(defn get-answer [path-to-file]
  (let [s (map tokenify (str/split-lines (slurp path-to-file)))]
   (time (println "Answer for first part is: " (day-2-p-1 s)))
    (time (println "Answer for second part is: " (day-2-p-2 s)))
    ))