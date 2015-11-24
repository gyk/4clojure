(fn [& xs]
  (reify
    clojure.lang.Seqable
    (toString [_] (clojure.string/join ", " (map str (sort xs))))
    (seq [_] (seq (distinct xs)))))
