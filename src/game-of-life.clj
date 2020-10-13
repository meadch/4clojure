; #94 Game of Life
; http://www.4clojure.com/problem/94

;; The game of life is a cellular automaton devised by mathematician John Conway.

;; The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:

;; 1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
;; 2) Any live cell with two or three live neighbours lives on to the next generation.
;; 3) Any live cell with more than three live neighbours dies, as if by overcrowding.
;; 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

;; Write a function that accepts a board, and returns a board representing the next generation of cells.

(defn next-game-state [board]
  (let [alive \#
        dead " "
        get-neighbors (fn get-neighbors [r c board]
                        (map #(get-in board %) (filter (fn [coords] (and (not= coords [r c]) (not (nil? (get-in board coords)))))
                                                       (for [x (range (dec r) (+ r 2))
                                                             y (range (dec c) (+ c 2))] [x, y]))))]
    (map-indexed (fn [i e]
                 (apply str (map-indexed (fn [j c]
                                (let [neighbors (get-neighbors i j board)
                                      live-neighbors (count (filter #(= % alive) neighbors))
                                      alive? (= c \#)
                                      dead? (not alive?)]
                                  (cond (and alive? 
                                             (< live-neighbors 2))        dead
                                        (and alive? 
                                             (or (= 2 live-neighbors) 
                                                 (= 3 live-neighbors)))   alive
                                        (and alive? 
                                             (> live-neighbors 3))        dead
                                        (and dead? 
                                             (= 3 live-neighbors))        alive
                                        :else                             dead))) e))) board)))


(= (next-game-state ["      "
        " ##   "
        " ##   "
        "   ## "
        "   ## "
        "      "])
   ["      "
    " ##   "
    " #    "
    "    # "
    "   ## "
    "      "])


(= (next-game-state ["     "
        "     "
        " ### "
        "     "
        "     "])
   ["     "
    "  #  "
    "  #  "
    "  #  "
    "     "])


(= (next-game-state ["      "
        "      "
        "  ### "
        " ###  "
        "      "
        "      "])
   ["      "
    "   #  "
    " #  # "
    " #  # "
    "  #   "
    "      "])