; #80 Transitive Closure
; http://www.4clojure.com/problem/84
; Write a function which generates the [transitive closure](https://en.wikipedia.org/wiki/Transitive_closure) of a [binary relation](https://en.wikipedia.org/wiki/Binary_relation). The relation will be represented as a set of 2 item vectors.

; Write a function which generates the transitive closure of a binary relation. The relation will be represented as a set of 2 item vectors.

(defn transitive-closure [s]
  (let [mapping (zipmap (map first s) (map last s))
        make-relations (fn [k] (loop [key k val (get mapping key) relations []]
                                 (if (contains? mapping key)
                                   (recur val (get mapping val) (conj relations [k val]))
                                   relations)))]
    (set (mapcat make-relations (keys mapping)))))

(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (transitive-closure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))

(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (transitive-closure more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))

(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (transitive-closure progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))