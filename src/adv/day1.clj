(ns adv.day1
   (:gen-class)
  (:require [clojure.string :as str]))
   
   (defn day-1-p-1
     "How many measurements are larger than the previous measurement?"
     [inp-vec]    
       (loop [last Integer/MAX_VALUE
              countx 0
              [current & remaining] inp-vec]
         (let [counter (if (>= last current) countx (inc countx))]
           (if (empty? remaining)
             counter
             (recur current counter remaining)))))
   
(defn day-1-p-2 
  "... using size three sliding window (3-element average -> sum is enough)"
  [inp]
   (let [inp-vec (vec inp)
         size (- (count inp-vec) 1)
         sum-sliding-window (fn [n] (+ (inp-vec (- n 1)) (inp-vec n) (inp-vec (+ n 1))))]
     (day-1-p-1 (map sum-sliding-window (range 1 size)))))

(defn get-answer [path-to-file]
  (let [s (map #(Integer/parseInt %)(str/split (slurp path-to-file) #"\n"))]
    (println "Answer for first part is: " (day-1-p-1 s))
    (println "Answer for second part is: " (day-1-p-2 s))
    ))