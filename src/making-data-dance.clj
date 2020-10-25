; #113 Making Data Dance
; http://www.4clojure.com/problem/113

;Write a function that takes a variable number of integer arguments. If the output is coerced into a string, it should return a comma (and space) separated list of the inputs sorted smallest to largest. If the output is coerced into a sequence, it should return a seq of unique input elements in the same order as they were entered.

;Special Restrictions
;proxy

(defn make-dance [& numbers]
  (reify clojure.lang.ISeq
    (toString [_] (apply str (interpose ", " (sort numbers))))
    (seq [_] (if-not (nil? numbers) (distinct numbers)))))

(= "1, 2, 3" (str (make-dance 2 1 3)))

(= '(2 1 3) (seq (make-dance 2 1 3)))

(= '(2 1 3) (seq (make-dance 2 1 3 3 1 2)))

(= '(1) (seq (apply make-dance (repeat 5 1))))

(= "1, 1, 1, 1, 1" (str (apply make-dance (repeat 5 1))))

(and (= nil (seq (make-dance)))
     (=  "" (str (make-dance))))