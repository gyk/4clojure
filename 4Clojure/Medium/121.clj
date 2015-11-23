(fn [code]
  (fn [m]
    (letfn [(exe [c]
              (cond
                (coll? c) (let [[op' & code] c
                                op ({'+ + '- - '* * '/ /} op')]
                            (apply op (map exe code)))
                (symbol? c) (m c)
                :else c))]
      (exe code))))
