(ns adv.day9
  (:require [clojure.string :as str]
            ))

(defn low-points [vec2d]
  (let [size-y (count vec2d)
        size-x (count (first vec2d))
        vectrs (for [x (range size-x) y (range size-y)]
                 [y x])
        cross-vec [[1 0] [0 1] [0 -1] [-1 0]]
        check-min (fn [[x y]] (let [center-value (get-in vec2d [x y])
                                    cross-vals (->> cross-vec
                                                   (map #(mapv + [x y] %))
                                                   (map #(get-in vec2d % 99)))]
                                (if (every? #(< center-value %) cross-vals)
                                  [x y]
                                  0)))]
    (->> vectrs
         (map check-min)
         (filter vector?))))


(defn basin-size [map2d yx-vec]
  (let [queque (conj (clojure.lang.PersistentQueue/EMPTY) yx-vec)
        basin #{}
        cross-vec [[1 0] [0 1] [0 -1] [-1 0]]
        shore? (fn [coor] (< 8 (get-in map2d coor 99 )))]
    (loop [q queque
           bas basin]
      (cond
        (empty? q) (count bas)
        (shore? (peek q)) (recur (pop q) bas)
        (bas (peek q)) (recur (pop q) bas)
        :else (recur
               (apply conj (pop q) (map #(mapv + (peek q) %) cross-vec))
               (conj bas (peek q)))))))


(defn make-vec-of-vec [line-of-str]
  (let [str-into-vec-of-int (fn [s] (->> s
                                         (#(str/split % #""))
                                         (map #(Integer/parseInt %))
                                         (vec)))]
    (->> line-of-str
         (map str-into-vec-of-int)
         (reduce conj []))))


(defn day-9-p-1 [s]
  (let [vectr (make-vec-of-vec s)]
    (->> vectr
         (low-points)
         (map #(inc (get-in vectr %)))
         (reduce +))))
  

(defn day-9-p-2 [s] 
  (let [vectr (make-vec-of-vec s)]
    (->> vectr 
     (low-points)
     (map #(basin-size vectr %))
     (sort >)
     (take 3)
     (apply *))))


  (defn get-answer [path-to-file]
    (let [s (str/split-lines (slurp path-to-file))]
      (time (println "Answer for first part is: " (day-9-p-1 s)))
      (time (println "Answer for second part is: " (day-9-p-2 s)))))