(ns adv.day17
  (:require [clojure.string :as str]))



(defn lasers [answer]
  ;{:pre}
  )

(defn day-17-p-1 [s]
  (let [[_ _ _ min-y] (re-seq #"\d+" s) 
        v0 (* -1  (Integer/parseInt min-y))]
    (* 1/2 v0 (+ 1 v0))
    ))

(defn day-17-p-2 [s]
   (let [[min-x max-x max-y min-y] (re-seq #"\d+" s) 
         answer (lasers #{})
         answer2 (mortars answer)
         ]
     
     ))

(defn get-answer [path-to-file]
  (let [s (slurp path-to-file)]
    (time (println "Answer for first part is: " (day-17-p-1 s)))
    (time (println "Answer for second part is: " (day-17-p-2 s)))))