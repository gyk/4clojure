(fn [c]
  (let [[st rk] (apply map vector c)
        in-seq
          (let [m (zipmap "23456789TJQKA" (range 1 14))
                cons? #(= 5 (- (apply max %) (apply min %) -1) (count (distinct %)))
                rk (map m rk)]
            (or (cons? rk) (cons? (map #(mod % 13) rk))))
        [suits? ranks?]
          (map (fn [x] (partial #(-> %1 frequencies vals sort (= %2)) x)) [st rk])]
    (cond
      (and (apply = st) in-seq) :straight-flush
      (ranks? [1 4]) :four-of-a-kind
      (ranks? [2 3]) :full-house
      (suits? [5]) :flush
      in-seq :straight
      (ranks? [1 1 3]) :three-of-a-kind
      (ranks? [1 2 2]) :two-pair
      (ranks? [1 1 1 2]) :pair
      :else :high-card)))
