(ns adv.day1
   (:gen-class)
  (:require [clojure.string :as str]))
   
   (defn day-1-p-1 [inp-vec]    
       (loop [last Integer/MAX_VALUE
              count 0
              vec inp-vec]
         (if (empty? vec)
           count
           (recur (first vec) (if (>= last (first vec)) count (inc count)) (rest vec)))))
   
(defn day-1-p-2 [inp]
   (let [inp-vec (vec inp)
         size (- (count inp-vec) 1)]
     (day-1-p-1 
      (map 
       #(+ (inp-vec (- % 1)) (inp-vec %) (inp-vec (+ % 1))) 
       (range 1 size)))
     ))

(defn get-answer [path-to-file]
  (let [s (map #(Integer/parseInt %)(str/split (slurp path-to-file) #"\n"))]
    (println "Answer for first part is: " (day-1-p-1 s))
    (println "Answer for second part is: " (day-1-p-2 s))
    ))