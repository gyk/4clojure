(fn inf-mat
  ([f r c]
    (let [range-wtf (fn wtf [x]
                      (cons x (lazy-seq (wtf (inc x)))))
          rows (map #(partial f %) (range-wtf r))]
      (map #(map % (range-wtf c)) rows)))
  ([f]
    (inf-mat f 0 0))
  ([f r c nr nc]
    (take nr (map #(take nc %) (inf-mat f r c)))))
