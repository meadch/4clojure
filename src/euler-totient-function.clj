; #75 Euler's Totient Function
; http://www.4clojure.com/problem/75

; Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f (x) is defined as the number of positive integers less than x which are coprime to x. The special case f (1) equals 1. Write a function which calculates Euler's totient function.

(defn totient-count [x]
  (let [gcd (fn gcd [a b]
              (if (zero? b)
                a
                (recur b (mod a b))))
        co-prime? #(= 1 (gcd x %))]
    (if (= x 1) x
        (count (filter co-prime? (range x))))))

(= (totient-count 1) 1)

(= (totient-count 10) (count '(1 3 7 9)) 4)

(= (totient-count 40) 16)

(= (totient-count 99) 60)