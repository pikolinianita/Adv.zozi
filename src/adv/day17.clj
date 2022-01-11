(ns adv.day17)

(defn possible-x-times
 "given initial vx, when is probe over target x (pos-x between min and max - I should use math, not recursion, lol)"
  [vx min-x max-x max-time]
  (loop [v vx
         pos 0
         time 0
         answer {}]  
    (if (or (= time max-time) (> pos max-x))
      answer
      (recur (if (> v 0) (dec v) v)
             (+ pos v)
             (inc time)
             (if (<= min-x pos max-x) (assoc answer time [vx]) answer)))))

(defn possible-y-times
  "given initial vy, when is probe over target y (pos-y between min and max - I should use math, not recursion, lol)"
  [vy min-y max-y max-time]
  (loop [v vy
         pos 0
         time 0
         answer {}]
    (if (or (= time max-time) (< pos min-y))
      answer
      (recur (dec v)
             (+ pos v)
             (inc time)
             (if (<= min-y pos max-y) (assoc answer time [vy] (+ time 1 (* -2 vy)) [(- vy)]) answer)))))

(defn make-x-map
  "after time _key_ probe is on target when initial vx are _value_ (that is [vx-1 vx-2 ...])"
  [min-x max-x max-time]
  (->> (+ max-x 1)
       (range 1)
       (map #(possible-x-times % min-x max-x max-time))
       (filter not-empty)
       (reduce #(merge-with into %1 %2))))
 
(defn make-y-map
  "after time _key_ probe is on target when initial vy are _value_ (that is [vy-1 vy-2 ...])"
  [min-y max-y max-time]
  (->> (range 0 (dec min-y) -1)
       (map #(possible-y-times % min-y max-y max-time))
       (filter not-empty)
       (reduce #(merge-with into %1 %2))))


(defn day-17-p-1
  "Mortar shot: assumed vx = 0 at the end, so vy = -(minimum y of target)"
  [s]
  (let [[_ _ min-y _] (re-seq #"-?\d+" s)
        v0 (Integer/parseInt min-y)]
    (* 1/2 v0 (+ 1 v0))))

(defn day-17-p-2 [s]
  (let [[min-x max-x min-y max-y] (map #(Integer/parseInt %) (re-seq #"-?\d+" s))
        max-time (+ 2 (* -2 min-y))
        x-map (make-x-map min-x max-x max-time)
        y-map (make-y-map min-y max-y max-time)
        kartesian (fn [n]
                    (let [xv (x-map n)
                          yv (y-map n)]
                      (for [x xv
                            y yv]
                        [x y])))]
    (->> (range max-time)
         (map kartesian)
         (reduce into #{})
         (count)
         )))

(defn get-answer [path-to-file]
  (let [s (slurp path-to-file)]
    (time (println "Answer for first part is: " (day-17-p-1 s)))
    (time (println "Answer for second part is: " (day-17-p-2 s)))))