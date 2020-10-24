; #121 Universal Computation Engine
; http://www.4clojure.com/problem/121

; Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols representing variables. The returned function has to accept a single parameter containing the map of variable names to their values.

; Special Restrictions
; eval
; resolve

(defn calc [exp]
  (let [ops {'/ / '* * '+ + '- -}]
    (fn compute [env]
      (cond (number? exp) exp
            (contains? env exp) (exp env)
            (contains? ops exp) (exp ops)
            :else (let [exp (map #((calc %) env) exp)] ;assume seq w/ operation in first position
                    (apply (first exp) (rest exp)))))))

(= 2 ((calc '(/ a b))
      '{b 8 a 16}))

(= 8 ((calc '(+ a b 2))
      '{a 2 b 4}))

(= [6 0 -4]
   (map (calc '(* (+ 2 a)
                (- 10 b)))
        '[{a 1 b 8}
          {b 5 a -2}
          {a 2 b 11}]))

(= 1 ((calc '(/ (+ x 2)
              (* 3 (+ y 1))))
      '{x 4 y 1}))