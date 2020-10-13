; #95 To Tree, or not to Tree
; http://www.4clojure.com/problem/95

;Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must have a value, a left child, and a right child.


(= (__ '(:a (:b nil nil) nil))
   true)


(= (__ '(:a (:b nil nil)))
   false)


(= (__ [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)


(= (__ [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)


(= (__ [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)


(= (__ [1 [2 [3 [4 false nil] nil] nil] nil])
   false)


(= (__ '(:a nil ()))
   false)