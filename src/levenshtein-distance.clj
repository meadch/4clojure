; Levenshtein Distance

; Given two sequences x and y, calculate the Levenshtein distance of x and y, i. e. the minimum number of edits needed to transform x into y. The allowed edits are:

;- insert a single item
;- delete a single item
;- replace a single item with another item

(defn levenshtein
  [s1 s2]
  (let [lv (memoize (fn [f s1 s2]
                      (cond
                        (empty? s1) (count s2); done consuming s1, return count of chars leftover
                        (empty? s2) (count s1); done consuming s2, return count of chars leftover
                        (= (first s1) (first s2)) (f f (rest s1) (rest s2)) ; chars match, move forward w/out an edit
                        :else (inc ; an edit is required, three ways to do it
                               (min
                                (f f (rest s1) s2)
                                (f f s1 (rest s2))
                                (f f (rest s1) (rest s2)))))))
        lv (partial lv lv)]
    (lv s1 s2)))

(levenshtein "kitten" "sitting")

(= (levenshtein "kitten" "sitting") 3)

(= (levenshtein "closure" "clojure") (levenshtein "clojure" "closure") 1)

(= (levenshtein "xyx" "xyyyx") 2)

(= (levenshtein "" "123456") 6)

(= (levenshtein "Clojure" "Clojure") (levenshtein "" "") (levenshtein [] []) 0)

(= (levenshtein [1 2 3 4] [0 2 3 4 5]) 2)

(= (levenshtein '(:a :b :c :d) '(:a :d)) 2)

(= (levenshtein "ttttattttctg" "tcaaccctaccat") 10)

(= (levenshtein "gaattctaatctc" "caaacaaaaaattt") 9)