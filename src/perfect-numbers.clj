
;; # 80 Perfect Numbers
;; http://www.4clojure.com/problem/80
;; A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6. Write a function which returns true for perfect numbers and false otherwise.

(defn perfect-number? [n]
  (->> (range 1 n)
       (filter #(zero? (rem n %)))
       (apply +)
       (= n)))

(= (perfect-number? 6) true)


(= (perfect-number? 7) false)


(= (perfect-number? 496) true)


(= (perfect-number? 500) false)


(= (perfect-number? 8128) true)