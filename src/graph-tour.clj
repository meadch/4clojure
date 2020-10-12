; #89 Graph Tour
; http://www.4clojure.com/problem/89

; Starting with a graph you must write a function that returns true if it is possible to make a tour of the graph in which every edge is visited exactly once.

; The graph is represented by a vector of tuples, where each tuple represents a single edge.

; The rules are:

; - You can start at any node.
; - You must visit each edge exactly once.
; - All edges are undirected.

(defn can-tour? [edges]
  (let [vectors (set (apply concat edges))
        tour (fn tour [node untraveled-edges]
               (or (empty? untraveled-edges)
                   (some true? (map-indexed (fn [i, edge]
                                              (cond (some #(= node %) edge)
                                                    (let [[a b] edge
                                                          target (if (= a node) b a)
                                                          remaining-edges (concat (subvec untraveled-edges 0 i) (subvec untraveled-edges (inc i)))]
                                                      (tour target (vec remaining-edges)))
                                                    :else false)) untraveled-edges))))]
    (boolean (some #(true? %) (map #(tour % edges) vectors)))))

(= true (can-tour? [[:a :b]]))

(= false (can-tour? [[:a :a] [:b :b]]))

(= false (can-tour? [[:a :b] [:a :b] [:a :c] [:c :a]
                     [:a :d] [:b :d] [:c :d]]))

(= true (can-tour? [[1 2] [2 3] [3 4] [4 1]]))

(= true (can-tour? [[:a :b] [:a :c] [:c :b] [:a :e]
                    [:b :e] [:a :d] [:b :d] [:c :e]
                    [:d :e] [:c :f] [:d :f]]))

(= false (can-tour? [[1 2] [2 3] [2 4] [2 5]]))