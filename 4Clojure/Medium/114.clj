(fn f [n p [x & xs]]
  (letfn [(g [n]
            (cons x (lazy-seq (f n p xs))))]
    (cond
      (nil? x) []
      (p x) (if (= 1 n) [] (g (dec n)))
      :else (g n))))
