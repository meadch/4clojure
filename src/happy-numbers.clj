; #86 Happy numbers
; http://www.4clojure.com/problem/86

; Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and then sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number whose squared sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly. Write a function that determines if a number is happy or not.

(defn happy-number?
  ([n] (happy-number? n #{n}))
  ([n memo]
   (let [squared-sum (apply + (map #(* % %) (loop [n n digits []]
                                              (if (zero? (quot n 10))
                                                (conj digits (mod n 10))
                                                (recur (quot n 10) (conj digits (mod n 10)))))))]
     (cond (= squared-sum 1) true
           (contains? memo squared-sum) false
           :else (happy-number? squared-sum (conj memo squared-sum))))))


(= (happy-number? 7) true)

(= (happy-number? 986543210) true)

(= (happy-number? 2) false)

(= (happy-number? 3) false)