(ns adv.day5
   (:require [clojure.string :as str]))

(defn sorted-range [x y]
  (if (< x y)
    (range x (inc y))
    (range y (inc x))))

(defn sorted-range-p2 [x y]
  (if (< x y)
    (range x (inc y))
    (reverse (range y (inc x)))))

(def line? (fn [vectr] (or (= (vectr 1) (vectr 3)) (= (vectr 2) (vectr 0)))))

(defn vectorize
   "from '0,9 -> 5,9' to [0 9 5 9]"
  [v]
  (vec (map #(Integer/parseInt %) (re-seq #"\d+" v))))  

(defn make-vectrs-v&h [[x1 y1 x2 y2]]
  (for [x (sorted-range x1 x2)
        y (sorted-range y1 y2)]
    [x y]
    ))

(defn make-vectr-dia [[x1 y1 x2 y2]]
  (map vec (partition 2 (interleave (sorted-range-p2 x1 x2) (sorted-range-p2 y1 y2))))
  )

(defn day-5-p-1 [s]  
  (let [v&h-lines (map make-vectrs-v&h (filter line? s))
        maps (map #(zipmap % (repeat 1)) v&h-lines)]
    (count (filter #(< 1 %) (vals (apply merge-with + maps))))))

(defn day-5-p-2 [s] 
  (let [{v&h-vec true dia-vec false} (group-by line? s)
        v&h-maps (map #(zipmap % (repeat 1)) (map make-vectrs-v&h v&h-vec))
        dia-maps (map #(zipmap % (repeat 1)) (map make-vectr-dia dia-vec))
        merged-maps  (merge-with + (apply merge-with + v&h-maps) (apply merge-with + dia-maps)) ]
   (count (filter #(< 1 %) (vals merged-maps)))   
    ))

(defn get-answer [path-to-file]
  (let [s (map vectorize (str/split-lines (slurp path-to-file)))]
    (time (println "Answer for first part is: " (day-5-p-1 s)))
    (time (println "Answer for second part is: " (day-5-p-2 s)))))