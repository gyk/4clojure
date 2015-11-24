(fn [[top mid bottom :as board]]
  (let [xss
        (concat board (map vector top mid bottom)
          [[(top 0) (mid 1) (bottom 2)]
          [(top 2) (mid 1) (bottom 0)]])]
    (cond (some true? (map #(every? #{:x} %) xss)) :x
          (some true? (map #(every? #{:o} %) xss)) :o
          :else nil)))
