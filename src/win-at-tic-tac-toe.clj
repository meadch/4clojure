; #119 Win at Tic-Tac-Toe

; As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. Create a function that accepts a game piece and board as arguments, and returns a set (possibly empty) of all valid board placements of the game piece which would result in an immediate win.

; Board coordinates should be as in calls to get-in. For example, [0 1] is the topmost row, center position.

(defn winning-moves [piece board]
  (let [empty-spaces
        (filter identity
                (mapcat identity
                        (map-indexed (fn [i row] (map-indexed (fn [j val] (if (= val :e) [i j] nil)) row)) board)))
        get-diagonal (fn get-diagonal [rows] (reduce (fn [diag row]
                                                       (conj diag (get row (count diag)))) [] rows))
        winning-move? (fn winning-move? [piece [r c] board]
                        (let [board (assoc-in board [r c] piece)
                              row (get board r)
                              col (map #(get % c) board)]
                          (some (fn [row] (every? #(= piece %) row))
                                [row col (get-diagonal board) (get-diagonal (reverse board))])))]
    (set (filter #(winning-move? piece % board) empty-spaces))))


(= (winning-moves :x [[:o :e :e]
                      [:o :x :o]
                      [:x :x :e]])
   #{[2 2] [0 1] [0 2]})

(= (winning-moves :x [[:x :o :o]
                      [:x :x :e]
                      [:e :o :e]])
   #{[2 2] [1 2] [2 0]})

(= (winning-moves :x [[:x :e :x]
                      [:o :x :o]
                      [:e :o :e]])
   #{[2 2] [0 1] [2 0]})

(= (winning-moves :x [[:x :x :o]
                      [:e :e :e]
                      [:e :e :e]])
   #{})

(= (winning-moves :o [[:x :x :o]
                      [:o :e :o]
                      [:x :e :e]])
   #{[2 2] [1 1]})
