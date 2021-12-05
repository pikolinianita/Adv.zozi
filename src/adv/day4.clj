(ns adv.day4
  (:require [clojure.string :as str]))

(def bingo-size 5)

(defn print-square [board]	
	(doall (map println (partition 5 board)))
	(println)
)

(defn make-board [lines]
  (let [numerify (fn [str] (Integer/parseInt str))
        line-to-vector (fn [line] (vec (map numerify (re-seq #"\d+" line))))] 
     (vec (apply concat [] (map line-to-vector lines)))))

(defn hit [board n]
	(let [hit-pos (.indexOf board n)]	
		(if (<= 0 hit-pos) 
		(update-in board [hit-pos] #(+ 100 %))
		board)
	))

(defn solved? [board]
(let [size (int (Math/sqrt (count board)))
	test-row (fn [pos] (->> board (drop (* pos size)) (take size)))
	test-col (fn [pos] (->> board (drop pos) (take-nth size)))
	hit? (fn [line] (every? #(< 100 %) line))]

 (true? (or
	(some true? (map hit? (map test-row (range size))))
	(some true? (map hit? (map test-col (range size))))))

))

(defn final-score-p1 [board n]

(println board n)
n
)

(defn try-solve-p1 [shots boards]
(let [hit-boards (vec (map #(hit % (first shots)) boards))
	  solved-vec (map solved? hit-boards)]
	;(println (first shots) (map solved? hit-boards))
	;(doall (map print-square hit-boards))
	;(println (class hit-boards))
	(if (every? false? solved-vec)
		(recur (rest shots) hit-boards)
		(final-score-p1 (get hit-boards (.indexOf solved-vec true)) (first shots)) 
		;(str "finished " (first shots))
)
))


(defn day-4-p-1 [s]
	(let [shots (map #(Integer/parseInt %) (str/split (first s) #","))
		board-str (map make-board (filter #(< 1 (count %)) (partition-by #(= "" %) (rest s))))]
	
(try-solve-p1 shots board-str)

  ))

(defn day-4-p-2 [s]
  "not implemented")

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-4-p-1 s)))
    (time (println "Answer for second part is: " (day-4-p-2 s)))))
