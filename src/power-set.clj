; #85 Power Set
; http://www.4clojure.com/problem/85
; Write a function which generates the power set of a given set. The power set of a set x is the set of all subsets of x, including the empty set and x itself.

(defn make-power-set [s]
  (let [choose (fn choose [s k]
                 (cond
                   (> k (count s)) #{}
                   (= k 1) (map #(set (list %)) s)
                   :else (let [val (first s) rem (rest s)]
                           (concat
                            (choose rem k)
                            (map #(conj % val) (choose rem (dec k)))))))]
    (conj (set (mapcat #(choose s %) (range 1 (inc (count s))))) #{})))

(= (make-power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})

(= (make-power-set #{}) #{#{}})

(= (make-power-set #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})

(= (count (make-power-set (into #{} (range 10)))) 1024)