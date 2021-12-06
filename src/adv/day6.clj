(ns adv.day6
  (:require [clojure.string :as str]))

;Fish are simulated as 9 element vector, number of fiss is given (=pos) age

(def empty-map {0 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0})

(defn make1 
  "runs one simulation step"
  [[n0 n1 n2 n3 n4 n5 n6 n7 n8]]  
 [n1 n2 n3 n4 n5 n6 (+ n7 n0) n8 n0])

(defn sim-loop 
  "runs s simulation steps"
  [sim n]
  (if (pos? n) 
    (recur (make1 sim) (dec n)) 
    sim))

(defn simulate 
  "returns result = number of fishes"
  [vec d]
  (let [sim-vec (vals (into (sorted-map) (merge empty-map (frequencies vec))))]
   (reduce + (sim-loop sim-vec d))))

(defn day-6-p-1 [s] 
  (simulate s 80))

(defn day-6-p-2 [s] 
  (simulate s 256))

(defn get-answer [path-to-file]
  (let [s (map #(Integer/parseInt % ) (str/split  (slurp path-to-file) #","))]
    (time (println "Answer for first part is: " (day-6-p-1 s)))
    (time (println "Answer for second part is: " (day-6-p-2 s)))))