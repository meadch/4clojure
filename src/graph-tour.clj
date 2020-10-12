; #89 Graph Tour
; http://www.4clojure.com/problem/89

; Starting with a graph you must write a function that returns true if it is possible to make a tour of the graph in which every edge is visited exactly once.

; The graph is represented by a vector of tuples, where each tuple represents a single edge.

; The rules are:

; - You can start at any node.
; - You must visit each edge exactly once.
; - All edges are undirected.

(= true (__ [[:a :b]]))

(= false (__ [[:a :a] [:b :b]]))

(= false (__ [[:a :b] [:a :b] [:a :c] [:c :a]
              [:a :d] [:b :d] [:c :d]]))

(= true (__ [[1 2] [2 3] [3 4] [4 1]]))

(= true (__ [[:a :b] [:a :c] [:c :b] [:a :e]
             [:b :e] [:a :d] [:b :d] [:c :e]
             [:d :e] [:c :f] [:d :f]]))

(= false (__ [[1 2] [2 3] [2 4] [2 5]]))