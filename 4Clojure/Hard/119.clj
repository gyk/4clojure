(fn [ply brd]
  (letfn [(win? [[top mid bottom :as board]]
            (->> (concat board (map vector top mid bottom)
                    [[(top 0) (mid 1) (bottom 2)]
                    [(top 2) (mid 1) (bottom 0)]])
                  (some #(apply = ply %))
                  (not= nil)))
          (win-at? [pos]
            (and (= (get-in brd pos) :e)
                 (win? (assoc-in brd pos ply))))]
    (set (for [x (range 3), y (range 3) :when (win-at? [x y])] [x y]))))
