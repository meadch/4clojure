; #95 To Tree, or not to Tree
; http://www.4clojure.com/problem/95

;Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must have a value, a left child, and a right child.

(defn bin-tree? [node]
  (and (coll? node)
       (= 3 (count node))
       (every? #(or (nil? %) (bin-tree? %)) (rest node))))

(= (bin-tree? '(:a (:b nil nil) nil))
   true)

(= (bin-tree? '(:a (:b nil nil)))
   false)

(= (bin-tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)

(= (bin-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)

(= (bin-tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)

(= (bin-tree? [1 [2 [3 [4 false nil] nil] nil] nil])
   false)

(= (bin-tree? '(:a nil ()))
   false)