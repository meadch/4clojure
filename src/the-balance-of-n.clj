; #115 The Balance of N
; http://www.4clojure.com/problem/115

;A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a function which accepts an integer n, and returns true iff n is balanced.

(defn balanced? [n]
  (let [digits (loop [ds '() n n]
                 (if (zero? n)
                   ds
                   (recur (conj ds (rem n 10)) (quot n 10))))
        [left right] (map #(% (/ (count digits) 2) digits) [take take-last])]
    (= (apply + left) (apply + right))))


(= true (balanced? 11))

(= true (balanced? 121))

(= false (balanced? 123))

(= true (balanced? 0))

(= false (balanced? 88099))

(= true (balanced? 89098))

(= true (balanced? 89089))

(= (take 20 (filter balanced? (range)))
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])