; #117 For Science!
; http://www.4clojure.com/problem/117

; A mad scientist with tenure has created an experiment tracking mice in a maze. Several mazes have been randomly generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to reach the cheesy endpoint. Write a function which accepts a maze in the form of a collection of rows, each row is a string where:
; spaces represent areas where the mouse can walk freely
; hashes (#) represent walls where the mouse can not walk
; M represents the mouse's starting point
; C represents the cheese which the mouse must reach
; The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he escape the edge of the maze. Your function must return true iff the maze is solvable by the mouse.

(defn winnable? [maze]
  (let [index-of (fn index-of [str val]
                   (loop [i 0 str str]
                     (cond
                       (empty? str) nil
                       (= (first str) val) i
                       :else (recur (inc i) (rest str)))))

        initial-mouse-location (some identity (map-indexed
                                               (fn [y, str]
                                                 (let [x (index-of str \M)]
                                                   (if-not (nil? x) [x y] nil))) maze))
        search-maze (fn search-maze [[x y] visited]
                      (let [val (get-in maze [y x])]
                        (cond (contains? visited [x y]) false
                              (= val \C) true
                              (= val \#) false
                              (= val nil) false
                              :else (let [visited (conj visited [x y])
                                          next-steps [[x (inc y)] ; down
                                                      [x (dec y)] ; up
                                                      [(dec x) y] ; left
                                                      [(inc x) y] ; right
                                                      ]]
                                      (->>
                                       (map (fn [step] [step (filter #(not= step %) next-steps)]) next-steps)
                                       (some (fn [[step already-visited]] (search-maze step (apply conj visited already-visited))))
                                       (boolean))))))]

    (search-maze initial-mouse-location #{})))

(= true  (winnable? ["M   C"]))

(= false (winnable? ["M # C"]))

(= true  (winnable? ["#######"
                     "#     #"
                     "#  #  #"
                     "#M # C#"
                     "#######"]))

(= false (winnable? ["########"
                     "#M  #  #"
                     "#   #  #"
                     "# # #  #"
                     "#   #  #"
                     "#  #   #"
                     "#  # # #"
                     "#  #   #"
                     "#  #  C#"
                     "########"]))

(= false (winnable? ["M     "
                     "      "
                     "      "
                     "    ##"
                     "    #C"]))

(= true  (winnable? ["C######"
                     " #     "
                     " #   # "
                     " #   #M"
                     "     # "]))

(= true  (winnable? ["C# # # #"
                     "        "
                     "# # # # "
                     "        "
                     " # # # #"
                     "        "
                     "# # # #M"]))