(fn [t]
  (letfn [(kf [ld] (fn [{st :suit rk :rank}]
            (+ ((assoc {t 26} ld 13) st 0) rk)))]
    #(-> % first :suit kf (sort-by %) last)))
