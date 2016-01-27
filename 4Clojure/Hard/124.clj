(fn [brd who]
  (let [nr (count brd)
        nc (count (brd 0))
        coords (for [r (range nr) c (range nc)] [r c])
        dirs [[-1 -1] [-1 0] [-1 1]
              [ 0 -1]        [ 0 1]
              [ 1 -1] [ 1 0] [ 1 1]]
        emp? #(or (= % 'e) (nil? %))
        transfer (fn [state which]
                    (case state
                      :init (if (= which 'e) :oppo :fail)
                      :oppo (if (emp? which)
                                :fail
                                (if (= who which) :fail :cont))
                      :cont (if (emp? which)
                                :fail
                                (if (= who which) :succ :cont))))
        put (fn [pos dir]
              (loop [p pos, state :init, reversed #{}]
                (let [new-state (transfer state (get-in brd p))
                      new-p (map + p dir)]
                  (case new-state
                    :succ {pos reversed}
                    :oppo (recur new-p new-state reversed)
                    :cont (recur new-p new-state (conj reversed p))
                    :fail {}))))]
    (apply merge (for [p coords d dirs] (put p d)))))
