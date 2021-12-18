(ns adv.day14
   (:require 
             [clojure.string :as str]))

(defn map-turn [input-mp ruleset-mp]
   (let [f (fn [mp key value]
             (if (= key :not-doubled) (assoc mp key value) 
                 (merge-with + mp 
                             {(first (ruleset-mp key)) value} 
                             {(last (ruleset-mp key)) value})))]
    (reduce-kv f {} input-mp)))

(defn map-rules 
  " lines with pattern 'CB -> H' into { ... 'CB' ['CH' 'HB'] ... }"
  [str-lines]
  (let [f (fn [mp [fir sec & line]]             
            (assoc mp (str fir sec) [(str fir (last line)) (str (last line) sec)]))]
    (reduce f {} str-lines)
  ))   

(defn map-input 
  "NNCB to {'NN' 1 'NC' 1 'CB' 1 :not-doubled [/N /B]} ; :Not-doubled  => first and last char"
  [poly ]
  (let [init {:not-doubled [(first poly) (last poly)]}
        list-of-input-pairs (map #(apply str %) (partition 2 1 poly))]
        (reduce #(merge-with + %1 {%2 1}) init list-of-input-pairs)
    ))

(defn calculate-diff [mp] 
  (let [endings (:not-doubled mp)
        pure (dissoc mp :not-doubled)
        count-letters (fn [mp [[f-ch s-ch] n]] (merge-with + mp {f-ch n} {s-ch n}))
        from-pure (reduce  count-letters {} (seq pure))
        result-doubled (sort (vals (count-letters from-pure [endings 1])))
        ]
   (/ (- (last result-doubled) (first result-doubled)) 2)
    )) 

(defn run-stuff [inp-txt rules-lines number]
  (loop [inp (map-input inp-txt)
        rules (map-rules rules-lines)
         n number] 
    (if (= n 0) (calculate-diff inp)
        (recur (map-turn inp rules) rules (dec n)))
    
    ))

(defn day-14-p-1 [[inp-str _ & commands]]
  (run-stuff inp-str commands 10))

(defn day-14-p-2 [[inp-str _ & commands]]
  (run-stuff inp-str commands 40))

(defn get-answer [path-to-file]
  (let [s (str/split-lines (slurp path-to-file))]
    (time (println "Answer for first part is: " (day-14-p-1 s)))
    (time (println "Answer for second part is: " (day-14-p-2 s)))))