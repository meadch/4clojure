; (116) Prime Sandwich
; http://www.4clojure.com/problem/116#prob-title
; A balanced prime is a prime number which is also the mean of the primes directly before and after it in the sequence of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime.

(defn prime-sandwich? [n]
  (let [prime? (fn [x]
                 (cond (< x 2) false
                       (and (> x 2) (zero? (rem x 2))) false
                       :else (loop [y (dec x)]
                               (cond (= y 1) true
                                     (zero? (rem x y)) false
                                     :else (recur (dec y))))))
        next-prime (fn [x f]
                     (loop [x (f x)]
                       (if (or (prime? x) (zero? x))
                         x
                         (recur (f x)))))
        mean #(/ (apply + %&) (count %&))]
    (and
     (prime? n)
     (= n (mean (next-prime n inc) (next-prime n dec))))))


(= false (prime-sandwich? 4))

(= true (prime-sandwich? 563))

(= 1103 (nth (filter prime-sandwich? (range)) 15))