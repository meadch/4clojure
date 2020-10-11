; #79 Triangle Minimal Path
; http://www.4clojure.com/problem/79

; Write a function which calculates the sum of the minimal path through a triangle. The triangle is represented as a collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next row until the bottom of the triangle is reached.

(defn sum-min-path
  ([t] (sum-min-path (vec t) 0 0))
  ([t row cell]
   (let [val (get-in t (list row cell))]
     (+ val (if (nil? (get t (inc row)))
              0
              (min
               (sum-min-path t (inc row) cell)
               (sum-min-path t (inc row) (inc cell))))))))

(= 7 (sum-min-path 
        '([1]
          [2 4]
          [5 1 4]
          [2 3 4 5]))) ; 1->2->1->3

(= 20 (sum-min-path 
         '([3]
           [2 4]
           [1 9 3]
           [9 9 2 4]
           [4 6 6 7 8]
           [5 7 3 5 1 4]))) ; 3->4->3->2->7->1