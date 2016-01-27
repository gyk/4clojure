(fn [brd]
  (let [brd (vec (map vec brd))
        nr (count brd)
        nc (count (brd 0))
        neigh (fn [r c]
                (count (filter identity
                  (for [rr [-1 0 1] cc [-1 0 1]
                        :when (or (not= rr 0) (not= cc 0))]
                        (= \# (get-in brd [(+ r rr) (+ c cc)]))))))]
        (->>
          (for [r (range nr)
                c (range nc)]
            (let [n (neigh r c)]
              (if (= \# (get-in brd [r c]))
                (cond
                  (< n 2) \space
                  (<= 2 n 3) \#
                  :else \space)
                (if (= n 3) \# \space))))
          (partition nc) (map #(apply str %)))))
