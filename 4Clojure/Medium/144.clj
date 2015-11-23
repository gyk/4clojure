(fn [x & xs]
  (letfn [(f [x & xs]
            (let [y ((first xs) x)]
              (cons x (lazy-seq (apply f y (next xs))))))]
    (apply f x (cycle xs))))
