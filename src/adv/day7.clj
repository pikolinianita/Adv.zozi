(ns adv.day7)

;1p - solution should be around median
;2p - around mean average.

(defn result-p1 
  "amount of fuel required - for part 1
   vector of crabs
   n given position"
  [vec n]
  (reduce + (map #(Math/abs (- % n)) vec)))

(defn sum_n 
  "sum of numbers from 1 to n"
  [n]
  (* n (/ (+ n 1) 2)))

(defn result-p2 
  "amount of fuel required - for part 2
   vector of crabs
   n given position"
  [vec n]
  (reduce + (map #(sum_n (Math/abs (- % n))) vec))) 

(defn find-minimum
  "finds local minimum f (inp n), where initial n = guess and n is inc/dec by one"
  [f inp guess]
  (let [result (f inp guess)]
    (cond
      (> result (f inp (inc guess))) (recur f inp (inc guess))
      (> result (f inp (dec guess))) (recur f inp (dec guess))
      :else result)))

(defn day-7-p-1 [s]
  (let [sorted-inp (vec (sort s))
        median (sorted-inp (/ (count s) 2))]
    (find-minimum result-p1 sorted-inp median)))

(defn day-7-p-2 [s]
  (let [avg (int (/ (reduce + s) (count s)))]
    (find-minimum result-p2 s avg)))

(defn get-answer [path-to-file]
  (let [s (map #(Integer/parseInt %)  (re-seq #"\d+" (slurp path-to-file)))]
    (time (println "Answer for first part is: " (day-7-p-1 s)))
    (time (println "Answer for second part is: " (day-7-p-2 s)))))